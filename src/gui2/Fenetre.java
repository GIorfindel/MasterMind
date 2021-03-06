package gui2;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mastermind.Joueur;
import mastermind.Niveau;
import mastermind.Pions;
import mastermind.Solo;

import client.Client;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {
	
	private Client client;
	private CardLayout cl;
	private JPanel content;
	
	public static final String ACCUEIL = "0", CONNEXION = "1", INSCRIPTION = "3", JOUER = "4", 
			UNJOUEUR = "5", DEUXJOUEURS = "6", REGLES = "7", PROFIL = "8", PERSONNALISER = "9",
			SOLO = "10", CREER="11", PERSONNALISERMULTI = "12", SCORE = "13", ATTENTEJOUEUR = "14", PARTIE_MULTI = "15", CLASSEMENT = "16";
	private Menu accueil, connexion, inscription, jouer, profil, unjoueur, deuxjoueurs, regles, personnaliser, solo, 
		creer, personnaliserMulti, score, attentejoueur, partieMulti, classement;
	private Menu menu_actuel;
	
	public Fenetre(){
		this.setTitle( "Mastermind" );
	    this.setSize( 960, 700 );
	    this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
	    this.setLocationRelativeTo( null );
	    this.setResizable( false );
	    
	    this.client = new Client( "localhost", 15000, this );
	    this.cl = new CardLayout();
	    this.content = new JPanel();
	    content.setLayout( cl );
	    this.initPanel();
	}
	
	public void dispose(){
		super.dispose();
		this.client.close();
	}
	
	public Client getClient(){
		return this.client;
	}
	
	public void showMenu( final String menu ){
		this.cl.show( this.content, menu);
		this.clicPanel( menu );
	}
	
	private void initPanel(){
		this.accueil = new Accueil( this );
		this.menu_actuel = this.accueil;
		this.connexion = new Connexion( this );
		this.inscription = new Inscription( this );
		this.jouer = new Jouer( this );
		this.profil = new Profil( this );
		this.unjoueur = new UnJoueur( this );
		this.deuxjoueurs = new DeuxJoueurs( this );
		this.regles = new Regles( this );
		this.personnaliser = new Personnaliser( this );
		this.solo = new MSolo( this );
		this.creer = new CreerPartie( this );
		this.personnaliserMulti = new PersonnaliserMulti( this );
		this.score = new MScore( this );
		this.attentejoueur = new AttenteJoueur( this );
		this.partieMulti = new PartieMulti( this );
		this.classement = new Classement (this);
		this.content.add( this.accueil, ACCUEIL );
		this.content.add( this.connexion, CONNEXION );
		this.content.add( this.inscription, INSCRIPTION );
		this.content.add( this.jouer, JOUER );
		this.content.add( this.profil, PROFIL );
		this.content.add( this.unjoueur, UNJOUEUR );
		this.content.add( this.deuxjoueurs, DEUXJOUEURS );
		this.content.add( this.regles, REGLES );
		this.content.add( this.personnaliser, PERSONNALISER );
		this.content.add( this.solo, SOLO );
		this.content.add( this.creer, CREER );
		this.content.add( this.personnaliserMulti, PERSONNALISERMULTI );
		this.content.add( this.score, SCORE );
		this.content.add( this.attentejoueur, ATTENTEJOUEUR );
		this.content.add( this.partieMulti, PARTIE_MULTI);
		this.content.add( this.classement, CLASSEMENT );
		
		this.getContentPane().add( this.content, BorderLayout.CENTER );
	    this.setVisible( true );
	}
	
	public void clicPanel( final String menu ){
		if( menu.equals( ACCUEIL ) ){
			this.accueil.clic();
			this.menu_actuel = this.accueil;
		}else if( menu.equals( CONNEXION ) ){
			this.connexion.clic();
			this.menu_actuel = this.connexion;
		}else if( menu.equals( INSCRIPTION ) ){
			this.inscription.clic();
			this.menu_actuel = this.inscription;
		}else if( menu.equals( JOUER ) ){
			this.jouer.clic();
			this.menu_actuel = this.jouer;
		}else if( menu.equals( PROFIL ) ){
			this.profil.clic();
			this.menu_actuel = this.profil;
		}else if( menu.equals( UNJOUEUR ) ){
			this.unjoueur.clic();
			this.menu_actuel = this.unjoueur;
		}else if( menu.equals( DEUXJOUEURS ) ){
			this.deuxjoueurs.clic();
			this.menu_actuel = this.deuxjoueurs;
		}else if( menu.equals( REGLES ) ){
			this.regles.clic();
			this.menu_actuel = this.regles;
		}else if( menu.equals( PERSONNALISER ) ){
			this.personnaliser.clic();
			this.menu_actuel = this.personnaliser;
		}else if( menu.equals( SOLO ) ){
			this.solo.clic();
			this.menu_actuel = this.solo;
		}else if( menu.equals( CREER ) ){
			this.creer.clic();
			this.menu_actuel = this.creer;
		}else if( menu.equals( PERSONNALISERMULTI ) ){
			this.personnaliserMulti.clic();
			this.menu_actuel = this.personnaliserMulti;
		}else if( menu.equals( SCORE ) ){
			this.score.clic();
			this.menu_actuel = this.score;
		}else if( menu.equals( ATTENTEJOUEUR ) ){
			this.attentejoueur.clic();
			this.menu_actuel = this.attentejoueur;
		}else if( menu.equals( PARTIE_MULTI ) ){
			this.partieMulti.clic();
			this.menu_actuel = this.partieMulti;
		}
		else if( menu.equals( CLASSEMENT ) ){
			this.classement.clic();
			this.menu_actuel = this.classement;
		}
	}
	
	public void decoServeur(){
		this.menu_actuel.decoServeur();
	}
	
	public void setNiveauSolo( Niveau niveau ){
		((MSolo) this.solo).setNiveau(niveau);
	}
	
	public void setSoloCharger(Solo s){
		((MSolo) this.solo).setSolo(s);
	}
	
	public void setInfoMultiAttente( Niveau n, Joueur j ){
		((AttenteJoueur) this.attentejoueur).setInfoMultiAttente(n,j);
	}
	
	public void setInfoPartieMulti( Niveau n, Joueur j, boolean createur ){
		((PartieMulti) this.partieMulti).setInfoPartieMulti(n,j, createur);
	}
	
	public void tuEsKick(){
		((AttenteJoueur) this.attentejoueur).tuEsKick();
	}
	
	public void joueur2Arrive( Joueur joueur ){
		((AttenteJoueur) this.attentejoueur).joueur2Arrive(joueur);
	}
	
	public void joueur2Pars(){
		this.menu_actuel.joueur2Pars();
	}
	
	public void joueur1Pars(){
		this.menu_actuel.joueur1Pars();
	}
	
	public void partieLancer(){
		((AttenteJoueur) this.attentejoueur).partieLancer();
	}
	
	public void choisitCombADeviner(){
		((PartieMulti) this.partieMulti).choisitCombADeviner();
	}
	
	public void choisitPasCombADeviner(){
		((PartieMulti) this.partieMulti).choisitPasCombADeviner();
	}
	
	public void compteur1Rate(){
		((PartieMulti) this.partieMulti).compteur1Rate();
	}
	
	public void compteur1RateAdv(){
		((PartieMulti) this.partieMulti).compteur1RateAdv();
	}

	public void perduCoupsCmpt2() {
		((PartieMulti) this.partieMulti).perduCoupsCmpt2();
	}

	public void advPerduCoupsCmpt2() {
		((PartieMulti) this.partieMulti).advPerduCoupsCmpt2();
	}
	
	public void perduCmpt2() {
		((PartieMulti) this.partieMulti).perduCmpt2();
	}

	public void advPerduCmpt2() {
		((PartieMulti) this.partieMulti).advPerduCmpt2();
	}
	
	public void combFixe(Pions comb) {
		((PartieMulti) this.partieMulti).combFixe(comb);
	}

	public void choisitEssai() {
		((PartieMulti) this.partieMulti).choisitEssai();
	}

	public void envoiEssaiAdv(Pions essai) {
		((PartieMulti) this.partieMulti).envoiEssaiAdv( essai );
	}

	public void tuAsGagne() {
		((PartieMulti) this.partieMulti).tuAsGagne();
	}

	public void tuAsPerdu() {
		((PartieMulti) this.partieMulti).tuAsPerdu();
	}

	public void combTrouve() {
		((PartieMulti) this.partieMulti).combTrouve();
	}
}
