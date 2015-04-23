package online;

import java.io.*;
import java.net.*;
import java.util.*;

public class Serveur {
	protected static final int port = 15000; // Port sur lequel se connecter
	protected static ServerSocket socketServeur = null; // Socket qui s'occupe SEULEMENT de l'�coute du port
	protected static Thread accepte_connexion; // D�claration d'un thread
	protected ArrayList<Socket> listeSocket; // Liste contenant les infos r�seau
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
			socketServeur = new ServerSocket(port); // Cr�ation d'un socket d'�coute sur le port donn�e
			Serveur serveur = new Serveur(); // Initialisation des ArrayList
			System.out.println("Le serveur est � l'�coute du port "+socketServeur.getLocalPort());
			
			/* Cr�ation d'un nouveau thread appel� accepte_connexion, dont la t�che � effectuer (la m�thode run() ) 
			 * se trouve dans la classe Accepter_connexion
			 * qui est elle-m�me une impl�mentation de runnable (voir tuto openclassroom)
			 */
			accepte_connexion = new Thread(serveur.new Accepter_connexion(socketServeur));
			accepte_connexion.start();
				
		} catch (IOException e) {
			System.err.println("Le port " + socketServeur.getLocalPort() + " est d�j� utilis� !");
		}
		
	}
		
	
	/*Classe membre qui contient ce que doit ex�cuter le thread
	 *Si �a vous pertubre, vous pouvez cr�er un nouveau fichier et faire un couper/coller.
	 *Si vous faites �a, n'oubliez pas la port�e des variables.
	 */
	class Accepter_connexion implements Runnable{
	
		private ServerSocket socketServeur = null; // Socket qui s'occupe SEULEMENT de l'�coute du port
		private Socket socketClient = null; // Socket qui s'occupe de l'�change de flux avec le client
		private PrintWriter sortie = null;
		private BufferedReader entree = null; // L'entr�e sera (presque) toujours lue en utilisant un buffer
		private String identifiant = null;
		
		// R�cup�ration du socket de serveur de la classe Serveur
		public Accepter_connexion(ServerSocket ss) {
			socketServeur = ss;
		}
		
		// D�marrage du thread accepte_connexion
		public void run() {
			
			try {
				while(nbJoueurs < 5){ // Normalement c'est un while(true), ou un autre pr�dicat
					
					socketClient = socketServeur.accept(); // On accepte la connexion
					
					entree = new BufferedReader(new InputStreamReader(socketClient.getInputStream())); //Initialisation du buffer sur le socketClient
					sortie = new PrintWriter(socketClient.getOutputStream()); // Initialisation du flux de sortie
					
					listeSocket.add(socketClient); // On ajoute le socket dans la liste (peut �tre utile pour la suite)
					
					identifiant = entree.readLine(); // On lit le sortie.println du Client
					
					listeJoueurs.add(identifiant); // On ajoute l'identifiant du client dans la liste
					
					System.out.println(identifiant+" vient de se connecter"); // Affichage d'un message c�t� Serveur
					/* Si on voulait envoyer un message chez le client, on aurait fait :
					 * sortie.println("blabla") c�t� Serveur
					 * sortie.flush() c�t� Serveur pour vider le buffer
					 * message = entree.readLine() c�t� Client
					 */
					
					nbJoueurs++;
				}
				afficheSocket(listeSocket);
				afficheLogin(listeJoueurs);
				System.out.println("Il y a "+nbJoueurs+" joueurs connect�s"); 
				
				
				/* Fermeture du socket � la FIN du programme. Obligatoire pour lib�rer le port.
				 * Cette m�thode n'est utile que si on souhaite fermer le serveur, 
				 * ce qui serait rare dans des conditions r�elles */
				socketServeur.close(); 
				
				
			} catch (IOException e) {
				e.getStackTrace();
				System.out.println(e);
			}			
		}
	}
}