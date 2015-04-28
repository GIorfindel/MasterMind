package serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

import client.Paquet;

import mastermind.Joueur;

public class Client extends Thread {
	private Socket socket;
	private ObjectInputStream sInput;
	private ObjectOutputStream sOutput;
	private Joueur joueur;
	private Serveur serveur;
	private int id;
	
	public Client( Socket socket, Serveur serveur ) {
		this.socket = socket;
		this.serveur = serveur;
		this.id = Serveur.ID ++;
		try{
			// Création de la sortie en premier
			this.sOutput = new ObjectOutputStream(socket.getOutputStream());
			this.sInput  = new ObjectInputStream(socket.getInputStream());
			this.joueur = null;
		}catch (IOException e) {
			this.close();
			System.out.println( "Impossible de se connecter avec:" + joueur.getIdentifiant() );
			e.printStackTrace();
		}
	}
	
	public int getID(){
		return this.id;
	}
	
	public void close(){
		//Envoyer au joeur deconexion
		this.stop();
		try{
			this.sOutput.close();
			this.sInput.close();
			this.socket.close();
			this.serveur.supprimeJoueur( this.id );
		}catch( Exception e ){
			//Impossible de fermer le flux, c'est genant !!!
			e.printStackTrace();
		}
	}
	
	public void demandeDeConnection( Paquet paquet ){
		//On extrait l'identifiant et le mot de passe du paquet
		//ON regarde si il existe dans la base de donnée
		//On regarde si il est pas déjà connecter
		//Si tous ses parametre(ci-dessus) sont OK, on envoie au joueur connection ok
	}
	
	public void gererPaquet( Paquet paquet ){
		switch ( paquet.getType() ) {
		case Paquet.DEMANDE_CONNECTION:
			this.demandeDeConnection( paquet );
			break;
		 }
	}
	
	public void run() {
		Paquet p = null;
		try{
			while( true ){
				p = (Paquet) this.sInput.readObject();
				this.gererPaquet( p );
			}
		}catch(IOException e) {
			this.close();
			e.printStackTrace();
			return;
		}
		catch(ClassNotFoundException e) {
			this.close();
			e.printStackTrace();
			return;
		}
	}
}
