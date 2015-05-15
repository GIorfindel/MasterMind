package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import mastermind.Paquet;


public class Serveur {
	private String addr_serveur;
	private int port_serveur;
	//La connection avec le serveur
	private Socket socket;
	//Un thread qui ecoute le serveur
	private EcouteServeur ecouteServeur;
	//Pour envoyer des données au serveur
	private ObjectOutputStream envoi;
	private Client client;
	//Si on est connecté au serveur ou pas
	private boolean connecte;

	public Serveur( String addr_serveur, int port_serveur, Client client ){
		this.addr_serveur = addr_serveur;
		this.port_serveur = port_serveur;
		this.socket = null;
		this.envoi = null;
		this.connecte = false;
		this.ecouteServeur = null;
		this.client = client;
	}

	public boolean getConnecte(){
		return this.connecte;
	}

	public synchronized boolean connectionAuServeur(){
		if( this.connecte ){
			return true;
		}
		//Ce que le serveur nous envoi
		ObjectInputStream recoi = null;
		try {


			InetAddress inet = InetAddress.getByName(this.addr_serveur);
			if ( !inet.isReachable(5000)){
				return false;
			}




			this.socket = new Socket( this.addr_serveur, this.port_serveur );
			this.connecte = true;
		}
		catch(Exception e) {//Impossible de se connecter au serveur
			this.connecte = false;
			//e.printStackTrace(); Car si le joueur n'a pas de connection internet, c'est normal qui ne peut pas se connecter
			return false;
		}
		try {
			recoi  = new ObjectInputStream( this.socket.getInputStream() );
			this.envoi = new ObjectOutputStream( this.socket.getOutputStream() );
		}
		catch (IOException e) {//Erreur en créant les flux
			this.connecte = false;
			this.close();
			e.printStackTrace();
			return false;
		}
		// Création du Thread à l'écoute du serveur
		this.ecouteServeur = new EcouteServeur( recoi, this.client );
		this.ecouteServeur.start();
		return true;
	}

	public boolean close(){
		this.connecte = false;
		if( this.ecouteServeur != null ){
			this.ecouteServeur.close();
			this.ecouteServeur = null;
		}
		try{
			if( this.envoi != null ){
				this.envoi.close();
				this.envoi = null;
			}
			if( this.socket != null ){
				this.socket.close();
				this.socket = null;
			}
			return true;
		}catch( Exception e ){
			e.printStackTrace();
		}
		return false;
	}

	public boolean envoyerPaquet( Paquet p ){
		try {
			if( this.envoi != null ){
				this.envoi.writeObject( p );
				return true;
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private double getTempsSeconde(){
		return System.currentTimeMillis()/1000.0;
	}

	//il y a une limite de temps(en seconde), si il y a pas de paquet
	//au bout de cette limite il retourne null
	public Paquet getAttentPaquet( double limite_temp_max, int id ){
		double temps_max = this.getTempsSeconde() + limite_temp_max;
		Paquet p = null;
		while( true ){
			if( this.ecouteServeur == null ){
				return null;
			}
			p = this.ecouteServeur.getPaquet( id );
			try {
				Thread.sleep( 200 );//Pour eviter de solliciter le EcouteServeur, sinon il fonctionne TRES mal
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if( p != null ){
				return p;
			}
			if( this.getTempsSeconde() > temps_max ){//Temps ecoule
				return null;
			}
		}
	}

}
