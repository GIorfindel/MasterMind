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
import mastermind.Niveau;
import mastermind.Pions;
import mastermind.Solo;

@SuppressWarnings("serial")
public class MSolo extends Menu{
	
	private Solo solo;
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
	
	
	public MSolo( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.solo = new Solo("",Niveau.niveauString("TresFacile"),null);
		this.maquette = null;
		this.valider = null;
		this.init();
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
	}
	
	public void setNiveau( Niveau niveau ){
		this.solo.setNiveau(niveau);
	}
	
	private void init(){
		this.setLayout( null );
		
		this.bouton = new JPanel();
		this.add( bouton );
		
		this.valider = new JButton("Valider");
		this.valider.setBounds(350,415,100,30);
		this.valider.setEnabled(false);
		this.valider.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if( essai.getNbPion() == solo.getNiveau().getPions() ){
	    			int coups = solo.getTour().getCoups();
	    			if (solo.getTour().testCombinaison( essai )){
	    				maquette.dessineSolution( solo.getTour().getComb() );
	    				information.setText("Bravo! encore " + ( 10 - solo.getNbTour()) + " tours" );
	    				suivant.setEnabled(true);
	    				effEssai.setEnabled(false);
	    				valider.setEnabled(false);
	    				desactiveBoutonColor();
	    			}else{
	    				if(solo.getTour().getCoups() == solo.getNiveau().getCoupMax()){
		    				//Vous avez perdu A faire*************************************************************
		    				information.setText("Vous avez perdu!" );
		    				suivant.setEnabled(false);
		    				effEssai.setEnabled(false);
		    				valider.setEnabled(false);
		    				desactiveBoutonColor();
		    			}
	    			}
	    			maquette.addAide( coups, solo.getTour().getNombreBonnePosition(coups), solo.getTour().getNombreBonneCouleur(coups));
	    			essai = new Pions(solo.getNiveau().getPions());
	    			valider.setEnabled(false);
	    		}
	    	}
		});
		this.add(this.valider);
		
		this.effEssai = new JButton("Effacer l'essai");
		this.effEssai.setBounds(470,415,200,30);
		this.effEssai.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		essai = new Pions(solo.getNiveau().getPions());;
	    		maquette.popEssai( solo.getTour().getCoups() );
	    		valider.setEnabled(false);
	    	}
		});
		this.add(this.effEssai);
		
		this.suivant = new JButton("Suivant");
		this.suivant.setBounds(350,450,100,30);
		this.suivant.setEnabled(false);
		this.suivant.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if( solo.getNbTour() == 10 ){
	    			information.setText("Vous avez gagné!" );
	    			suivant.setEnabled(false);
    				effEssai.setEnabled(false);
    				valider.setEnabled(false);
    				desactiveBoutonColor();
    				return;
	    			//Vous avez gagné***************************************************************************
	    		}else{
	    			information.setText("" );
	    		}
	    		nouveauTour();
	    	}
		});
		this.add(this.suivant);
		
		this.information = new JLabel();
		this.information.setBounds(350,500,300,30);
		this.add(this.information);
		
		JButton quit = new JButton("Quitter");
		quit.setBounds(800,0,100,30);
		quit.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		quitter();
	    		fenetre.showMenu( Fenetre.ACCUEIL );
	    	}
		});
		this.add(quit);
		
		JButton recomm = new JButton("Recommencer");
		recomm.setBounds(800,400,150,30);
		recomm.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		solo.reset();
	    		nouveauTour();
	    	}
		});
		this.add(recomm);
	}
	
	public void quitter(){
		this.remove( this.maquette );
		this.maquette = null;
		this.bouton.removeAll();
		this.valider.setEnabled(false);
		this.suivant.setEnabled(false);
		this.effEssai.setEnabled(true);
		this.couleursAutorise = null;
		this.solo.reset();
		this.information.setText("");
		this.desactiveBoutonColor();
	}
	
	public void clic(){
		this.maquette = new Maquette( this.solo.getNiveau());
		this.maquette.setBounds(0, 0, 500, 700);
		this.maquette.setVisible(true);
		this.add(this.maquette);
		this.couleursAutorise = this.solo.getNiveau().getCouleurAutorise();
		this.initBoutonCouleur();
		this.essai = new Pions(this.solo.getNiveau().getPions());
		
		this.nouveauTour();
	}
	
	public void nouveauTour(){
		this.solo.nouveauTour( this.couleursAutorise );
		this.maquette.reset();
		this.information.setText("");
		this.valider.setEnabled(false);
		this.suivant.setEnabled(false);
		this.effEssai.setEnabled(true);
		this.essai = new Pions(solo.getNiveau().getPions());
		this.activeBoutonColor();
		
		this.maquette.dessineSolution( solo.getTour().getComb() );
	}
	
	private void initBoutonCouleur(){
		this.bouton.setLayout(new GridLayout(2,5,5,5));
		this.bouton.setBounds(350, 255, 200,100);
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
}