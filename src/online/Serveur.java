package online;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Le serveur
 */
public class Serveur {
	// Un identifiant unique pour chaque connexion
	private static int idUnique;
	// Une liste contenant les clients
	private ArrayList<ClientThread> listeClients;
	// Pour afficher le temps
	private SimpleDateFormat sdf;
	// Le numéro du port à écouter pour la connexion
	private int port;
	// Booléen qui décide de l'arrêt du serveur
	private boolean enCours;
	

	/*
	 *  Constructeur du serveur qui reçoit le port à écouter en paramètre
	 * 
	 */
	public Serveur(int port) {
		this.port = port;
		sdf = new SimpleDateFormat("HH:mm:ss");
		// Liste des Clients
		listeClients = new ArrayList<ClientThread>();
	}
	
	
	public void start() {
		enCours = true;
		/* Création d'un socket serveur qui attend pour les demandes de connexion */
		try 
		{
			// Le socket utilisé par le serveur
			ServerSocket socketServeur = new ServerSocket(port);

			// Boucle infinie qui attend pour les connexion
			while(enCours) 
			{
				// Message qui dit que le serveur est en train d'attendnre
				affiche("Le serveur attend des clients sur le port " + port + ".");
				
				Socket socket = socketServeur.accept();  	// Accepte la connexion
				
				// Si on arrête le serveur
				if(!enCours)
					break; // Sort de la boucle;
				
				ClientThread t = new ClientThread(socket);  // crée un Thread pour chaque client connecté
				listeClients.add(t); // Enregistre le client dans la liste
				t.start(); // démarre le Thread
			}
			// Si le serveur est arrêté, on arrête les clients et on ferme les entrées/sorties
			try {
				socketServeur.close();
				for(int i = 0; i < listeClients.size(); ++i) {
					ClientThread client = listeClients.get(i);
					try {
					client.sInput.close();
					client.sOutput.close();
					client.socket.close();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
			catch(Exception e) {
				affiche("Exception en fermant le serveur et le client : " + e);
			}
		}
		// Erreur innatendue
		catch (IOException e) {
            String msg = sdf.format(new Date()) + " Exception sur le socket du serveur : " + e + "\n";
			affiche(msg);
		}
	}		
    
	
	/*
	 * Afficher un événement (pas un message) dans la console
	 */
	private void affiche(String msg) {
		String temps = sdf.format(new Date()) + " " + msg;
		System.out.println(temps);
	}
	
	
	/*
	 *  Pour envoyer un message à tous les clients
	 */
	private synchronized void diffusion(String expediteur, String message) {
		// Ajoute le temps au format HH:mm:ss et \n au message 
		String temps = sdf.format(new Date());
		String messageLf = temps + " "   + expediteur + " dit : " + message + "\n";
	
		// Affiche le message dans console
		System.out.print(messageLf);
	
		
		// On boucle dans l'ordre inverse au cas où il y aurait un client à supprimer
		// parce qu'il a été déconnecté
		for(int i = listeClients.size(); --i >= 0;) {
			ClientThread ct = listeClients.get(i);
			
			// Essaite d'écrire au Client. Si ça échoue, on l'enlève de la liste 
			// (car ses entrées/sorties son fermées donc il n'est plus là)
			if(!ct.ecritMsg(messageLf)) {
				listeClients.remove(i);
				affiche("Client déconnecté : " + ct.identifiant + ". Il a été supprimé de la liste.");
			}
		}
	}

	// Pour un client qui s'est déconnecté avec le message DECO
	synchronized void remove(int id) {
		
		// Scan la liste jusqu'à trouver l'id
		for(int i = 0; i < listeClients.size(); ++i) {
			ClientThread ct = listeClients.get(i);
			
			// Trouvé
			if(ct.id == id) {
				listeClients.remove(i);
				return;
			}
		}
	}

	
	/*
	 * Pour démarrer l'application en mode console, on peut utiliser les commandes suivantes : 
	 * java Serveur
	 * java Serveur numPort
	 * Si aucun port n'est spécifié, on utilise le port 1500
	 */ 
	public static void main(String[] args) {
		// Démarre le serveur sur le port 1500 sauf si numPort est donné 
		int numPort = 1500;
		switch(args.length) {
			case 1:
				try {
					numPort = Integer.parseInt(args[0]);
				}
				catch(Exception e) {
					System.out.println("Numéro de port invalide.");
					System.out.println("Utilisation : java Serveur [numPort]");
					return;
				}
			case 0:
				break;
			default:
				System.out.println("Utilisation : java Serveur [numPort]");
				return;
				
		}
		// Création d'un objet serveur, puis on le démarre
		Serveur serveur = new Serveur(numPort);
		serveur.start();
	}

	/** Une instance de ce Thread tournera pour chaque client */
	class ClientThread extends Thread {
		// Le socket sur lequel écouter/parler
		Socket socket;
		ObjectInputStream sInput;
		ObjectOutputStream sOutput;
		// Identifiant unique (pour faciliter la déconnexion
		int id;
		// L'identifiant du client
		String identifiant;
		// Le seul type de message qui sera reçu
		ChatMessage cm;
		// Date de la connexion
		String date;
		boolean partieEnCours = false;
		
		ClientThread autreJoueur = null;

		// Constructeur
		ClientThread(Socket socket) {
			// Identifiant unique
			id = idUnique++;
			this.socket = socket;
			/* Création des flux de données */
			System.out.println("Le Thread essaie de créer des flux d'entrée/sortie");
			try
			{
				// Création de la sortie en premier
				sOutput = new ObjectOutputStream(socket.getOutputStream());
				sInput  = new ObjectInputStream(socket.getInputStream());
				// Lit l'identifiant du client
				identifiant = (String) sInput.readObject();
				affiche(identifiant + " vient de se connecter.");
			}
			catch (IOException e) {
				affiche("Exception lors de la création des entrée/sortie : " + e);
				return;
			}
			catch (ClassNotFoundException e) {
			}
            date = new Date().toString() + "\n";
		}
		
		public ClientThread chercheClient(String identifiant) {
			ClientThread client = null;
			for(int i = 0; i<listeClients.size(); i++) {
				client = listeClients.get(i);
				if(client.identifiant.equals(identifiant)) {
					return client;
				}
			}
			return client;
		}
		
		public ChatMessage litMessage(ObjectInputStream sInput) {
			try {
				return (ChatMessage) sInput.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch ( IOException e ){
				e.printStackTrace();
			}
			return null;
		}

		// Ce qui va tourner tout le temps
		public void run() {

			// A boucler jusqu'à déconnexion
			boolean enCours = true;
			
			while(enCours) {
				
				cm = litMessage(sInput);
				
				// La partie message ChatMessage
				String message = cm.getMessage();
				
				//La partie destinataire de ChatMessage
				String destinataire = cm.getDestinataire();
				
				
				// Switch sur le type de message reçu
				switch(cm.getType()) {

				case ChatMessage.MESSAGE:
					diffusion(identifiant, message);
					break;
				case ChatMessage.DECO:
					affiche(identifiant + " déconnecté.");
					enCours = false;
					break;
				case ChatMessage.PARTIESDISPO:
					ecritMsg("Liste des parties disponibles à " + sdf.format(new Date()) + "\n");
					for(int i = 0; i < listeClients.size(); ++i) {
						ClientThread ct = listeClients.get(i);
						ecritMsg((i+1) + ") " + ct.identifiant + " depuis " + ct.date);
					}
					break;
				case ChatMessage.JOUERAVEC:
					autreJoueur = chercheClient(destinataire);
					autreJoueur.ecritMsg(identifiant + " veut jouer avec vous... accepter ?");
					break;
				case ChatMessage.ACCEPTE:
					autreJoueur = chercheClient(destinataire);
					ecritMsg("Début de la partie...");
					autreJoueur.ecritMsg("Début de la partie...");
					break;
				case ChatMessage.MESSAGEA:
					autreJoueur = chercheClient(destinataire);
					autreJoueur.ecritMsg(message);
				}
			}
			// Supprime le client de la liste des clients connectés
			remove(id);
			close();
		}

		// On essaie de tout fermer
		private void close() {
			try {
				if(sOutput != null) sOutput.close();
			}
			catch(Exception e) {}
			try {
				if(sInput != null) sInput.close();
			}
			catch(Exception e) {};
			try {
				if(socket != null) socket.close();
			}
			catch (Exception e) {}
		}

		/*
		 * Ecrire un String vers le client
		 */
		private boolean ecritMsg(String msg) {
			// Si le client est toujours connecté, on lui envoie un message
			if(!socket.isConnected()) {
				close();
				return false;
			}
			// Ecrit le message dans le flux de sortie
			try {
				sOutput.writeObject("\n"+msg);
			}
			// Si il y a une erreur, on en informe l'utilisateur
			catch(IOException e) {
				affiche("Erreur en envoyer le message " + identifiant);
				affiche(e.toString());
			}
			return true;
		}
	}
}


