package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;


import mastermind.Joueur;

import client.Paquet;

public class Serveur {
	private ArrayList<Client> listeClients;
	private int port;
	private boolean enCours;
	public static int ID = 0;
	
	public Serveur( int port ){
		this.port = port;
		this.listeClients = new ArrayList<Client>();
		this.enCours =false;
	}
	
	public void start(){
		this.enCours = true;
		ServerSocket socketServeur = null;
		try {
			socketServeur = new ServerSocket( this.port );
			while( enCours ){
				Socket socket = socketServeur.accept();  	// Accepte la connexion, cette methode est bloquante
				Client c = new Client( socket, this );  // crée un Thread pour chaque client connecté
				listeClients.add( c ); // Enregistre le client dans la liste
				c.start(); // démarre le Thread
			}
		// Erreur innatendue
		}catch (IOException e) {
			e.printStackTrace();
		}
		// Si le serveur est arrêté, on arrête les clients et on ferme les entrées/sorties
		try {
			if( socketServeur != null ){
				socketServeur.close();
			}
			for(int i = 0; i < listeClients.size(); ++i) {
				listeClients.get(i).close();
			}
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
	public synchronized void supprimeJoueur( int id ) {
		
		// Scan la liste jusqu'à trouver l'id
		for(int i = 0; i < listeClients.size(); ++i) {
			Client ct = listeClients.get(i);
			
			// Trouvé
			if( ct != null && ct.getID() == id) {
				listeClients.remove(i);
				return;
			}
		}
	}
}
