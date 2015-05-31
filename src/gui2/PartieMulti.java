package gui2;

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
import mastermind.Tour;

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
	private Pions comb_a_trouve;
	
	private Couleur[] couleursAutorise;
	
	private JButton valider;
	private JButton voirScore;
	private JButton effEssai;
	private JButton choisirQuiComm;
	private JButton tourSuivant;
	
	private JLabel information;
	
	private JLabel imgJ1;
	private JLabel imgJ2;
	private JLabel infoNiveau;
	
	private int etat;
	private static int CHOISIR_QUI_COMM = 0 , CHOISIT_COMB_A_DEVI = 1, ATTEND_COMB_A_DEVI_ADV = 2, CHOISIT_ESSAI = 3, QUITTER = 4, TOUR_SUIVANT = 5;
	
	public PartieMulti(Fenetre f){
		this.fenetre = f;
		this.joueur = null;
		this.niveau = null;
		
		this.maquette = null;
		
		this.bouton = null;
		this.essai = null;
		
		this.rouge = new ImageIcon( PartieMulti.class.getResource("/imgPions/rouge.png") );
     	this.jaune = new ImageIcon( PartieMulti.class.getResource("/imgPions/jaune.png") );
     	this.vert = new ImageIcon( PartieMulti.class.getResource("/imgPions/vert.png") );
     	this.bleu = new ImageIcon( PartieMulti.class.getResource("/imgPions/bleu.png") );
     	this.orange = new ImageIcon( PartieMulti.class.getResource("/imgPions/orange.png") );
     	this.blanc = new ImageIcon( PartieMulti.class.getResource("/imgPions/blanc.png") );
     	this.violet = new ImageIcon( PartieMulti.class.getResource("/imgPions/violet.png") );
     	this.cyan = new ImageIcon( PartieMulti.class.getResource("/imgPions/cyan.png") );
     	this.rose = new ImageIcon( PartieMulti.class.getResource("/imgPions/rose.png") );
     	this.noir = new ImageIcon( PartieMulti.class.getResource("/imgPions/noir.png") );
     	
     	this.init();
	}
	
	private void init(){
		this.setLayout( null );
		
		this.infoNiveau = new JLabel();
		this.infoNiveau.setBounds(350,100,400,30);
		this.add(this.infoNiveau);
		
		this.bouton = new JPanel();
		this.add( bouton );
		
		this.valider = new JButton("Valider");
		this.valider.setBounds(380,415,100,30);
		this.valider.setEnabled(false);
		this.valider.setVisible(true);
		this.valider.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if( essai != null && essai.getNbPion() == niveau.getPions() ){
	    			if( etat == CHOISIT_COMB_A_DEVI ){
	    				if( !niveau.valideCombinaison(essai) ){
	    					information.setText("Combinaison ne respecte pas le niveau");
	    					return;
	    				}
	    				fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_ENVOI_COMB(essai) );
	    				desactiveBoutonColor();
	    				effEssai.setEnabled(false);
	    				valider.setEnabled(false);
	    				maquette.dessineSolution(essai);
	    				comb_a_trouve = essai;
	    				essai = new Pions( niveau.getPions() );
	    				maquette.popEssai(0);
	    			}else if( etat == CHOISIT_ESSAI ){
	    				fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_ENVOI_COMB(essai) );
	    				desactiveBoutonColor();
	    				effEssai.setEnabled(false);
	    				valider.setEnabled(false);
	    				nbCoupsJ1 ++;
	    				Pions aide = Tour.getAide(essai, comb_a_trouve);
	    				maquette.addAide( nbCoupsTour, Tour.comptePionsCouleur(aide, Couleur.Noir), Tour.comptePionsCouleur(aide, Couleur.Blanc));
	    				nbCoupsTour ++;
	    				refreshInfoPartie();
	    				essai = new Pions( niveau.getPions() );
	    			}
	    		}
	    		information.setText("");
	    	}
		});
		this.add(this.valider);
		
		this.effEssai = new JButton("Effacer l'essai");
		this.effEssai.setBounds(500,415,200,30);
		this.effEssai.setVisible(true);
		this.effEssai.setEnabled(false);
		this.effEssai.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		maquette.popEssai( nbCoupsTour );
	    		essai = new Pions( niveau.getPions() );
	    		valider.setEnabled(false);
	    	}
		});
		this.add(this.effEssai);
		
		this.voirScore = new JButton("Voir score");
		this.voirScore.setBounds(380,450,150,30);
		this.voirScore.setVisible(true);
		this.voirScore.setEnabled(false);
		this.voirScore.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitter();
	    		fenetre.showMenu(Fenetre.SCORE);
	    	}
		});
		this.add(this.voirScore);
		
		this.information = new JLabel();
		this.information.setBounds(350,500,400,30);
		this.add(this.information);
		
		JButton quit = new JButton("Quitter");
		quit.setBounds(800,0,100,30);
		quit.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if( fenetre.getClient().getConnecteAuServeur() ){
	    			if(createur){
		    			fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_JOUEUR1_PARTI() );
		    		}else{
		    			fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_JOUEUR2_PARTI() );
		    		}
		    		if( etat == CHOISIT_COMB_A_DEVI || etat == CHOISIT_ESSAI ){
		    			Joueur j = fenetre.getClient().getJoueur();
		    			j.setMalus( j.getMalus() + 1 );
		    		}
		    		quitter();
		    		fenetre.showMenu(Fenetre.DEUXJOUEURS);
	    		}else{
	    			quitter();
		    		fenetre.showMenu(Fenetre.ACCUEIL);
	    		}
	    	}
		});
		this.add(quit);
		
		this.infoPartie = new JLabel();
		this.infoPartie.setBounds(350,130,400,30);
		this.add(this.infoPartie);
		
		this.choisirQuiComm = new JButton("Choisir qui commence");
		this.choisirQuiComm.setBounds(350,160,400,30);
		this.choisirQuiComm.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if( createur && etat == CHOISIR_QUI_COMM ){
	    			fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_CHOISIR_QUI_COMMENCE() );
	    			choisirQuiComm.setVisible(false);
	    		}
	    	}
		});
		this.add(this.choisirQuiComm);
		
		this.tourSuivant = new JButton("Tour suivant");
		this.tourSuivant.setBounds(380,480,150,30);
		this.tourSuivant.setVisible(false);
		this.tourSuivant.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_TOUR_SUIVANT() );
	    		tourSuivant.setVisible(false);
	    	}
		});
		this.add(this.tourSuivant);
		
		this.imgJ1 = new JLabel();
		this.imgJ1.setBounds(500,400, Joueur.WIDTH_AVATAR, Joueur.HEIGHT_AVATAR);
		this.add(this.imgJ1);
		
		this.imgJ2 = new JLabel();
		this.imgJ2.setBounds(500, 400+Joueur.HEIGHT_AVATAR+10, Joueur.WIDTH_AVATAR, Joueur.HEIGHT_AVATAR);
		this.add(this.imgJ1);
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
		if( this.maquette != null ){
			this.remove(this.maquette);
			this.maquette = null;
		}
		this.bouton.removeAll();
		this.essai = null;
		this.couleursAutorise = null;
		this.valider.setEnabled(false);
		this.voirScore.setEnabled(false);
		this.effEssai.setEnabled(false);
		this.tourSuivant.setEnabled(false);
		this.choisirQuiComm.setEnabled(false);
		this.information.setText("");
	}
	
	public void decoServeur(){
		this.quitter();
		this.information.setText("Vous êtes déconecté du serveur");
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
		
		this.infoNiveau.setText("Niveau: "+this.niveau.getNomNiveau());
		
		if( this.joueur.getAvatar() != null ){
			this.imgJ2.setIcon( this.joueur.getAvatar() );
			this.repaint();
		}
		
		if( this.fenetre.getClient().getJoueur().getAvatar() != null ){
			this.imgJ2.setIcon( this.fenetre.getClient().getJoueur().getAvatar() );
			this.repaint();
		}
		
		this.maquette = new Maquette( this.niveau);
		this.maquette.setBounds(0, 0, 500, 700);
		this.maquette.setVisible(true);
		this.add(this.maquette);
		this.maquette.repaint();
		
		this.couleursAutorise = this.niveau.getCouleurAutorise();
		this.bouton.setLayout(new GridLayout(2,5,5,5));
		this.bouton.setBounds(350, 255, 200,100);
		JButton b = null;
		for( int i = 0; i < this.couleursAutorise.length; i++ ){
			b = new JButton( this.getImage( this.couleursAutorise[i] ) );
			b.addActionListener(new ListenerBoutonColor(this.couleursAutorise[i]));
			this.bouton.add(b);
		}
		
		this.essai = new Pions( this.niveau.getPions() );
		this.refreshInfoPartie();
		
		this.valider.setEnabled(false);
		this.effEssai.setEnabled(false);
		this.voirScore.setVisible(false);
		this.desactiveBoutonColor();
		this.etat = CHOISIR_QUI_COMM;
		if( this.createur ){
			this.choisirQuiComm.setVisible(true);
			this.choisirQuiComm.setEnabled(true);
		}else{
			this.choisirQuiComm.setVisible(false);
			this.information.setText("On choisit qui commence...");
		}
		this.tourSuivant.setVisible(false);
	}
	
	//Si on est le createur(true)
	public void joueur2Pars(){
		this.quitter();
		this.information.setText("L'adversaire est parti");
		this.etat = QUITTER;
	}
		
	//Si on n'est pas le createur(false)
	public void joueur1Pars(){
		this.quitter();
		this.information.setText("L'adversaire est parti");
		this.etat = QUITTER;
	}
	
	//C'est à toi de choisir la combinaison à faire deviner
	public void choisitCombADeviner(){
		this.nbCoupsTour = 0;
		this.nbTours++;
		this.valider.setEnabled(false);
		this.essai = new Pions( this.niveau.getPions() );
		if( this.maquette != null ){
			this.maquette.reset();
		}
		
		this.information.setText("60 secondes pour choisir une combinaison");
		this.etat = CHOISIT_COMB_A_DEVI;
		this.activeBoutonColor();
		this.effEssai.setEnabled(true);
		this.repaint();
	}
	
	//C'est à l'autre de choisir la combinaison à faire deviner
	public void choisitPasCombADeviner(){
		this.nbCoupsTour = 0;
		this.nbTours++;
		this.valider.setEnabled(false);
		this.essai = new Pions( this.niveau.getPions() );
		if( this.maquette != null ){
			this.maquette.reset();
		}
		
		this.desactiveBoutonColor();
		this.etat = ATTEND_COMB_A_DEVI_ADV;
		this.information.setText("L'adversaire choisit une combinaison");
		this.repaint();
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
		this.effEssai.setEnabled(false);
		this.valider.setEnabled(false);
		this.voirScore.setEnabled(false);
		Joueur j = this.fenetre.getClient().getJoueur();
		j.setMalus( j.getMalus() + 1 );
		this.etat = QUITTER;
	}
		
	//L'adversaire a perdu à cause du compteur2 écoulé
	public void advPerduCmpt2() {
		this.information.setText("L'adversaire à écoulé son compteur, il a perdu");
		this.effEssai.setEnabled(false);
		this.valider.setEnabled(false);
		this.voirScore.setEnabled(false);
	}
	
	//La combinaison à trouver a été fixé
	public void combFixe(Pions comb){
		this.nbCoupsTour = 0;
		this.comb_a_trouve = comb;
		this.information.setText("La combinaison à deviner, a été fixé, fait ton essai");
		this.activeBoutonColor();
		this.effEssai.setEnabled(true);
		this.etat = CHOISIT_ESSAI;
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
		this.maquette.addPions(nbCoupsTour,essai);
		Pions aide = Tour.getAide(essai, comb_a_trouve);
		maquette.addAide( nbCoupsTour, Tour.comptePionsCouleur(aide, Couleur.Noir), Tour.comptePionsCouleur(aide, Couleur.Blanc));
		this.nbCoupsTour++;
		this.nbCoupsJ2 ++;
		this.refreshInfoPartie();
	}
	
	public void combTrouve() {
		this.etat = TOUR_SUIVANT;
		if( this.createur ){
			this.information.setText("Combinaison trouvé");
			this.tourSuivant.setVisible(true);
			this.tourSuivant.setEnabled(true);
		}else{
			this.information.setText("Combinaison trouvé, attendre le tour suivant");
		}
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
			this.maquette.addPion( this.nbCoupsTour, this.essai.getNbPion(), c);
			this.essai.addPion( c );
		}
		if( this.essai.getNbPion() == this.niveau.getPions() ){
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

	public void tuAsGagne() {
		this.information.setText("Tu as gagné");
		this.effEssai.setEnabled(false);
		this.valider.setEnabled(false);
		this.voirScore.setVisible(true);
		this.voirScore.setEnabled(true);
		desactiveBoutonColor();
		this.etat = QUITTER;
	}

	public void tuAsPerdu() {
		this.information.setText("Tu as perdu");
		this.effEssai.setEnabled(false);
		this.valider.setEnabled(false);
		this.voirScore.setVisible(true);
		this.voirScore.setEnabled(true);
		desactiveBoutonColor();
		this.etat = QUITTER;
	}
}
