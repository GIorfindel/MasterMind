package online;


import java.net.*;
import java.io.*;
import java.util.*;

/*
 * Le client 
 */
public class Client  {

	// Les Entrées/Sorties
	private ObjectInputStream sInput;		// Pour lire depuis le socket
	private ObjectOutputStream sOutput;		// Pour écrire depuis le socket
	private Socket socket;
	
	// Le serveur, l'identifiant, le port
	private String serveur, identifiant;
	private int port;

	/*
	 *  Constructeur
	 *  serveur: L'adresse du serveur
	 *  port: Le numéro de port
	 *  identifiant: L'identifiant
	 */
	Client(String serveur, int port, String identifiant) {
		// which calls the common constructor with the GUI set to null
		this.serveur = serveur;
		this.port = port;
		this.identifiant = identifiant;
	}
	
	/*
	 * Pour démarrer un dialogue
	 */
	public boolean demarrer() {
		// Essaie de se connecter au serveur
		try {
			socket = new Socket(serveur, port);
		} 
		// Si la connexion échoue
		catch(Exception ec) {
			afficher("Erreur lors de la connexion au serveur :" + ec);
			return false;
		}
		
		String msg = "Connexion acceptée " + socket.getInetAddress() + ":" + socket.getPort();
		afficher(msg);
	
		/* Création des flux de données */
		try
		{
			sInput  = new ObjectInputStream(socket.getInputStream());
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException eIO) {
			afficher("Exception en créant les flux : " + eIO);
			return false;
		}

		// Création du Thread à l'écoute du serveur
		new EcouteServeur().start();
		
		// Envoie l'identifitant au serveur. C'est le seul message envoyé en tant
		// que String. Tous les autres messages seront des objets de ChatMessage
		try
		{
			sOutput.writeObject(identifiant);
		}
		catch (IOException e) {
			afficher("Exception en se connectant : " + e);
			deconnexion();
			return false;
		}
		// Succès
		return true;
	}
	
	/*
	 * Pour afficher un message dans la console
	 */
	private void afficher(String msg) {
		System.out.println(msg);      // println in console mode
	}
	
	/*
	 * Pour envoyer un message vers le serveur
	 */
	void sendMessage(ChatMessage msg) {
		try {
			sOutput.writeObject(msg);
		}
		catch(IOException e) {
			afficher("Exception en écrivant au serveur: " + e);
		}
	}

	/*
	 * Lorsque quelque chose se passe mal
	 * Ferme les flux d'entrée et de sortie, et déconnecte.
	 */
	private void deconnexion() {
		try { 
			if(sInput != null) sInput.close();
		}
		catch(Exception e) {} // On ne peut plus faire grand chose
		try {
			if(sOutput != null) sOutput.close();
		}
		catch(Exception e) {} // On ne peut plus faire grand chose
        try{
			if(socket != null) socket.close();
		}
		catch(Exception e) {} // On ne peut plus faire grand chose
				
	}
	/*
	 * Pour demarrer le client en mode console, il fau utiliser les commandes suivantes :
	 * java Client
	 * java Client identifiant
	 * java Client identifiant numPort
	 * java Client identifiant numPort adresseServeur
	 * dans le terminal
	 * Si numPort n'est pas indiqué, 1500 sera utilisé
	 * Si adresseServeur n'est pas indiqué "localHost" est utilisé
	 * Si identifiant n'est pas indiqué "Anonymous" est utilisé
	 * java Client 
	 * est equivalent à
	 * java Client Anonymous 1500 localhost  
	 * 
	 * En mode console, s'il y a une erreur, le programme s'arrête
	 * 
	 */
	public static void main(String[] args) {
		// Valeurs par défaut
		int numPort = 1500;
		String adresseServeur = "89.2.133.53";
		String login = "christ";

		// Selon les arguments donnés :
		switch(args.length) {
			// javac Client identifiant numPort serverAddr
			case 3:
				adresseServeur = args[2];
			// javac Client identifiant numPort
			case 2:
				try {
					numPort = Integer.parseInt(args[1]);
				}
				catch(Exception e) {
					System.out.println("Numéro de port invalise");
					System.out.println("Utilisation : java Client [identifiant] [numPort] [adresseServeur]");
					return;
				}
			// javac Client identifiant
			case 1: 
				login = args[0];
			// java Client
			case 0:
				break;
			// Nombre d'argument invalide
			default:
				System.out.println("Utilisation : java Client [identifiant] [numPort] {adresseServeur]");
			return;
		}
		// Création de l'objet Client
		Client client = new Client(adresseServeur, numPort, login);
		
		// Test si on peut démarrer la connexion au serveur
		// Si ça échoue, on termine
		if(!client.demarrer())
			return;
		
		// Attends le message de l'utilisateur
		Scanner scan = new Scanner(System.in);
		int j = 0;
		// Boucle infinie qui traite ce qui actionné par l'utilisateur
		while(true) {
			String msg[] = new String[3];
			int i = 0;
			while(j<1) {
				System.out.print(login + " > ");
				j++;
			}
			
			// Lit le message de l'utilisateur
			String entree = scan.nextLine();
			StringTokenizer t = new StringTokenizer (entree, "%%");
			while(t.hasMoreTokens()) {
				msg[i] = t.nextToken();
				i++;
			}
			
			// Déconnexion
			// Utilisation : DECO
			if(msg[0].equalsIgnoreCase("DECO")) {
				client.sendMessage(new ChatMessage(ChatMessage.DECO, "", ""));
				// break pour démarrer la déconnexion
				break;
			}
			
			// Demande qui est en ligne
			// Utilisation : PARTIESDISPO
			else if(msg[0].equalsIgnoreCase("PARTIESDISPO")) {
				client.sendMessage(new ChatMessage(ChatMessage.PARTIESDISPO, "", ""));				
			}
			
			// Rejoindre un joueur
			// Utilisation : JOUERAVEC%%toto
			else if(msg[0].equalsIgnoreCase("JOUERAVEC")) {
				client.sendMessage(new ChatMessage(ChatMessage.JOUERAVEC, msg[1], ""));
			}
			
			// Accepter un joueur
			// Utilisation : ACCEPTE%%toto
			else if(msg[0].equalsIgnoreCase("ACCEPTE")) {
				client.sendMessage(new ChatMessage(ChatMessage.ACCEPTE, msg[1], ""));
			}
			
			// Refuser un joueur
			// Utilisation : REFUSE%%toto
			else if(msg[0].equalsIgnoreCase("REFUSE")) {
				client.sendMessage(new ChatMessage(ChatMessage.ACCEPTE, msg[1], ""));
			}
			
			//Message privé
			// Utilisation : MESSAGEA%%toto%%message
			else if(msg[0].equalsIgnoreCase("MESSAGEA")) {
				client.sendMessage(new ChatMessage(ChatMessage.MESSAGEA, msg[1], msg[2]));
			}
			
			//Message à tout le monde
			// Utilisation : tapez simplement votre message
			else {	
				client.sendMessage(new ChatMessage(ChatMessage.MESSAGE,"", msg[0]));
			}
		}
		// Si on sort de la boucle, on se déconnecte
		client.deconnexion();
		scan.close();
	}

	/*
	 * Classe qui attends le message du serveur
	 * System.out.println() est utilisé pour afficher le message dans la console
	 */
	class EcouteServeur extends Thread {

		public void run() {
			while(true) {
				try {
					String msg = (String) sInput.readObject();
					
						System.out.println(msg);
						System.out.print(identifiant + " > ");
				}
				catch(IOException e) {
					afficher("La connexion au serveur a échouée : " + e);
					break;
				}
				// On ne peut rien faire mais catch obligatoire au cas où
				catch(ClassNotFoundException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}

