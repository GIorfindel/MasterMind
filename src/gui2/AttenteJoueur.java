package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AttenteJoueur extends Menu {
	
	private Fenetre fenetre;
	
	private JLabel lbltitre;
	private JLabel infoPartie;
	private JLabel lblnbPions;
	private JLabel lblnbCoups;
	private JLabel lblnbCouleurs;
	private JLabel lbldifficulte;
	private JLabel lblcouleursMultiples;
	private JLabel lblJoueurs;
	private JLabel lbljoueur1;
	private JLabel lbljoueur2;
	private JLabel information;

	private JButton modifier;
	private JButton lancer;
	private JButton retour;
	private JButton kicker;

	
	private JLabel titre;
	private JLabel nbPions;
	private JLabel nbCoups;
	private JLabel nbCouleurs;
	private JLabel difficulte;
	private JLabel couleursMultiples;
	private JLabel joueur1;
	private JLabel joueur2;
	
	

	private JButton valider;

	
	private static int X = 405, W = 200, H = 40;

	public AttenteJoueur( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init() {
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addTitre();
		this.addAttente();
		this.addNbCouleurs();
		this.addNbCoup();
		this.addNbPions();
		this.addDificulte();
		this.addJ1();
		this.addJ2();
		this.addJoueur();
		this.addInfoPartie();
		this.addModifier();
		this.addLancer();
		this.addKicker();
		this.addQuitter();
		this.addCouleursMultiples();
	}
	
	private void addTitre() {
		this.lbltitre = new JLabel( "Partie en ligne" );
		this.lbltitre.setFont(new Font("Agency FB", Font.PLAIN, 40)); // Modification de la police
		this.lbltitre.setBounds(170, 50, 660, 100);
		this.lbltitre.setVerticalAlignment( SwingConstants.TOP );
		this.lbltitre.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( this.lbltitre );
	}
	
	private void addAttente() {
		this.information = new JLabel("Attente d'un joueur...");
	    this.information.setBounds(434, 143, 150, 30);
	    this.add( this.information );
	}
	
	private void addInfoPartie() {
		this.infoPartie = new JLabel("Paramètres de la partie :");
	    this.infoPartie.setFont(new Font("Tahoma", Font.BOLD, 13));
	    this.infoPartie.setBounds(170, 190, 209, 30);
	    this.add(this.infoPartie);
	}
	
	private void addPret() {
		this.information = new JLabel("Joueur trouvé. Démarrage de la partie...");
	    this.information.setBounds(434, 143, 300, 30);
	    this.add( this.information );
	}
	
	private void addNbCoup() {
		this.lblnbCoups = new JLabel("Nombre de coups :");
		this.lblnbCoups.setBounds(170, 300, 130, 50);
		this.add(this.lblnbCoups);
	}
	
	private void addNbPions() {
		this.lblnbPions = new JLabel("Nombre de pions :");
	    this.lblnbPions.setBounds(170, 260, 130, 50);
	    this.add(this.lblnbPions);
	}
	
	private void addNbCouleurs() {
		this.lblnbCouleurs = new JLabel("Nombre de couleurs :");
	    this.lblnbCouleurs.setBounds(170, 340, 130, 50);
	    this.add(this.lblnbCouleurs);
	}
	
	private void addCouleursMultiples() {
		this.lblcouleursMultiples = new JLabel("Couleurs multiples :");
	    this.lblcouleursMultiples.setBounds(170, 380, 130, 50);
	    this.add(this.lblcouleursMultiples);
	}
	
	private void addDificulte() {
		this.lbldifficulte = new JLabel("Difficulté :");
		this.lbldifficulte.setBounds(170, 220, 130, 50);
		this.add(this.lbldifficulte);
	}
	
	private void addJ1() {
		this.lbljoueur1 = new JLabel("Joueur 1");
	    this.lbljoueur1.setBounds(607, 220, 56, 50);
	    this.add(lbljoueur1);
	}

	private void addJ2() {
		this.lbljoueur2 = new JLabel("Joueur 2");
	    this.lbljoueur2.setBounds(607, 260, 56, 50);
	    this.add(this.lbljoueur2);
	}
	
	private void addJoueur() {
		this.lblJoueurs = new JLabel("Joueurs :");
	    this.lblJoueurs.setFont(new Font("Tahoma", Font.BOLD, 13));
	    this.lblJoueurs.setBounds(607, 190, 125, 30);
	    this.add(this.lblJoueurs);
	}
	
	private void addModifier() {
		this.modifier = new JButton("Modifier");
	    this.modifier.setBounds(170, 440, 200, 40);
	    this.modifier.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		fenetre.showMenu(Fenetre.CREER);
	    	}
	    });
	    this.add(this.modifier);
	}
	
	private void addLancer() {
		this.lancer = new JButton("Démarrer la partie");
	    this.lancer.setBounds(607, 310, 200, 40);
	    this.lancer.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    	}
	    });
	    this.add(this.lancer);
	}
	
	private void addKicker() {
		this.lancer = new JButton("Exclure le joueur");
	    this.lancer.setBounds(607, 360, 200, 40);
	    this.lancer.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    	}
	    });
	    this.add(this.lancer);
	}
	
	private void addQuitter() {
		this.retour = new JButton("Quitter");
	    this.retour.setBounds(607, 410, 200, 40);
	    this.retour.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		fenetre.showMenu(Fenetre.DEUXJOUEURS);
	    	}
	    });
	    this.add(this.retour);
	}

}
