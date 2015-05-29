package gui2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mastermind.Joueur;
import mastermind.Niveau;
import mastermind.Pions;

@SuppressWarnings("serial")
public class PartieMulti extends Menu{
	
	private Fenetre fenetre;
	private Joueur joueur;
	private Niveau niveau;
	private boolean createur;
	
	private int nbCoups;
	private int nbTours;
	
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
	
	private JButton valider;
	private JButton suivant;
	private JButton effEssai;
	
	private JLabel information;
	
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
	    		
	    	}
		});
		this.add(this.valider);
		
		this.effEssai = new JButton("Effacer l'essai");
		this.effEssai.setBounds(500,415,200,30);
		this.effEssai.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
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
		this.information.setBounds(350,500,300,30);
		this.add(this.information);
		
		JButton quit = new JButton("Quitter");
		quit.setBounds(800,0,100,30);
		quit.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
		});
		this.add(quit);
	}
	
	public void quitter(){
		
	}
	
	public void decoServeur(){
		
	}
	
	public void setInfoPartieMulti( Niveau n, Joueur j, boolean createur ){
		this.joueur = j;
		this.niveau = n;
		this.createur = createur;
	}
	
	public void clic(){
		this.nbCoups = 0;
		this.nbTours = 0;
	}
	
	//Si on est le createur(true)
	public void joueur2Pars(){
		
	}
		
	//Si on n'est pas le createur(false)
	public void joueur1Pars(){
			
	}
}
