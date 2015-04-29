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
	
	public Serveur( int port ){
		this.port = port;
		this.listeClients = new ArrayList<Client>();
		this.enCours =false;
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
		ServerSocket socketServeur = null;
		System.out.println( "Le serveur demarre, il ecoute le port :" + this.port );
		try {
			socketServeur = new ServerSocket( this.port );
			while( enCours ){
				Socket socket = socketServeur.accept();  	// Accepte la connexion, cette methode est bloquante
				System.out.println( "Un client vient de se connecter" );
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
			this.db.deconnexion();
			if( socketServeur != null ){
				socketServeur.close();
			}
			for(int i = 0; i < listeClients.size(); ++i) {
				listeClients.get(i).close();
			}
		}catch( SQLException e ) {
			System.out.println( "Impossible de fermer la connexion à la base de donnée" );
			e.printStackTrace();
		}catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
	public synchronized void supprimeJoueur( int id ) {
		
		// Scan la liste jusqu'à trouver l'id
		for(int i = 0; i < listeClients.size(); ++i) {
			Client ct = listeClients.get(i);
			
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
