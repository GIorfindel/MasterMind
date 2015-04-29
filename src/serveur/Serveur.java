package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import BDD.DB;

public class Serveur {
	private ArrayList<Client> listeClients;
	private int port;
	private boolean enCours;
	public static int ID = 0;
	private DB db;
	private Commande comm;
	private ServerSocket socketServeur;
	
	public Serveur( int port ){
		this.port = port;
		this.listeClients = new ArrayList<Client>();
		this.enCours = false;
		this.comm = null;
		this.socketServeur = null;
	}
	
	private void initDB(){
		this.db = new DB( "localhost", "ben", "ben", "mastermind" );
		this.db.connexion();
	}
	
	public DB getBD(){
		return this.db;
	}
	
	public void start(){
		this.enCours = true;
		this.initDB();
		System.out.println( "Le serveur demarre, il ecoute le port :" + this.port );
		this.comm = new Commande( this );
		this.comm.start();
		try {
			this.socketServeur = new ServerSocket( this.port );
			while( this.enCours ){
				Socket socket = this.socketServeur.accept();  	// Accepte la connexion, cette methode est bloquante
				System.out.println( "Un client vient de se connecter" );
				Client c = new Client( socket, this );  // crée un Thread pour chaque client connecté
				listeClients.add( c ); // Enregistre le client dans la liste
				c.start(); // démarre le Thread
			}
		// Erreur innatendue
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Arret du serveur");
	}
	
	public void close(){
		this.enCours = false;
		try{
			this.socketServeur.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		this.supprimeTousJoueur();
	}
	
	public void supprimeTousJoueur(){
		for(int i = 0; i < this.listeClients.size(); ++i) {
			Client ct = this.listeClients.get(i);
			if( ct != null) {
				ct.close();
				return;
			}
		}
	}
	
	public synchronized void supprimeJoueur( int id ) {
		
		// Scan la liste jusqu'à trouver l'id
		for(int i = 0; i < this.listeClients.size(); ++i) {
			Client ct = this.listeClients.get(i);
			
			// Trouvé
			if( ct != null && ct.getID() == id) {
				if( ct.getJoueur() != null ){
					System.out.println( ct.getJoueur().getIdentifiant() + " c'est déconnecté" );
				}else{
					System.out.println("Un client c'est déconnecté");
				}
				listeClients.remove(i);
				return;
			}
		}
	}
}
