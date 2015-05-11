package client;

import mastermind.Joueur;
import mastermind.Paquet;

public class Client {
	
	//Ca communqiue avec le serveur
	private Serveur serveur;
	private Joueur joueur;
	
	//Nécessaire pour les paquets
	public static int ID_ACTUEL = 0;
	
	public Client( String addr_serveur, int port_serveur ){
		this.joueur = null;
		this.serveur = new Serveur( addr_serveur, port_serveur, this );
	}
	
	//Peut renvoyer null si le client n'est pas connecté à son compte
	public Joueur getJoueur(){
		return this.joueur;
	}
		
	public void setJoueur( Joueur j ){
		this.joueur = j;
	}
	
	public void close(){
		if( this.getConnecteAuServeur() ){
			this.serveur.envoyerPaquet( Paquet.creeJEMEDECO() );
		}
		this.serveur.close();
		this.joueur = null;
	}
	
	//Retourne true si on est connecté au serveur
	public boolean getConnecteAuServeur(){
		return this.serveur.getConnecte();
	}
	
	//Retourne true si on est connecté au à notre compte
	public boolean connecterAuCompte(){
		return this.getConnecteAuServeur() && this.joueur != null;
	}
	
	//Retourne true si on arrvie à se connecter au serveur
	public boolean seConnecterAuServeur(){
		if( !this.getConnecteAuServeur() ){
			return this.serveur.connectionAuServeur();
		}
		return true;
	}
	
	public void closeServeur(){//Connexion au serveur perdu
		this.joueur = null;
		this.serveur.close();
	}
	
	//Envoi un paquet au serveur
	public boolean envoyerPaquet( Paquet p ){
		return this.serveur.envoyerPaquet( p );
	}
	
	//Attend un paquet du serveur, avec une limite max de temps en seconde. L'id_paquet est dans paquet.getId()
	public Paquet recevoirPaquet( double limite_temp_max, int id_paquet ){
		return this.serveur.getAttentPaquet( limite_temp_max, id_paquet );
	}
}
