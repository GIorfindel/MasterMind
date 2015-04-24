package online;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

	protected static final int port = 15000; //Même port que le serveur
	protected static Socket socket = null; // Initialisation du socket clien
	protected static Thread emission; // Initialisation du thread
	protected static Thread reception; // Initialisation du thread 
	protected static PrintWriter sortie = null;
	protected static BufferedReader entree = null;
	protected String identifiant = null;
	
	public Client(String identifiant) {
		this.identifiant = identifiant;
	}
	
	public static void afficherListeJoueurs() {
		try {
			entree = new BufferedReader(new InputStreamReader(socket.getInputStream())); //Initialisation du buffer sur le socketClient
			int nbJoueurs = Integer.parseInt(entree.readLine());
			for(int i = nbJoueurs; i > 0; i--) {
				System.out.println(entree.readLine());
			}
		} catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	public static void main(String[] args) {
	
		
		try {
			
			Client client = new Client("toto"); // L'identifiant est normalement fourni par le programme
			
			
			System.out.println("Demande de connexion");
			socket = new Socket("127.0.0.1",port); // Création d'un nouveau socket à l'adresse et au port donnés
			sortie = new PrintWriter(socket.getOutputStream());
			entree = new BufferedReader(new InputStreamReader(socket.getInputStream())); //Initialisation du buffer sur le socketClient
			sortie.println(client.identifiant);
			sortie.flush();
			String statut = entree.readLine();
			
			
			if(statut.equals("connecte")) {
				//Après acceptation par le serveur (socketserveur.accept())
				System.out.println("Connexion établie avec le serveur"); // Si le message s'affiche c'est que je suis connecté
				emission = new Thread(client.new Emission(sortie));
				reception = new Thread(client.new Reception(entree));
				emission.start();
				reception.start();

			}
			else {
				System.out.println("Connexion échouée " + statut);
			}
			
		} catch (UnknownHostException e) {
		  System.err.println("Impossible de se connecter à cette adresse");
		} catch (IOException e) {
		  System.err.println("Aucun serveur à l'écoute de ce port");
		}
	}
	
	class Emission implements Runnable {

		private PrintWriter sortie;
		private String action = null;
		private Scanner clavier = null;
		
		public Emission(PrintWriter sortie) {
			this.sortie = sortie;	
		}
		
		public void run() {
			
			clavier = new Scanner(System.in);
			  
			while(true){
				System.out.println(identifiant+" : ");
				action = clavier.nextLine();
				sortie.println(action);
				sortie.flush();
			}
		}
	}
	
	public class Reception implements Runnable {

		private BufferedReader entree;
		private String message = null;
		
		public Reception(BufferedReader entree){
			this.entree = entree;
		}
		
		public void run() {
			
			while(true){
				try {
					
					message = entree.readLine();
					System.out.println(message);
				
			    } catch (IOException e) {	
					e.printStackTrace();
				}
			}
		}
	}
	
}