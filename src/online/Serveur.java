package online;

import java.io.*;
import java.net.*;
import java.util.*;

public class Serveur {
	protected static final int port = 15000; // Port sur lequel se connecter
	protected static ServerSocket socketServeur = null; // Socket qui s'occupe SEULEMENT de l'écoute du port
	protected static Thread accepte_connexion; // Déclaration d'un thread
	protected ArrayList<Socket> listeSocket; // Liste contenant les infos réseau
	protected ArrayList<String> listeJoueurs; //Liste contenant les identifiants
	protected int nbJoueurs = 0; 
	
	
	public Serveur() {
		this.listeSocket = new ArrayList<Socket>();
		this.listeJoueurs = new ArrayList<String>();
	}
	
	
	public void afficheSocket(ArrayList<Socket> listeSocket) {
		for(int i = 0; i < listeSocket.size(); i++) {
			System.out.println(i+":"+listeSocket.get(i).getInetAddress());
		}
	}
	
	public void afficheLogin(ArrayList<String> listeJoueurs) {
		for(int i = 0; i < listeJoueurs.size(); i++) {
			System.out.println(i+":"+listeJoueurs.get(i));
		}
	}
	
	public void envoyerListeJoueurs(PrintWriter sortie) {
		int j = 0;
		int nbJoueurs = listeJoueurs.size();
		if(nbJoueurs < 2) {
			sortie.println("Il y a "+nbJoueurs+" joueur connecté");	
		}
		else {
			sortie.println("Il y a "+nbJoueurs+" joueurs connectés");	
		}
		for(j=0; j<nbJoueurs;j++) {
			sortie.println(listeJoueurs.get(j));
		}
		sortie.flush();
		
	}
	 
	public static void main(String[] args) {
		
		try {
			socketServeur = new ServerSocket(port); // Création d'un socket d'écoute sur le port donnée
			Serveur serveur = new Serveur(); // Initialisation des ArrayList
			System.out.println("Le serveur est à l'écoute du port "+socketServeur.getLocalPort());
			
			/* Création d'un nouveau thread appelé accepte_connexion, dont la tâche à effectuer (la méthode run() ) 
			 * se trouve dans la classe Accepter_connexion
			 * qui est elle-même une implémentation de runnable (voir tuto openclassroom)
			 */
			accepte_connexion = new Thread(serveur.new Accepter_connexion(socketServeur));
			accepte_connexion.start();
				
		} catch (IOException e) {
			System.err.println("Le port " + socketServeur.getLocalPort() + " est déjà utilisé !");
		}
		
	}
		
	
	/*Classe membre qui contient ce que doit exécuter le thread
	 *Si ça vous pertubre, vous pouvez créer un nouveau fichier et faire un couper/coller.
	 *Si vous faites ça, n'oubliez pas la portée des variables.
	 */
	class Accepter_connexion implements Runnable {
	
		private ServerSocket socketServeur = null; // Socket qui s'occupe SEULEMENT de l'écoute du port
		private Socket socketClient = null; // Socket qui s'occupe de l'échange de flux avec le client
		private PrintWriter sortie = null;
		private BufferedReader entree = null; // L'entrée sera (presque) toujours lue en utilisant un buffer
		private String identifiant = null;
		private Thread traitements = null;
		
		// Récupération du socket de serveur de la classe Serveur
		public Accepter_connexion(ServerSocket ss) {
			socketServeur = ss;
		}
		
		// Démarrage du thread accepte_connexion
		public void run() {
			
			try {
				while(true){ // Normalement c'est un while(true), ou un autre prédicat
					
					socketClient = socketServeur.accept(); // On accepte la connexion
					
					entree = new BufferedReader(new InputStreamReader(socketClient.getInputStream())); //Initialisation du buffer sur le socketClient
					sortie = new PrintWriter(socketClient.getOutputStream()); // Initialisation du flux de sortie
					
					listeSocket.add(socketClient); // On ajoute le socket dans la liste (peut être utile pour la suite)
					identifiant = entree.readLine(); // On lit le sortie.println du Client
					listeJoueurs.add(identifiant); // On ajoute l'identifiant du client dans la liste
					
					System.out.println(identifiant+" vient de se connecter"); // Affichage d'un message côté Serveur
					/* Si on voulait envoyer un message chez le client, on aurait fait :
					 * sortie.println("blabla") côté Serveur
					 * sortie.flush() côté Serveur pour vider le buffer
					 * message = entree.readLine() côté Client
					 */
					
					sortie.println("connecte"); // envoi le statut du client
					sortie.flush();
					nbJoueurs++;
					
					traitements = new Thread(new Traitement_action(entree, sortie, identifiant));
					traitements.start();

				}
				/*afficheSocket(listeSocket);
				afficheLogin(listeJoueurs);
				System.out.println("Il y a "+nbJoueurs+" joueurs connectés"); 
				*/
				
				/* Fermeture du socket à la FIN du programme. Obligatoire pour libérer le port.
				 * Cette méthode n'est utile que si on souhaite fermer le serveur, 
				 * ce qui serait rare dans des conditions réelles */
				//socketServeur.close(); 
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
	
	
	/* Cette classe traitera les actions envoyés par un client.
	 * Un client envoie une demande au serveur (par exemple afficherJoueurs) et le serveur retourne un résultat.
	 * Les demandes sont définies dans un switch. Toute action inconnue ne sera pas traitée.
	 * Les méthodes System.out.println ne servent qu'au débuggage. On pourrait les utiliser pour faire des logs si on a le temps ?
	 */
	class Traitement_action implements Runnable {

		private PrintWriter sortie;
		private BufferedReader entree;
		private String demandeClient = null, identifiant = null, identifiant2 = null;
		 
		public Traitement_action(BufferedReader entree, PrintWriter sortie, String identifiant){
			this.entree = entree;
			this.sortie = sortie;
			this.identifiant = identifiant;
		}
			
		public void run() {
			 
			while(true){
				try {
					demandeClient = entree.readLine(); // Lit la demande d'un client
					System.out.println(identifiant +" : "+ demandeClient); 
					 
					
					// Liste des demandes possibles
					switch(demandeClient) {
					
						case "afficherJoueurs" :
							envoyerListeJoueurs(sortie); // Envoie la liste des joueurs à la sortie
					 		break;
					 		
						case "jouer_avec": // Un joueur demande de jouer avec un joueur connecté
							sortie.println("qui ?");; // Le serveur répond avec qui il voudrait jouer
							sortie.flush();
							identifiant2 = entree.readLine(); // Le joueur donne l'identifiant de l'autre joueur avec qui il veut jouer
							
							if(listeJoueurs.contains(identifiant2)) { // Si l'identifiant donné existe dans la liste, on fait le traitement
								System.out.println(identifiant+" veut jouer avec "+ identifiant2); //log
								Socket joueur2 = listeSocket.get(listeJoueurs.indexOf(identifiant2));
								PrintWriter sortieJ2 = new PrintWriter(joueur2.getOutputStream());
								BufferedReader entreeJ2 = new BufferedReader(new InputStreamReader(joueur2.getInputStream())); 
								sortieJ2.println(identifiant+" souhaite jouer avec vous. Accepter ?"); // On informe un joueur qu'une personne souhaite jouer avec lui
								sortieJ2.flush();
								if(entreeJ2.readLine().equals("accepte")) { // Si il accepte, on lance la partie (je bloque pour la suite !!!)
									System.out.println("test");
									sortie.println("lancement");
									sortie.flush();
									sortieJ2.println("lancement");
									sortieJ2.flush();
								}
							}
							else {
								sortie.println("Ce joueur n'existe pas.");
								sortie.flush();
							}
							break;
							
					 }
					 
					
				 } catch (IOException e) {
						
					 e.printStackTrace();
				 }
			}
		}
	}
}