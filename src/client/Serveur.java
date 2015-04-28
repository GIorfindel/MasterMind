package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Serveur {
	//Si le client attent une reponse du serveur
	private boolean attent;
		
	private String addr_serveur;
	private int port_serveur;
	private Socket socket;
	private EcouteServeur ecouteServeur;
	private ObjectOutputStream sOutput;
		
	//Si le joueur est connecté au serveur
	private boolean connecter;
	
	public Serveur( String addr_serveur, int port_serveur ){
		this.addr_serveur = addr_serveur;
		this.port_serveur = port_serveur;
		this.socket = null;
		this.sOutput = null;
		this.connecter = false;
		this.attent = false;
		this.ecouteServeur = null;
	}
	
	public boolean getConnecter(){
		return this.connecter;
	}
	
	public void close(){
		try{
			this.connecter = false;
			if( this.socket != null ){
				this.socket.close();
			}
			if( this.sOutput != null ){
				this.sOutput.close();
			}
			this.attent = false;
			this.ecouteServeur.close();
		}catch( Exception e ){
			//Impossible de fermer les flux, c'est genant !!!
			e.printStackTrace();
		}
	}
	
	public boolean connectionAuServeur(){
		ObjectInputStream sInput = null;
		try {
			this.socket = new Socket( this.addr_serveur, this.port_serveur );
			this.connecter = true;
		} 
		catch(Exception e) {//Impossible de se connecter au serveur
			this.connecter = false;
			this.close();
			e.printStackTrace();
			return false;
		}
		try {
			sInput  = new ObjectInputStream( this.socket.getInputStream() );
			this.sOutput = new ObjectOutputStream( this.socket.getOutputStream() );
		}
		catch (IOException e) {//Erreur en créant les flux
			this.connecter = false;
			this.close();
			e.printStackTrace();
			return false;
		}
		// Création du Thread à l'écoute du serveur
		this.ecouteServeur = new EcouteServeur( sInput );
		this.ecouteServeur.start();
		return true;
	}
	
	public boolean envoyerPaquet( Paquet paquet, boolean attentReponse ){
		if( this.connecter ){
			try{
				this.sOutput.writeObject( paquet );
				this.attent = attentReponse;
				return true;
			}catch(IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public Paquet getPaquet(){
		if( this.connecter && this.ecouteServeur.getUneReponse() ){
			this.ecouteServeur.getReponseServeur();
		}
		return null;
	}
}
