package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.SwingConstants;

import mastermind.Joueur;
import mastermind.Niveau;
import mastermind.Paquet;

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
	
	private Niveau niveau;
	private Joueur j;//peut être le joueur1 ou 2, ca depend de la valeur de createur
	private boolean createur;

	
	private static int X = 405, W = 200, H = 40;

	public AttenteJoueur( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.niveau = null;
		this.init();
		this.createur = false;
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
//		this.addModifier(); On le fera après
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
		this.information.setText("Joueur trouvé. Démarrage de la partie...");
	}
	
	private void popPret(){
		this.information.setText("Attente d'un joueur...");
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
	    		fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_JOUER_MULTI() );
	    		//A faire**************************************************************************************************
	    	}
	    });
	    this.add(this.lancer);
	}
	
	private void addKicker() {
		this.kicker = new JButton("Exclure le joueur");
	    this.kicker.setBounds(607, 360, 200, 40);
	    this.kicker.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_KICKER_JOUEUR2() );
	    		lancer.setEnabled(false);
	    		kicker.setEnabled(false);
	    		j = null;
	    		refreshJoueur2();
	    	}
	    });
	    this.add(this.lancer);
	}
	
	private void addQuitter() {
		this.retour = new JButton("Quitter");
	    this.retour.setBounds(607, 410, 200, 40);
	    this.retour.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		if( createur ){
	    			fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_JOUEUR1_PARTI() );
	    		}else{
	    			fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_JOUEUR2_PARTI() );
	    		}
	    		quitter();
	    		fenetre.showMenu(Fenetre.DEUXJOUEURS);
	    	}
	    });
	    this.add(this.retour);
	}
	
	public void setInfoMultiAttente( Niveau n, Joueur j ){
		this.niveau = n;
		this.createur = true;
		if( j != null ){
			this.createur = false;
			this.j = j;
		}
	}
	
	private void quitter(){
		this.niveau = null;
		this.j = null;
		this.createur = false;
		this.lancer.setVisible(false);
		this.kicker.setVisible(false);
		this.lancer.setEnabled(false);
		this.kicker.setEnabled(false);
		this.refreshJoueur2();
	}
	
	public void clic(){
		if( this.createur ){
			this.popPret();
			this.lancer.setVisible(true);
			this.kicker.setVisible(true);
			this.lancer.setEnabled(true);
			this.kicker.setEnabled(true);
			this.refreshNiveau();
			this.refreshJoueur1();
		}else{
			this.lancer.setVisible(false);
			this.kicker.setVisible(false);
			this.lancer.setEnabled(false);
			this.kicker.setEnabled(false);
			this.refreshNiveau();
			this.refreshJoueur1();
			this.refreshJoueur2();
		}
	}
	
	//Si on est le createur(true)
	public void joueur2Arrive( Joueur j2 ){
		this.j = j2;
		this.lancer.setEnabled(true);
		this.kicker.setEnabled(true);
		this.refreshJoueur2();
	}
	
	//Si on est le createur(true)
	public void joueur2Pars(){
		this.j = null;
		this.lancer.setEnabled(false);
		this.kicker.setEnabled(false);
		this.refreshJoueur2();
	}
	
	//Si on n'est pas le createur(false)
	public void joueur1Pars(){
		quitter();
		fenetre.showMenu(Fenetre.DEUXJOUEURS);
	}
	
	//Si on n'est pas le createur(false)
	public void tuEsKick(){
		quitter();
		fenetre.showMenu(Fenetre.DEUXJOUEURS);
	}
	
	public void decoServeur(){
		this.quitter();
		this.fenetre.showMenu(Fenetre.DEUXJOUEURS);
	}
	
	public void refreshNiveau(){
		this.lblnbCoups.setText("Nombre de coups :"+this.niveau.getCoupMax());
		this.lblnbPions.setText("Nombre de pions :"+this.niveau.getPions());
		this.lblnbCouleurs.setText("Nombre de couleurs :"+this.niveau.getCouleurs());
		this.lblcouleursMultiples.setText("Couleurs multiples :"+this.niveau.getDouble());
		this.lbldifficulte.setText("Difficulté :"+this.niveau.toString());
	}
	
	public void refreshJoueur1(){
		Joueur j = this.fenetre.getClient().getJoueur();
		this.lbljoueur1.setText("Joueur 1 :"+j.getIdentifiant()+" Malus :"+j.getMalus());
	}
	
	public void refreshJoueur2(){
		if( this.j != null ){
			this.lbljoueur2.setText("Joueur 2 :"+this.j.getIdentifiant()+" Malus :"+this.j.getMalus());
		}else{
			this.lbljoueur2.setText("Aucun joueur 2");
		}
	}

}
