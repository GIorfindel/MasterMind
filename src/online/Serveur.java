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
	class Accepter_connexion implements Runnable{
	
		private ServerSocket socketServeur = null; // Socket qui s'occupe SEULEMENT de l'écoute du port
		private Socket socketClient = null; // Socket qui s'occupe de l'échange de flux avec le client
		private PrintWriter sortie = null;
		private BufferedReader entree = null; // L'entrée sera (presque) toujours lue en utilisant un buffer
		private String identifiant = null;
		
		// Récupération du socket de serveur de la classe Serveur
		public Accepter_connexion(ServerSocket ss) {
			socketServeur = ss;
		}
		
		// Démarrage du thread accepte_connexion
		public void run() {
			
			try {
				while(nbJoueurs < 5){ // Normalement c'est un while(true), ou un autre prédicat
					
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
					
					nbJoueurs++;
				}
				afficheSocket(listeSocket);
				afficheLogin(listeJoueurs);
				System.out.println("Il y a "+nbJoueurs+" joueurs connectés"); 
				
				
				/* Fermeture du socket à la FIN du programme. Obligatoire pour libérer le port.
				 * Cette méthode n'est utile que si on souhaite fermer le serveur, 
				 * ce qui serait rare dans des conditions réelles */
				socketServeur.close(); 
				
				
			} catch (IOException e) {
				e.getStackTrace();
				System.out.println(e);
			}			
		}
	}
}