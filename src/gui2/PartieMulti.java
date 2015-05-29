package gui2;

import gui2.MSolo.ListenerBoutonColor;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mastermind.Couleur;
import mastermind.Joueur;
import mastermind.Niveau;
import mastermind.Paquet;
import mastermind.Pions;

@SuppressWarnings("serial")
public class PartieMulti extends Menu{
	
	private Fenetre fenetre;
	private Joueur joueur;
	private Niveau niveau;
	private boolean createur;
	
	private int nbCoupsJ1;//moi
	private int nbCoupsJ2;//adversaire
	private int nbTours;
	private int nbCoupsTour;
	
	private JLabel infoPartie;
	
	private Maquette maquette;
	
	private JPanel bouton;
	private ImageIcon rouge;
	private ImageIcon jaune;
	private ImageIcon vert;
	private ImageIcon bleu;
	private ImageIcon orange;
	private ImageIcon blanc;
	private ImageIcon violet;
	private ImageIcon cyan;
	private ImageIcon rose;
	private ImageIcon noir;
	
	private Pions essai;
	
	private Couleur[] couleursAutorise;
	
	private JButton valider;
	private JButton suivant;
	private JButton effEssai;
	
	private JLabel information;
	private JLabel tourDe;
	
	private int etat;
	private static int CHOISIT_COMB_A_DEVI = 0, ATTEND_COMB_A_DEVI_ADV = 1, CHOISIT_ESSAI = 2;
	
	public PartieMulti(Fenetre f){
		this.fenetre = f;
		this.joueur = null;
		this.niveau = null;
		
		this.maquette = null;
		this.bouton = null;
		this.essai = null;
		
		this.rouge = new ImageIcon( "images/maquette/rouge.png" );
     	this.jaune = new ImageIcon( "images/maquette/jaune.png" );
     	this.vert = new ImageIcon( "images/maquette/vert.png" );
     	this.bleu = new ImageIcon( "images/maquette/bleu.png" );
     	this.orange = new ImageIcon( "images/maquette/orange.png" );
     	this.blanc = new ImageIcon( "images/maquette/blanc.png" );
     	this.violet = new ImageIcon( "images/maquette/violet.png" );
     	this.cyan = new ImageIcon( "images/maquette/cyan.png" );
     	this.rose = new ImageIcon( "images/maquette/rose.png" );
     	this.noir = new ImageIcon( "images/maquette/noir.png" );
     	
     	this.init();
	}
	
	private void init(){
		this.setLayout( null );
		
		this.bouton = new JPanel();
		this.add( bouton );
		
		this.valider = new JButton("Valider");
		this.valider.setBounds(380,415,100,30);
		this.valider.setEnabled(false);
		this.valider.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if( essai != null && essai.getNbPion() == niveau.getPions() ){
	    			if( etat == CHOISIT_COMB_A_DEVI ){
	    				fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_ENVOI_COMB(essai) );
	    				desactiveBoutonColor();
	    				effEssai.setEnabled(false);
	    				valider.setEnabled(false);
	    				maquette.dessineSolution(essai);
	    				essai = new Pions( niveau.getPions() );
	    				maquette.popEssai(0);
	    			}else if( etat == CHOISIT_ESSAI ){
	    				fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_ENVOI_COMB(essai) );
	    				desactiveBoutonColor();
	    				effEssai.setEnabled(false);
	    				valider.setEnabled(false);
	    				nbCoupsJ1 ++;
	    				refreshInfoPartie();
	    				essai = new Pions( niveau.getPions() );
	    			}
	    		}
	    	}
		});
		this.add(this.valider);
		
		this.effEssai = new JButton("Effacer l'essai");
		this.effEssai.setBounds(500,415,200,30);
		this.effEssai.setEnabled(false);
		this.effEssai.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		maquette.popEssai( nbCoupsTour );
	    		essai = new Pions( niveau.getPions() );
	    		valider.setEnabled(false);
	    	}
		});
		this.add(this.effEssai);
		
		this.suivant = new JButton("Suivant");
		this.suivant.setBounds(380,450,100,30);
		this.suivant.setEnabled(false);
		this.suivant.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
		});
		this.add(this.suivant);
		
		this.information = new JLabel();
		this.information.setBounds(350,500,400,30);
		this.add(this.information);
		
		JButton quit = new JButton("Quitter");
		quit.setBounds(800,0,100,30);
		quit.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
		});
		this.add(quit);
		
		this.tourDe = new JLabel();
		this.tourDe.setBounds(350,100,300,30);
		this.add(this.tourDe);
		
		this.infoPartie = new JLabel();
		this.infoPartie.setBounds(350,130,400,30);
		this.add(this.infoPartie);
	}
	
	private void refreshInfoPartie(){
		this.infoPartie.setText("Tour: "+this.nbTours+", Mes coups: "+this.nbCoupsJ1+" Ses coups: "+this.nbCoupsJ2);
	}
	
	public void quitter(){
		this.joueur = null;
		this.niveau = null;
		this.nbCoupsJ1 = 0;
		this.nbCoupsJ2 = 0;
		this.nbTours = 0;
		this.remove(this.maquette);
		this.maquette = null;
		this.bouton.removeAll();
		this.essai = null;
		this.couleursAutorise = null;
		this.valider.setEnabled(false);
		this.suivant.setEnabled(false);
		this.effEssai.setEnabled(false);
		this.information.setText("");
		this.tourDe.setText("");
	}
	
	public void decoServeur(){
		
	}
	
	public void setInfoPartieMulti( Niveau n, Joueur j, boolean createur ){
		this.joueur = j;
		this.niveau = n;
		this.createur = createur;
	}
	
	public void clic(){
		this.nbCoupsJ1 = 0;
		this.nbCoupsJ2 = 0;
		this.nbCoupsTour = 0;
		this.nbTours = 0;
		
		this.maquette = new Maquette( this.niveau);
		this.maquette.setBounds(0, 0, 500, 700);
		this.maquette.setVisible(true);
		this.add(this.maquette);
		
		this.couleursAutorise = this.niveau.getCouleurAutorise();
		this.bouton.setLayout(new GridLayout(2,5,5,5));
		this.bouton.setBounds(350, 255, 200,100);
		JButton b = null;
		for( int i = 0; i < this.couleursAutorise.length; i++ ){
			b = new JButton( this.getImage( this.couleursAutorise[i] ) );
			b.addActionListener(new ListenerBoutonColor(this.couleursAutorise[i]));
			this.bouton.add(b);
		}
		this.desactiveBoutonColor();
		
		this.essai = new Pions( this.niveau.getPions() );
	}
	
	//Si on est le createur(true)
	public void joueur2Pars(){
		this.quitter();
		this.fenetre.showMenu( Fenetre.DEUXJOUEURS );
	}
		
	//Si on n'est pas le createur(false)
	public void joueur1Pars(){
		this.quitter();
		this.fenetre.showMenu( Fenetre.DEUXJOUEURS );
	}
	
	//C'est à toi de choisir la combinaison à faire deviner
	public void choisitCombADeviner(){
		this.information.setText("60 secondes pour choisir un combinaison");
		this.maquette.reset();
		this.etat = CHOISIT_COMB_A_DEVI;
		this.activeBoutonColor();
		this.effEssai.setEnabled(true);
	}
	
	//C'est à l'autre de choisir la combinaison à faire deviner
	public void choisitPasCombADeviner(){
		this.maquette.reset();
		this.etat = ATTEND_COMB_A_DEVI_ADV;
		this.information.setText("L'adversaire choisit une combinaison");
	}
	
	//Le 1er compteur est écoulé
	public void compteur1Rate(){
		this.information.setText("60 secondes ecoulés, tous les 10 secondes tes coups augmentent");
	}
	
	//L'adversaire a écoulé son 1er compteur
	public void compteur1RateAdv(){
		this.information.setText("L'adversaire a écoulé son 1er compteur");
	}
	
	//Tu as perdu un coup car 10 seconde ce sont écoulées du 2eme compteur
	public void perduCoupsCmpt2() {
		this.information.setText("10 secondes écoulés, tu as augmentés tes coups");
		this.nbCoupsJ1 ++;
		this.refreshInfoPartie();
	}
	
	//L'adversaire a perdu un coups car 10 seconde ce sont écoulées du 2eme compteur
	public void advPerduCoupsCmpt2() {
		this.information.setText("L'adversaire à perdu un coups");
		this.nbCoupsJ2 ++;
		this.refreshInfoPartie();
	}
	
	//Tu as perdu à cause du compteur2 écoulé
	public void perduCmpt2() {
		this.information.setText("Compteur terminé, tu as perdu, malus augmenté");
		//Quitter partie****************************************************************************************
	}
		
	//L'adversaire a perdu à cause du compteur2 écoulé
	public void advPerduCmpt2() {
		this.information.setText("L'adversaire à écoulé son compteur, il a perdu");
		//Quitter partie****************************************************************************************
	}
	
	//La combinaison à trouver a été fixé
	public void combFixe(){
		this.information.setText("La combinaison à deviner, a été fixé");
	}
	
	//On n'attend(et autorise) que tu choisis(et envoi) ton essai
	public void choisitEssai(){
		this.information.setText("Fait ton essai");
		this.activeBoutonColor();
		this.effEssai.setEnabled(true);
		this.etat = CHOISIT_ESSAI;
	}
	
	//L'adversaire a sousmis un essai
	public void envoiEssaiAdv(Pions essai) {
		this.information.setText("L'adversaire a soumis un essai");
		this.maquette.addPions(this.nbCoupsJ2,essai);
		this.nbCoupsJ2 ++;
		this.refreshInfoPartie();
	}
	
	private ImageIcon getImage( Couleur c ){
		if( c == Couleur.Blanc ){
			return this.blanc;
		}else if( c == Couleur.Bleu ){
			return this.bleu;
		}else if( c == Couleur.Cyan ){
			return this.cyan;
		}else if( c == Couleur.Jaune ){
			return this.jaune;
		}else if( c == Couleur.Noir ){
			return this.noir;
		}else if( c == Couleur.Orange ){
			return this.orange;
		}else if( c == Couleur.Rose ){
			return this.rose;
		}else if( c == Couleur.Rouge ){
			return this.rouge;
		}else if( c == Couleur.Vert ){
			return this.vert;
		}else if( c == Couleur.Violet ){
			return this.violet;
		}
		return null;
	}
	
	class ListenerBoutonColor implements ActionListener{
		private Couleur c;
		public ListenerBoutonColor(Couleur c){
			this.c = c;
		}
		public void actionPerformed(ActionEvent e) {
    		clicBoutonCouleur(this.c);
    	}
	}
	
	private void clicBoutonCouleur( Couleur c ){
		if( this.essai.getNbPion() < this.niveau.getPions() ){
			this.essai.addPion( c );
			this.maquette.addPion( this.nbCoupsTour, this.essai.getNbPion(), c);
		}else{
			this.valider.setEnabled(true);
		}
	}
	
	private void desactiveBoutonColor(){
		Component[] tab = this.bouton.getComponents();
		for(int i = 0; i < tab.length; i++ ){
			tab[i].setEnabled(false);
		}
	}
	
	private void activeBoutonColor(){
		Component[] tab = this.bouton.getComponents();
		for(int i = 0; i < tab.length; i++ ){
			tab[i].setEnabled(true);
		}
	}
}
