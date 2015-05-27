package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import mastermind.Multijoueur;
import mastermind.Niveau;
import mastermind.Paquet;

import bdd.DB;


public class Serveur {
	private ArrayList<Client> listeClients;
	private int port;
	private boolean enCours;
	public static int ID = 0;
	private DB db;
	private Commande comm;
	private ServerSocket socketServeur;
	private static int MAX_CLIENT = 10;
	
	private ArrayList<Client> partiesMulti;
	
	public Serveur( int port ){
		this.port = port;
		this.listeClients = new ArrayList<Client>();
		this.partiesMulti = new ArrayList<Client>();
		this.enCours = false;
		this.comm = null;
		this.db = null;
		this.socketServeur = null;
	}
	
	private void initDB(){
		 this.db = new DB( "localhost", "ben", "ben", "mastermind" ); 
		 this.db.connexion();
	}
	
	public DB getBD(){
		return this.db;
	}
	
	public synchronized void afficher( String message ){
		System.out.println( message );
	}
	
	public void start(){
		this.enCours = true;
		this.initDB();
		this.afficher( "Le serveur demarre, il ecoute sur le port :" + this.port );
		this.comm = new Commande( this );
		this.comm.start();
		try {
			this.socketServeur = new ServerSocket( this.port );
		}catch (IOException e) {
			this.afficher( "Problème lors de la connection au port " + this.port );
			e.printStackTrace();
			return;
		}
		try{
			while( this.enCours ){
				if( this.listeClients.size() < MAX_CLIENT ){
					if( this.socketServeur != null ){
						Socket socket = this.socketServeur.accept();  	// Accepte la connexion, cette methode est bloquante
						Client c = new Client( socket, this );  // crée un Thread pour chaque client connecté
						listeClients.add( c ); // Enregistre le client dans la liste
						c.start(); // démarre le Thread
					}
				}
			}
		}catch (IOException e) {//Si on arrete le serveur et ba il lance cette exception (Socket closed), mais je pense que le serveur s'arrête correctement
			if( this.enCours ){
				this.afficher( "Problème lors de l'écoute du port " + this.port );
				e.printStackTrace();
			}
		}
		
	}
	
	public void close(){
		this.enCours = false;
		this.envoyerAtousServeurClose();
		try {
			this.db.deconnexion();
		} catch (SQLException e) {
			this.afficher( "Impossible de se déconnecté de la bdd" );
			e.printStackTrace();
		}
		try{
			if( this.socketServeur != null ){
				this.socketServeur.close();
				this.socketServeur = null;
			}
			this.supprimeTousJoueur();
			this.afficher( "Arret du serveur" );
		}catch(IOException e){
			this.afficher( "Problème lors de l'arret du serveur" );
			e.printStackTrace();
		}
	}
	
	public void envoyerAtousServeurClose(){
		for(int i = 0; i < this.listeClients.size(); ++i) {
			Client ct = this.listeClients.get(i);
			if( ct != null) {
				this.listeClients.get( i ).envoyerPaquet( Paquet.creeSERVEUR_ETEINT() );
			}
		}
	}
	
	public void supprimeTousJoueur(){
		for(int i = 0; i < this.listeClients.size(); ++i) {
			Client ct = this.listeClients.get(i);
			if( ct != null) {
				ct.close();
			}
		}
	}
	
	public synchronized void supprimeJoueur( int id ) {
		for(int i = 0; i < this.listeClients.size(); ++i) {
			Client ct = this.listeClients.get(i);
			if( ct != null && ct.getID() == id) {
				this.afficher( "Le client " + ct.getID() + " est supprimé" );
				listeClients.remove(i);
				return;
			}
		}
	}
	
	public void addPartieMulti( Client client ){
		this.partiesMulti.add(client);
	}
	
	public void popPartieMulti( String nomPartie ){
		for( int i=0; i< this.partiesMulti.size(); i++ ){
			if( this.partiesMulti.get(i).getMulti().getNom().equals(nomPartie) ){
				this.partiesMulti.remove(i);
			}
		}
	}
	
	public Client addJoueur2( String nom_partie, Client cJoueur2 ){
		Client c;
		for( int i=0; i<this.partiesMulti.size();i++ ){
			c = this.partiesMulti.get(i);
			if( c.getMulti() != null && c.getMulti().getNom().equals(nom_partie) && c.getMulti().getEtat() == Multijoueur.ETAT_CHERCHE_JOUEUR2 ){
				c.addJoueur2( cJoueur2 );
				return c;
			}
		}
		return null;
	}
	
	public Paquet listeParties( int id_paquet ){
		ArrayList<Multijoueur> parties = new ArrayList<Multijoueur>();
		for( int i=0; i< this.partiesMulti.size(); i++ ){
			if( this.partiesMulti.get(i).getMulti().getEtat() == Multijoueur.ETAT_ATTENTE_JOUER ){
				parties.add( this.partiesMulti.get(i).getMulti() );
			}
		}
		return Paquet.creeREPONSE_LISTE_PARTIES(parties, id_paquet);
	}
}

