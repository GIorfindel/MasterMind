package serveur;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
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
	
	public Joueur getJoueur(){
		return this.joueur;
	}
	
	public void close(){
		this.interrupt();
		//Envoyer au joueur deconexion
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
		String login = (String) paquet.getObjet(0);
		String mdp = (String) paquet.getObjet(1);
		System.out.println( login + " fait une demande de connection" );
		//ON regarde si il existe dans la base de donnée
		Joueur j = null;
		try{
			j = this.serveur.getBD().getJoueur( login, mdp );
		}catch( SQLException e ){
			System.out.println("Impossible de charger le joueur: " + login );
			e.printStackTrace();
		}
		Paquet p = null;
		if( j == null ){
			p = new Paquet( 0, Paquet.REPONSE_CONNECTION );
		}else{
			this.joueur = j;
			p = new Paquet( 1, Paquet.REPONSE_CONNECTION );
			p.addObjet( j );
		}
		try{
			this.sOutput.writeObject( p );
			System.out.println( "Un paquet a ete envoye a " + login );
		}catch( IOException e ){
			System.out.println("Impossible d'envoyer un paquet à :" + login );
			e.printStackTrace();
		}
	}
	
	public void gererPaquet( Paquet paquet ){
		switch ( paquet.getType() ) {
		case Paquet.DEMANDE_CONNECTION:
			this.demandeDeConnection( paquet );//objet[0] = identifiant, objet[1] = mot de passe
			break;
		 }
	}
	
	public void interrupt() {
        super.interrupt();
        try {
            this.sInput.close(); // Fermeture du flux si l'interruption n'a pas fonctionné.
        } catch (IOException e) {
        	e.printStackTrace();
        }
    } 
	
	public void run() {
		Paquet p = null;
		try{
			while( true ){
				p = (Paquet) this.sInput.readObject();
				this.gererPaquet( p );
			}
		}catch (InterruptedIOException e) { // Si l'interruption a été gérée correctement.
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return;
        }catch(IOException e) {//Joueur deconnecté
			this.close();
			return;
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
	}
}
