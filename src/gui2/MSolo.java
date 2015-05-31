package gui2;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mastermind.Couleur;
import mastermind.Niveau;
import mastermind.Paquet;
import mastermind.Pions;
import mastermind.Score;
import mastermind.Solo;

@SuppressWarnings("serial")
public class MSolo extends Menu{
	
	private Solo solo;
	private boolean soloCharger;
	private Fenetre fenetre;
	private Maquette maquette;
	private Couleur[] couleursAutorise;
	
	private JPanel bouton = new JPanel();
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
	
	private JButton valider;
	private JButton suivant;
	private JButton effEssai;

	private JLabel information;
	private JLabel infoPartie;
	
	private JMenuBar menuBar;
	
	private JMenu mnJeu;
	
	JMenuItem mntmNouvellePartie;
	JMenuItem save;
	JMenuItem charger;
	JMenuItem mntmQuitter;
	JMenuItem retourChoixNiveau;

    
	public MSolo( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.solo = new Solo("",Niveau.niveauString("TresFacile"),null);
		this.soloCharger = false;
		this.maquette = null;
		this.valider = null;
		this.init();
		this.rouge = new ImageIcon( MSolo.class.getResource("/imgPions/rouge.png") );
     	this.jaune = new ImageIcon( MSolo.class.getResource("/imgPions/jaune.png") );
     	this.vert = new ImageIcon( MSolo.class.getResource("/imgPions/vert.png") );
     	this.bleu = new ImageIcon( MSolo.class.getResource("/imgPions/bleu.png") );
     	this.orange = new ImageIcon( MSolo.class.getResource("/imgPions/orange.png") );
     	this.blanc = new ImageIcon( MSolo.class.getResource("/imgPions/blanc.png") );
     	this.violet = new ImageIcon( MSolo.class.getResource("/imgPions/violet.png") );
     	this.cyan = new ImageIcon( MSolo.class.getResource("/imgPions/cyan.png") );
     	this.rose = new ImageIcon( MSolo.class.getResource("/imgPions/rose.png") );
     	this.noir = new ImageIcon( MSolo.class.getResource("/imgPions/noir.png") );
	}
	
	private void addMenuBar() {
		this.menuBar= new JMenuBar();
		menuBar.setBounds(0, 0, 960, 26);
		    
		this.mnJeu = new JMenu("Jeu");
		this.menuBar.add(mnJeu);
		    
	    this.mntmNouvellePartie = new JMenuItem("Nouvelle partie");
	    this.mntmNouvellePartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	    		int option = JOptionPane.showConfirmDialog(null,
	    				"Voulez-vous démarrer une nouvelle partie ?\n La partie actuelle sera perdue.",
	    				"Nouvelle partie", 
	    				JOptionPane.YES_NO_OPTION, 
	    				JOptionPane.QUESTION_MESSAGE);

	    		if(option != JOptionPane.NO_OPTION && option != JOptionPane.CLOSED_OPTION){
	    			solo.reset();
		    		nouveauTour();
	    	      }
	    		
	    	}
		});
	    this.mnJeu.add(mntmNouvellePartie);
	    
	    this.save = new JMenuItem("Sauvegarder");
	    this.save.setEnabled(false);
		this.save.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int option = JOptionPane.showConfirmDialog(null,
	    				"Sauvegarder la partie actuelle ?",
	    				"Sauvegarder", 
	    				JOptionPane.YES_NO_OPTION, 
	    				JOptionPane.QUESTION_MESSAGE);

	    		if(option != JOptionPane.NO_OPTION && option != JOptionPane.CLOSED_OPTION){
	    			if( fenetre.getClient().connecterAuCompte() && !solo.getNiveau().toString().equals( Niveau.PERSO )){
		    			solo.setJoueur(fenetre.getClient().getJoueur());
		    			solo.setNom( solo.getJoueur().getIdentifiant() );
			    		fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_SAVE_SOLO(solo) );
			    		information.setText("Partie sauvegardée");
		    		}
	    	      }
	    	}
		});
	    this.mnJeu.add(save);
	    
	    this.charger = new JMenuItem("Charger");
	    this.charger.setEnabled(false);
	    this.charger.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				int option = JOptionPane.showConfirmDialog(null,
	    				"Voulez-vous charger une partie ?\n La partie actuelle sera perdue.",
	    				"Sauvegarder", 
	    				JOptionPane.YES_NO_OPTION, 
	    				JOptionPane.QUESTION_MESSAGE);

	    		if(option != JOptionPane.NO_OPTION && option != JOptionPane.CLOSED_OPTION){
	    			if( fenetre.getClient().connecterAuCompte()){
						Paquet p = Paquet.creeDEMANDE_CHARGER_SOLO();
						int id = p.getId();
						fenetre.getClient().envoyerPaquet( p );
						Paquet ps = fenetre.getClient().recevoirPaquet(5.0, id );
						
						if(ps != null) {
							
							if(ps.getNbObjet() == 0) {
								information.setText("Partie introuvable");
							}
							
							else {
								quitter();
					    		fenetre.showMenu( Fenetre.ACCUEIL );
								Solo s = Paquet.getSolo(ps);
								s.getTour().ajouteAides();
								fenetre.setSoloCharger(s);
								fenetre.showMenu( Fenetre.SOLO );
							}
						}else{
							information.setText("Impossible de charger votre partie");
						}
					}
	    	      }
			}
	    });
	    this.mnJeu.add(charger);
	    
	    this.retourChoixNiveau = new JMenuItem("Changer la difficulté");
	    this.retourChoixNiveau.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int option = JOptionPane.showConfirmDialog(null,
	    				"Changer la difficulté ?\n La partie actuelle sera perdue.",
	    				"Changer la difficulté", 
	    				JOptionPane.YES_NO_OPTION, 
	    				JOptionPane.QUESTION_MESSAGE);

	    		if(option != JOptionPane.NO_OPTION && option != JOptionPane.CLOSED_OPTION){
	    			quitter();
		    		fenetre.showMenu( Fenetre.UNJOUEUR );
	    	      }
	    	}
		});
	    this.mnJeu.add(retourChoixNiveau);
	    
	    this.mntmQuitter = new JMenuItem("Quitter");
	    this.mntmQuitter.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int option = JOptionPane.showConfirmDialog(null,
	    				"Voulez-vous vraiment quitter ?\n La partie actuelle sera perdue.",
	    				"Quitter", 
	    				JOptionPane.YES_NO_OPTION, 
	    				JOptionPane.QUESTION_MESSAGE);

	    		if(option != JOptionPane.NO_OPTION && option != JOptionPane.CLOSED_OPTION){
	    			quitter();
		    		fenetre.showMenu( Fenetre.ACCUEIL );
	    	      }
	    		
	    	}
		});
	    this.mnJeu.add(mntmQuitter);
	    
		
	    this.add(this.menuBar);
			
	}
	
	public void setNiveau( Niveau niveau ){
		this.solo.setNiveau(niveau);
	}
	
	public void setSolo( Solo s){
		this.solo = s;
		this.soloCharger = true;
	}
	
	private void init(){
		this.setLayout( null );
		
		this.addMenuBar();
		this.infoPartie = new JLabel();
		this.infoPartie.setBounds(380,50,500,40);
		this.add(this.infoPartie);
		this.bouton = new JPanel();
		this.add( bouton );
		
		this.valider = new JButton("Valider");
		this.valider.setBounds(380,300,200,40);
		this.valider.setEnabled(false);
		this.valider.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if( essai.getNbPion() == solo.getNiveau().getPions() ){
	    			int coups = solo.getTour().getCoups();
	    			if (solo.testCombinaison( essai )){
	    				maquette.dessineSolution( solo.getTour().getComb() );
	    				information.setText("Bravo! encore " + ( 10 - solo.getNbTour()) + " tours" );
	    				suivant.setEnabled(true);
	    				effEssai.setEnabled(false);
	    				valider.setEnabled(false);
	    				desactiveBoutonColor();
	    				save.setEnabled(false);
	    			}else{
	    				if(solo.getTour().getCoups() == solo.getNiveau().getCoupMax()){//Perdu
	    					//On peut pas sauvegarder le score si le niveau est perso
	    					if( fenetre.getClient().connecterAuCompte() && !solo.getNiveau().toString().equals( Niveau.PERSO )){
	        					Score s = new Score(Score.MODE_SOLO, solo.getCoups(),false,solo.getNbTour(),solo.getNiveau());
	        					fenetre.getClient().envoyerPaquet(Paquet.creeDEMANDE_NOUV_SCORE(s));
	        					quitter();
	        					fenetre.showMenu(Fenetre.SCORE);
	        				}else{
	        					save.setEnabled(false);
	        					valider.setEnabled(false);
	        					suivant.setEnabled(false);
	        					effEssai.setEnabled(false);
	        					solo.reset();
	        					desactiveBoutonColor();
	        					information.setText("Vous avez Perdu");
	        				}
	        				return;
		    			}
	    			}
	    			maquette.addAide( coups, solo.getTour().getNombreBonnePosition(coups), solo.getTour().getNombreBonneCouleur(coups));
	    			essai = new Pions(solo.getNiveau().getPions());
	    			valider.setEnabled(false);
	    			refreshInfoPartie();
	    		}
	    	}
		});
		this.add(this.valider);
		
		this.effEssai = new JButton("Effacer l'essai");
		this.effEssai.setBounds(380,350,200,40);
		this.effEssai.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		essai = new Pions(solo.getNiveau().getPions());;
	    		maquette.popEssai( solo.getTour().getCoups() );
	    		valider.setEnabled(false);
	    	}
		});
		this.add(this.effEssai);
		
		this.suivant = new JButton("Suivant");
		this.suivant.setBounds(380,400,200,40);
		this.suivant.setEnabled(false);
		this.suivant.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if( solo.getNbTour() == 10 ){//Gagné
    				if( fenetre.getClient().connecterAuCompte() && !solo.getNiveau().toString().equals( Niveau.PERSO )){
    					Score s = new Score(Score.MODE_SOLO, solo.getCoups(),true,solo.getNbTour(),solo.getNiveau());
    					fenetre.getClient().envoyerPaquet(Paquet.creeDEMANDE_NOUV_SCORE(s));
    					quitter();
    					fenetre.showMenu(Fenetre.SCORE);
    				}else{
    					save.setEnabled(false);
    					valider.setEnabled(false);
    					suivant.setEnabled(false);
    					effEssai.setEnabled(false);
    					solo.reset();
    					desactiveBoutonColor();
    					information.setText("Vous avez Gagné");
    				}
    				return;
	    		}
	    		nouveauTour();
	    		refreshInfoPartie();
	    	}
		});
		this.add(this.suivant);
		
		this.information = new JLabel();
		this.information.setBounds(380,500,300,40);
		this.add(this.information);

	}
	
	public void quitter(){
		this.remove( this.maquette );
		this.save.setEnabled(false);
		this.maquette = null;
		this.bouton.removeAll();
		this.valider.setEnabled(false);
		this.suivant.setEnabled(false);
		this.effEssai.setEnabled(true);
		this.couleursAutorise = null;
		this.solo.reset();
		this.information.setText("");
		this.desactiveBoutonColor();
		this.infoPartie.setText("");
	}
	
	public void clic(){
		this.maquette = new Maquette( this.solo.getNiveau());
		this.maquette.setBounds(20, 50, 500, 700);
		this.maquette.setVisible(true);
		this.add(this.maquette);
		this.couleursAutorise = this.solo.getNiveau().getCouleurAutorise();
		this.initBoutonCouleur();
		this.essai = new Pions(this.solo.getNiveau().getPions());
		if( this.fenetre.getClient().connecterAuCompte() && !solo.getNiveau().toString().equals( Niveau.PERSO ) ){
			this.save.setEnabled(true);
			this.charger.setEnabled(true);
		}
		if( this.soloCharger ){
			this.maquette.reset();
			this.information.setText("");
			this.valider.setEnabled(false);
			this.suivant.setEnabled(false);
			this.effEssai.setEnabled(true);
			this.essai = new Pions(solo.getNiveau().getPions());
			this.activeBoutonColor();
			this.soloCharger = false;
			this.maquette.setMaquette(solo.getTour());
			this.refreshInfoPartie();
			
			this.maquette.dessineSolution( solo.getTour().getComb() );
		}else{
			this.nouveauTour();
		}
	}
	
	public void nouveauTour(){
		if( this.fenetre.getClient().connecterAuCompte() && !solo.getNiveau().toString().equals( Niveau.PERSO )){
			this.save.setEnabled(true);
		}
		this.solo.nouveauTour( this.couleursAutorise );
		this.maquette.reset();
		this.information.setText("");
		this.valider.setEnabled(false);
		this.suivant.setEnabled(false);
		this.effEssai.setEnabled(true);
		this.essai = new Pions(solo.getNiveau().getPions());
		this.activeBoutonColor();
		this.refreshInfoPartie();
		
		this.maquette.dessineSolution( solo.getTour().getComb() );
	}
	
	private void refreshInfoPartie(){
		this.infoPartie.setText("Tour : "+this.solo.getNbTour()+"      Coups : "+this.solo.getTour().getCoups()+"      Coups totaux : "+this.solo.getCoups());
	}
	
	private void initBoutonCouleur(){
		this.bouton.setLayout(new GridLayout(2,5,5,5));
		this.bouton.setBounds(380, 150, 200,100);
		JButton b = null;
		for( int i = 0; i < this.couleursAutorise.length; i++ ){
			b = new JButton( this.getImage( this.couleursAutorise[i] ) );
			b.addActionListener(new ListenerBoutonColor(this.couleursAutorise[i]));
			this.bouton.add(b);
		}
	}
	
	class ListenerBoutonColor implements ActionListener{
		private Couleur c;
		public ListenerBoutonColor(Couleur c){
			this.c = c;
		}
		public void actionPerformed(ActionEvent e) {
    		clicBoutonCouleur(c);
    	}
	}
	
	private void clicBoutonCouleur( Couleur c ){
		if( this.essai.getNbPion() < this.solo.getNiveau().getPions()){
			this.maquette.addPion(this.solo.getTour().getCoups(), this.essai.getNbPion(), c);
			this.essai.addPion( c );
		}
		if( this.essai.getNbPion() == this.solo.getNiveau().getPions() ){
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
	
	public void decoServeur(){
		this.save.setEnabled(false);
		this.information.setText("Vous n'êtes plus connecté au serveur");
	}
}
