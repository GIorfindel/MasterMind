package serveur;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import mastermind.Joueur;
import mastermind.Paquet;
import mastermind.Solo;


public class Client extends Thread {
	private Socket socket;
	private ObjectInputStream recoi;
	private ObjectOutputStream envoi;
	private Joueur joueur;
	private Serveur serveur;
	private int id;
	private boolean continuer;
	
	public Client( Socket socket, Serveur serveur ) {
		this.socket = socket;
		this.serveur = serveur;
		this.id = Serveur.ID ++;
		this.continuer = false;
		this.joueur = null;
		this.envoi = null;
		this.recoi = null;
		this.serveur.afficher( "Nouveau client: " + this.id );
	}
	
	
	public void run() {
		try{
			// Création de la sortie en premier
			this.envoi = new ObjectOutputStream( this.socket.getOutputStream() );
			this.recoi  = new ObjectInputStream( this.socket.getInputStream() );
		}catch (IOException e) {
			this.serveur.afficher( "Impossible de charger les flux du client " + this.id );
			e.printStackTrace();
			return;
		}
		Paquet p = null;
		this.continuer = true;
			while( this.continuer ){
				try{
					if( this.recoi != null ){
						p = (Paquet) this.recoi.readObject();
						this.gererPaquet( p );
					}
				}catch( EOFException e ){//Le client s'arrete brutalement
					this.close();
				}catch( SocketException e ){//Le client s'arrete brutalement
					this.close();
				}catch( IOException e ) {
					if( this.continuer ){
						this.serveur.afficher( "Probleme de readObject du client " + this.id );
						e.printStackTrace();
					}
					this.close();
				}
				catch( ClassNotFoundException e ) {
					e.printStackTrace();
					this.serveur.afficher( "Le client " + this.id + " n'as pas envoyé une Classe Paquet" );
					this.close();
				}
			}
	}
	
	
	public void close(){
		this.continuer = false;
		try{
			if( this.envoi != null ){
				this.envoi.close();
				this.envoi = null;
			}
			if( this.recoi != null ){
				this.recoi.close();
				this.recoi = null;
			}
			if( this.socket != null ){
				this.socket.close();
				this.socket = null;
			}
			this.serveur.supprimeJoueur( this.id );
			this.joueur = null;
			this.serveur.afficher( "Client " + this.id +" est fermé" );
		}catch( IOException e ){
			this.serveur.afficher( "Impossible de fermer le client " + this.id );
			e.printStackTrace();
		}
	}
	
	public synchronized void envoyerPaquet( Paquet p ){
		if( this.envoi != null ){
			try {
				this.envoi.writeObject( p );
			} catch (IOException e) {
				this.serveur.afficher( "Impossible d'envoyer un paquet de type :" + p.getType() + ", à " + this.id );
				e.printStackTrace();
			}
		}
	}
	
	public int getID(){
		return this.id;
	}
	
	public void gererPaquet( Paquet paquet ){
		switch ( paquet.getType() ) {
		case Paquet.JEMEDECO:
			this.close();
			break;
		case Paquet.DEMANDE_CONNECTION:
			this.demandeDeConnection( paquet );
			break;
		case Paquet.MODIFI_AVATAR:
			this.modifieAvatar( paquet );
			break;
		case Paquet.DEMANDE_MAJ_JOUEUR:
			this.MAJJoueur( paquet );
			break;
		case Paquet.DEMANDE_INSCRIPTION:
			this.demandeInscription( paquet );
			break;
		case Paquet.DEMANDE_SAVE_SOLO:
			this.demandeSaveSolo( paquet );
			break;
		case Paquet.DEMANDE_CHARGER_SOLO:
			this.demandeChargerSolo( paquet );
			break;
		 }
	}
	
	public void MAJJoueur( Paquet p ){
		Joueur j = null;
		if( this.joueur == null ){
			this.serveur.afficher( "Le client " + this.id + " re-demande son profil, mais son joueur est null" );
		}else{
		
			try{
				j = this.serveur.getBD().getJoueur( this.joueur.getIdentifiant(), this.joueur.getMDP() );
			}catch( SQLException e ){
				this.serveur.afficher( "Impossible de faire la mise à jour du joueur(" + this.id + ") : " + this.joueur.getIdentifiant() );
				e.printStackTrace();
				return;
			}
			if( j == null ){
				this.serveur.afficher( "Le client (" + this.id + ") " + this.joueur.getIdentifiant() + " re-demande son profil, mais la BD lui repond null" );
			}
		}
		this.envoyerPaquet( Paquet.creeREPONSE_MAJ_JOUEUR( j, p.getId() ) );
		
	}
	
	public void modifieAvatar( Paquet paquet ){
		if( this.joueur == null ){
			this.serveur.afficher( "Le client " + this.id + " veut changer son avatar, mais son joueur est null" );
			return;
		}
		ImageIcon avatar = (ImageIcon) paquet.getObjet( 0 );
		try {
			ImageIO.write( this.getRenderedImage(avatar), "png", new File( "images/avatar/" + this.joueur.getIdentifiant() + ".png" ) );
			this.serveur.getBD().modifieAvatar( this.joueur );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private RenderedImage getRenderedImage(ImageIcon in)
    {
		BufferedImage bImage = new BufferedImage(in.getImage().getWidth(null), in.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D bImageGraphics = bImage.createGraphics();
		bImageGraphics.drawImage(in.getImage(), null, null);
		RenderedImage rImage = (RenderedImage)bImage;
		return rImage;
    }
	
	public void demandeDeConnection( Paquet paquet ){
		//On extrait l'identifiant et le mot de passe du paquet
		String login = (String) paquet.getObjet(0);
		String mdp = (String) paquet.getObjet(1);
		this.serveur.afficher( login + ", client " + this.id + " fait une demande de connexion" );
		//ON regarde si il existe dans la base de donnée
		Joueur j = null;
		try{
			j = this.serveur.getBD().getJoueur( login, mdp );
		}catch( SQLException e ){
			this.serveur.afficher( "Impossible de charger le joueur(" + this.id + "): " + login );
			e.printStackTrace();
			return;
		}
		if( j != null ){
			this.joueur = j;
		}
		this.envoyerPaquet( Paquet.creeREPONSE_CONNECTION( j, paquet.getId() ) );
	}
	
	public void demandeInscription( Paquet paquet ){
		//On extrait l'identifiant et le mot de passe du paquet
		String login = (String) paquet.getObjet(0);
		String mdp = (String) paquet.getObjet(1);
		Joueur j = null;
		try{
			j = this.serveur.getBD().inscrireJoueur( login, mdp );
		}catch( SQLException e ){
			this.serveur.afficher( "Impossible d'inscrire le joueur(" + this.id + "): " + login );
			e.printStackTrace();
			return;
		}
		//Si j == null joueur existe deja, donc on l'ajoute pas dans la BDD
		this.joueur = j;
		this.envoyerPaquet( Paquet.creeREPONSE_INSCRIPTION( j, paquet.getId() ) );
	}
	
	public void demandeSaveSolo( Paquet paquet ){
		Solo s = Paquet.getSolo(paquet);
		
		System.out.println("nbTour: "+s.getNbTour() + "  nbCoupsTotau: "+s.getCoups()+"  Size Essais: " +s.getTour().getEssais().size());
		
		try {
			this.serveur.getBD().sauvegarderSolo( s );
		} catch (SQLException e) {
			this.serveur.afficher("Le client demande de sauver une partie solo, mais une erreur");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void demandeChargerSolo( Paquet p ){
		String nom = (String) p.getObjet( 0 );
		try {
			Solo s = this.serveur.getBD().chargerSolo( nom, this.joueur );
			this.envoyerPaquet( Paquet.creeREPONSE_CHARGER_SOLO( s, p.getId() ) );
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
