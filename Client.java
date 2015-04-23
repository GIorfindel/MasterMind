package online;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

	protected static final int port = 15000; //Même port que le serveur
	protected static Socket socket = null; // Initialisation du socket clien
	protected static Thread t1; // Initialisation du premier thread 
	protected static PrintWriter sortie = null;
	protected static BufferedReader entree = null;
	protected static String identifiant = null; 	
	
	
	public static void main(String[] args) {
	
		
	try {
		String identifiant = "tata"; // L'identifiant est normalement fourni par le programme
		
		System.out.println("Demande de connexion");
		socket = new Socket("127.0.0.1",port); // Création d'un nouveau socket à l'adresse et au port donnés
		sortie = new PrintWriter(socket.getOutputStream());
		sortie.println(identifiant);
		sortie.println("test");
		sortie.flush();
		
		//Après acceptation par le serveur (socketserveur.accept())
		System.out.println("Connexion établie avec le serveur"); // Si le message s'affiche c'est que je suis connecté	
		
	} catch (UnknownHostException e) {
	  System.err.println("Impossible de se connecter à cette adresse");
	} catch (IOException e) {
	  System.err.println("Aucun serveur à l'écoute de ce port");
	}
	
	

	}

}