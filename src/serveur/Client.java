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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import mastermind.Joueur;
import mastermind.Multijoueur;
import mastermind.Niveau;
import mastermind.Paquet;
import mastermind.Pions;
import mastermind.Score;
import mastermind.Solo;


public class Client extends Thread {
	private Socket socket;
	private ObjectInputStream recoi;
	private ObjectOutputStream envoi;
	private Joueur joueur;
	private Serveur serveur;
	private int id;
	private boolean continuer;
	
	private Multijoueur multi;//Il y a que le joueur1(createur de la partie) qui a cette variable initialisé.Le joueur2 cette variable est à null
	private Client cJoueur2;//Si il n'est pas null ca veut dire qu'on est le joueur1(Celui qu'a créé la partie)
	private Client cJoueur1;//Si il n'est pas null ca veut dire qu'on est le joueur2(Celui qu'a rejoins la partie)
	private CompteurMulti cmptMulti;
	
	public Client( Socket socket, Serveur serveur ) {
		this.socket = socket;
		this.serveur = serveur;
		this.id = Serveur.ID ++;
		this.continuer = false;
		this.joueur = null;
		this.envoi = null;
		this.recoi = null;
		this.serveur.afficher( "Nouveau client: " + this.id );
		this.multi = null;
		this.cJoueur2 = null;
		this.cJoueur1 = null;
		this.cmptMulti = null;
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
		if( this.multi != null ){//On a créé une partie
			this.demandeJoueur1Parti();
		}else if( this.cJoueur1 != null ){//On a rejoins une partie
			this.demandeJoueur2Parti();
		}
		this.continuer = false;
		this.multi = null;
		this.cJoueur2 = null;
		this.cJoueur1 = null;
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
		case Paquet.DEMANDE_NOUV_SCORE:
			this.demandeNouvScore( paquet );
			break;
		case Paquet.DEMANDE_STATS:
			this.demandeStats( paquet );
			break;
		case Paquet.DEMANDE_CLASSEMENT:
			this.demandeClassement ( paquet );
			break;
		case Paquet.DEMANDE_CREE_MULTI:
			this.demandeCreeMulti( paquet );
			break;
		case Paquet.DEMANDE_LISTE_PARTIES:
			this.demandeListeParties( paquet );
			break;
		case Paquet.DEMANDE_NOUV_JOUEUR2:
			this.demandeNouvJoueur2Multi( paquet );
			break;
		case Paquet.DEMANDE_KICKER_JOUEUR2:
			this.demandeKickerJoueur2( paquet );
			break;
		case Paquet.DEMANDE_JOUEUR2_PARTI:
			this.demandeJoueur2Parti();
			break;
		case Paquet.DEMANDE_JOUEUR1_PARTI:
			this.demandeJoueur1Parti();
			break;
		case Paquet.DEMANDE_JOUER_MULTI:
			this.demandeJouerMulti( paquet );
			break;
		case Paquet.DEMANDE_ENVOI_COMB:
			this.demandeEnvoiComb( paquet );
			break;
		case Paquet.DEMANDE_CHOISIR_QUI_COMMENCE:
			this.demandeChoisirQuiComm();
			break;
		case Paquet.DEMANDE_TOUR_SUIVANT:
			this.switchTour();
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
			ImageIO.write( this.getRenderedImage(avatar), "png", new File( "avatar/"+this.joueur.getIdentifiant()+".png" ) );
			this.serveur.getBD().modifieAvatar( this.joueur );
		} catch (IOException e) {
			this.serveur.afficher("Imposssible de sauvegarder l'avatar");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch( Exception e ){
			this.serveur.afficher("Imposssible de sauvegarder l'avatar");
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
		try {
			Solo s = this.serveur.getBD().chargerSolo( this.joueur );
			this.envoyerPaquet( Paquet.creeREPONSE_CHARGER_SOLO( s, p.getId() ) );
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void demandeNouvScore( Paquet p ){
		try {
			Score score = (Score) p.getObjet(0);
			this.serveur.getBD().nouvScore( this.joueur, score );
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void demandeStats( Paquet p ){
		try {
			String login = (String) p.getObjet(0);
			Integer joues = new Integer(this.serveur.getBD().StatJoues(login, "solo"));
			Integer coups = new Integer(this.serveur.getBD().StatCoups(login, "solo"));
			Integer gagnes = new Integer(this.serveur.getBD().StatGagnes(login, "solo"));
			Integer jouem = new Integer(this.serveur.getBD().StatJoues(login, "multi"));
			Integer coupm = new Integer(this.serveur.getBD().StatCoups(login, "multi"));
			Integer gagnem = new Integer(this.serveur.getBD().StatGagnes(login, "multi"));
			Object[] stats = {joues, gagnes, coups, jouem, gagnem, coupm};
			
			this.envoyerPaquet( Paquet.creeREPONSE_STATS( stats, p.getId() ) );
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void demandeClassement ( Paquet p)
	{
		try {
			Object[] joueurs = this.serveur.getBD().getJoueurs();
			this.envoyerPaquet( Paquet.creeREPONSE_CLASSEMENT( joueurs, p.getId() ) );
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void demandeCreeMulti( Paquet p ){
		Niveau n = (Niveau) p.getObjet(0);
		this.multi = new Multijoueur(this.joueur.getIdentifiant(), n, this.joueur);
		this.serveur.addPartieMulti( this );
	}
	
	public void demandeListeParties( Paquet p ){
		this.envoyerPaquet( this.serveur.listeParties(p.getId()) );
	}
	
	public Multijoueur getMulti(){
		return this.multi;
	}
	
	public Joueur getJoueur(){
		return this.joueur;
	}
	
	public Client getCJoueur2(){
		return this.cJoueur2;
	}
	
	public void demandeNouvJoueur2Multi( Paquet p ){
		String nom_partie = (String) p.getObjet(0);
		this.cJoueur1 = this.serveur.addJoueur2( nom_partie, this );
		if( this.cJoueur1 != null ){
			this.envoyerPaquet( Paquet.creeREPONSE_NOUV_JOUEUR2( this.cJoueur1.getJoueur(), this.cJoueur1.getMulti().getNiveau(),  p.getId() ) );
		}else{
			//Il n'a pas trouvé la partie ou Partie pleine
			this.envoyerPaquet( Paquet.creeREPONSE_NOUV_JOUEUR2( null, null, p.getId() ) );
		}
	}
	
	public void addJoueur2( Client cJoueur2 ){
		this.cJoueur2 = cJoueur2;
		this.multi.setJoueur2(cJoueur2.getJoueur());
		this.envoyerPaquet( Paquet.creeNOUV_JOUEUR2( this.cJoueur2.getJoueur() ) );
	}
	
	public void demandeKickerJoueur2( Paquet p ){
		if( this.cJoueur2 != null ){
			this.cJoueur2.envoyerPaquet( Paquet.creeTU_ES_KICK() );
			this.multi.kickJoueur2();
			this.cJoueur2.kick();
			this.cJoueur2 = null;
		}
	}
	
	public void kick(){
		this.cJoueur1 = null;
	}
	
	public void demandeJoueur2Parti(){
		if(this.cJoueur1 != null){
			this.cJoueur1.joueur2Parti();
			this.cJoueur1 = null;
		}
	}
	
	public void joueur2Parti(){
		if( this.multi == null ){
			return;
		}
		if( this.multi.getEtat() == Multijoueur.ETAT_ATTENTE_JOUER ){
			this.multi.kickJoueur2();
			this.envoyerPaquet( Paquet.creeJOUEUR2_PARTI() );
			this.cJoueur2 = null;
		} else if( this.multi.getEtat() == Multijoueur.ETAT_CHOISIT_COMB_A_DEVINER_COMPT_1 ||  this.multi.getEtat() == Multijoueur.ETAT_CHOISIT_COMB_A_DEVINER_COMPT_2 ){
			this.cmptMulti.close();
			this.multi.reset();
			this.envoyerPaquet( Paquet.creeJOUEUR2_PARTI() );
			try {
				this.serveur.getBD().addMalus( this.cJoueur2.joueur );
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.cJoueur2 = null;
		}else if( this.multi.getEtat() == Multijoueur.ETAT_COMB_FIXE || this.multi.getEtat() == Multijoueur.TOUR_SUIVANT || this.multi.getEtat() == Multijoueur.CHOISIT_QUI_COMM ){
			this.multi.reset();
			this.envoyerPaquet( Paquet.creeJOUEUR2_PARTI() );
			try {
				this.serveur.getBD().addMalus( this.cJoueur2.joueur );
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.cJoueur2 = null;
		}
	}
		
	public void demandeJoueur1Parti(){
		if( this.multi == null ){
			return;
		}
		if( this.multi.getEtat() == Multijoueur.ETAT_CHERCHE_JOUEUR2 ){
			this.serveur.popPartieMulti( this.multi.getNom() );
			this.multi = null;
		}else if( this.multi.getEtat() == Multijoueur.ETAT_ATTENTE_JOUER ){
			this.cJoueur2.joueur1Parti();
			this.cJoueur2 = null;
			this.serveur.popPartieMulti( this.multi.getNom() );
			this.multi = null;
		} else if( this.multi.getEtat() == Multijoueur.ETAT_CHOISIT_COMB_A_DEVINER_COMPT_1 ||  this.multi.getEtat() == Multijoueur.ETAT_CHOISIT_COMB_A_DEVINER_COMPT_2 ){
			this.cmptMulti.close();
			this.multi.reset();
			if( this.cJoueur2 != null ){
				this.cJoueur2.joueur1Parti();
				this.cJoueur2 = null;
			}
			this.serveur.popPartieMulti( this.multi.getNom() );
			this.multi = null;
			try {
				this.serveur.getBD().addMalus( this.joueur );
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if( this.multi.getEtat() == Multijoueur.ETAT_COMB_FIXE || this.multi.getEtat() == Multijoueur.TOUR_SUIVANT || this.multi.getEtat() == Multijoueur.CHOISIT_QUI_COMM ){
			this.multi.reset();
			if( this.cJoueur2 != null ){
				this.cJoueur2.joueur1Parti();
				this.cJoueur2 = null;
			}
			this.serveur.popPartieMulti( this.multi.getNom() );
			this.multi = null;
			try {
				this.serveur.getBD().addMalus( this.joueur );
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void joueur1Parti(){
		this.cJoueur1 = null;
		this.envoyerPaquet( Paquet.creeJOUEUR1_PARTI() );
	}
	
	//C'est le créateur qui demande à commencer à jouer(dans la page AttenteJoueur)
	public void demandeJouerMulti( Paquet p ){
		this.cJoueur2.envoyerPaquet( Paquet.creePARTIE_LANCER() );
		this.multi.commencerPartie();
		
	}
	
	public void demandeChoisirQuiComm(){
		this.multi.demandeChoisitQuiComm();
		this.choisirCombAtrouve();
	}
	
	//Choisir la combinaison à trouvé
	private void choisirCombAtrouve(){
		if( this.multi.getTourDeCreateur() ){
			this.envoyerPaquet( Paquet.creeCHOISIT_COMB_A_DEVINER() );
			this.cJoueur2.envoyerPaquet( Paquet.creeCHOISITPAS_COMB_A_DEVINER() );
		}else{
			this.cJoueur2.envoyerPaquet( Paquet.creeCHOISIT_COMB_A_DEVINER() );
			this.envoyerPaquet( Paquet.creeCHOISITPAS_COMB_A_DEVINER() );
		}
		this.cmptMulti = new CompteurMulti( this, CompteurMulti.COMPTEUR_1);
		this.cmptMulti.start();
	}
	
	//Quand les 60 secondes sont passés (le 1er compteur)
	public void compteur1TempsAtteint(){
		if( this.multi.getEtat() == Multijoueur.ETAT_CHOISIT_COMB_A_DEVINER_COMPT_1 ){
			if( this.multi.getTourDeCreateur() ){
				this.envoyerPaquet( Paquet.creeCOMPTEUR1_RATE() );
				this.cJoueur2.envoyerPaquet( Paquet.creeCOMPTEUR1_RATE_ADVER() );
			}else{
				this.cJoueur2.envoyerPaquet( Paquet.creeCOMPTEUR1_RATE() );
				this.envoyerPaquet( Paquet.creeCOMPTEUR1_RATE_ADVER() );
			}
			this.multi.compteur1Ecoule();
			this.cmptMulti = new CompteurMulti( this, CompteurMulti.COMPTEUR_2);
			this.cmptMulti.start();
		}
	}
	
	//Pour le compteur2 10 secondes se sont écoulé
	public void compteur2AjouteCoups(){
		if( this.multi.getTourDeCreateur() ){
			this.multi.addCoupsJ1();
			this.envoyerPaquet( Paquet.creePERDU1_COUP_CMPT2() );
			this.cJoueur2.envoyerPaquet( Paquet.creeADV_PERDU1_COUP_CMPT2() );
		}else{
			this.multi.addCoupsJ2();
			this.cJoueur2.envoyerPaquet( Paquet.creePERDU1_COUP_CMPT2() );
			this.envoyerPaquet( Paquet.creeADV_PERDU1_COUP_CMPT2() );
		}
	}
	
	//Les 60 secondes sont passé pour le compteur2
	public void compteur2TempsAtteint(){
		if( this.multi.getTourDeCreateur() ){
			try {
				this.serveur.getBD().addMalus( this.joueur );
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.envoyerPaquet( Paquet.creePERDU_CMPT2() );
			this.cJoueur2.envoyerPaquet( Paquet.creeADV_PERDU_CMPT2() );
		}else{
			try {
				this.serveur.getBD().addMalus( this.cJoueur2.joueur );
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.cJoueur2.envoyerPaquet( Paquet.creePERDU_CMPT2() );
			this.envoyerPaquet( Paquet.creeADV_PERDU_CMPT2() );
		}
		this.multi.reset();
		if( this.cJoueur2 != null ){
			this.cJoueur2.cJoueur1 = null;
			this.cJoueur2 = null;
		}
		this.serveur.popPartieMulti( this.multi.getNom() );
		this.multi = null;
	}
	
	public void demandeEnvoiComb( Paquet p ){
		if( this.cJoueur2 == null ){
			this.cJoueur1.demandeEnvoiComb(p);
			return;
		}
		Pions pions = (Pions) p.getObjet(0);
		int etat = this.multi.getEtat(); 
		if( etat == Multijoueur.ETAT_CHOISIT_COMB_A_DEVINER_COMPT_1 || etat ==  Multijoueur.ETAT_CHOISIT_COMB_A_DEVINER_COMPT_2 ){
			this.multi.setComb(pions);
			this.cmptMulti.close();
			if( this.multi.getTourDeCreateur() ){
				this.cJoueur2.envoyerPaquet( Paquet.creeCOMB_FIXE(pions) );
			}else{
				this.envoyerPaquet( Paquet.creeCOMB_FIXE(pions) );
			}
		}else if( etat == Multijoueur.ETAT_COMB_FIXE ){
			this.envoiPionsJoueurAdversse( pions );
			this.addCoupsActuel();
			this.multi.addCoupsTour();
			if( this.getCoupsJActuel() == 100 ){
				this.partiFini();
				return;
			}
			if( pions.equals(this.multi.getComb()) ){
				this.envoyerPaquet( Paquet.creeCOMB_TROUVE() );
				this.cJoueur2.envoyerPaquet( Paquet.creeCOMB_TROUVE() );
				return;
			}else{
				if( this.multi.getCoupTour() == this.multi.getNiveau().getCoupMax() ){
					this.partiFini();
				}else{
					this.reEssaye();
				}
			}
		}
	}
	
	public void envoiPionsJoueurAdversse( Pions p ){
		if( this.multi.getTourDeCreateur() ){
			this.envoyerPaquet( Paquet.creeENVOI_ESSAI_ADV(p) );
		}else{
			this.cJoueur2.envoyerPaquet( Paquet.creeENVOI_ESSAI_ADV(p) );
		}
	}
	
	public int getCoupsJActuel(){
		if( this.multi.getTourDeCreateur() ){
			return this.multi.getCoupsJ2();
		}else{
			return this.multi.getCoupsJ1();
		}
	}
	
	public void addCoupsActuel(){
		if( this.multi.getTourDeCreateur() ){
			this.multi.addCoupsJ2();
		}else{
			this.multi.addCoupsJ1();
		}
	}
	
	public void partiFini(){
		if( this.multi.getTourDeCreateur() ){
			this.envoyerPaquet( Paquet.creeTU_AS_GAGNE() );
			this.cJoueur2.envoyerPaquet( Paquet.creeTU_AS_PERDU() );
			try {
				this.serveur.getBD().nouvScore(this.joueur, new Score(Score.MODE_MULTI,this.multi.getCoupsJ1(),true,this.multi.getNbTour(),this.multi.getNiveau()));
				this.serveur.getBD().nouvScore(this.cJoueur2.getJoueur(), new Score(Score.MODE_MULTI,this.multi.getCoupsJ2(),false,this.multi.getNbTour(),this.multi.getNiveau()));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.cJoueur2.envoyerPaquet( Paquet.creeTU_AS_GAGNE() );
			this.envoyerPaquet( Paquet.creeTU_AS_PERDU() );
			try {
				this.serveur.getBD().nouvScore(this.joueur, new Score(Score.MODE_MULTI,this.multi.getCoupsJ1(),false,this.multi.getNbTour(),this.multi.getNiveau()));
				this.serveur.getBD().nouvScore(this.cJoueur2.getJoueur(), new Score(Score.MODE_MULTI,this.multi.getCoupsJ2(),true,this.multi.getNbTour(),this.multi.getNiveau()));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.multi.reset();
		if( this.cJoueur2 != null ){
			this.cJoueur2.cJoueur1 = null;
			this.cJoueur2 = null;
		}
		this.serveur.popPartieMulti( this.multi.getNom() );
		this.multi = null;
	}
	
	public void reEssaye(){
		if( this.multi.getTourDeCreateur() ){
			this.cJoueur2.envoyerPaquet( Paquet.creeCHOISI_ESSAI() );
		}else{
			this.envoyerPaquet( Paquet.creeCHOISI_ESSAI() );
		}
	}
	
	public void switchTour(){
		this.multi.switchTourDe();
		this.choisirCombAtrouve();
	}
	
	
}
