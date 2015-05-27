package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;


/*
 * Quand le joueur a choisit une partie tu fais:
 * Paquet p = Paquet.creeDEMANDE_NOUV_JOUEUR2( nom_partie )
 * int id = p.getId();
 * fenetre.getClient().envoyerPaquet( p );
 * Paquet rep = fenetre.getClient().recevoirPaquet(x secondes, id);
 * if(rep==null){
 * 	Limite de temps dépassé
 * }else{
 * 	if(rep.getNbObjet()==0){
 * 		Partie non trouvé ou Partie pleine
 * 	}else{
 * 		Joueur j = (Joueur) p.getObjet(0);
 * 		Niveau n = (Niveau) p.getObjet(1);
 * 		fenetre.setInfoMultiAttente(n,j);
 * 		fenetre.showMenu(Fenetre.ATTENTEJOUEUR);
 * 	}
 * }
 * 
 * Pour demander la liste des parties:
 * Paquet p = Paquet.creeDEMANDE_LISTE_PARTIES( nom_partie )
 * int id = p.getId();
 * fenetre.getClient().envoyerPaquet( p );
 * Paquet rep = fenetre.getClient().recevoirPaquet(x secondes, id);
 * if(rep==null){
 * 	Limite de temps dépassé
 * }else{
 * 	Le nombre de partie disponibles: p.getNbObjet();
 * 	Tu as une partie avec cette methode:
 * 		(Multijoueur)p.getObjet(i)
 * 		Et tu regarde dans la classe Multijoueur pour avoir toutes les infos, par contre ne te sert pas de la variable joueur2 elle est à null
 * }
 */


@SuppressWarnings("serial")
public class DeuxJoueurs extends Menu{

	private Fenetre fenetre;
	@SuppressWarnings("rawtypes")
	private ArrayList listeParties;
	private static int X = 405, W = 200, H = 40;

	@SuppressWarnings("rawtypes")
	public DeuxJoueurs( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.listeParties = new ArrayList();
		this.init();

	}
	
	private void init() {
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addLabelChoixMode();
		this.addLabelPartiesDispo();
		this.addListeParties();
		this.addBoutonRejoindre();
		this.addBoutonCreer();
		this.addBoutonRafraichir();
		this.addBoutonRetour();

	}
	
	private void addLabelChoixMode(){
		JLabel lblMastermind = new JLabel( "Mode deux joueurs" );
		lblMastermind.setFont(new Font("Agency FB", Font.PLAIN, 40)); // Modification de la police
		lblMastermind.setBounds(170, 50, 660, 100);
		lblMastermind.setVerticalAlignment( SwingConstants.TOP );
		lblMastermind.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( lblMastermind );
	}
	
	private void addLabelPartiesDispo() {
	    JLabel lblPartiesDisponibles = new JLabel("Parties disponibles");
	    lblPartiesDisponibles.setFont(new Font("Agency FB", Font.PLAIN, 19));
	    lblPartiesDisponibles.setBounds(100, 130, 200, 50);
	    this.add(lblPartiesDisponibles);
	}
	
	@SuppressWarnings("rawtypes")
	private Object[][] arrayVersTab(ArrayList listeParties) {
		Object[][] tableau = new Object[listeParties.size()][6];
		int i=0;
		for (i = 0; i<listeParties.size(); i++){
			Object[] ligne = (Object[]) listeParties.get(i);
			for(int j = 0; j<6; j++) {
				tableau[i][j] = ligne[j];
			}
		}
		
		return tableau;
	}
	
	private void addListeParties() {

	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(100, 180, 770, 210);
	    this.add(scrollPane);
		
	    String[] nomsColonnes = {"Nom de la partie", "Difficulté", "Pions max", "Coups max", "Couleurs max", "Couleurs multiples"};
	   
		// Création d'un tableau que l'utilisateur ne peut pas modifier
	    JTable table = new JTable(arrayVersTab(this.listeParties), nomsColonnes){
	    	public boolean isCellEditable(int row, int column) {
	    		return false;
	    	}
	    };
	    
	    // Permet de ne sélection qu'une seule ligne
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    table.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    table.setCellSelectionEnabled(false);
	    table.setRowSelectionAllowed(true);
	    scrollPane.setViewportView(table);
	    table.setBackground(Color.WHITE);
	}
	
	private void addBoutonRejoindre(){
	    JButton btnRejoindre = new JButton("Rejoindre la partie");
	    btnRejoindre.setBounds(X, 410, W, H);
	    btnRejoindre.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){	
		    	  fenetre.showMenu( Fenetre.ATTENTEJOUEUR );
		      }
		    });
	    this.add(btnRejoindre);
	}
	
	private void addBoutonCreer(){
	    JButton btnCrerUnePartie = new JButton("Créer une partie");
	    btnCrerUnePartie.setBounds(X, 470, W, H);
	    btnCrerUnePartie.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  fenetre.showMenu( Fenetre.CREER );
		      }
		    });
	    this.add(btnCrerUnePartie);
	}
	
	private void addBoutonRafraichir(){
		JButton btn = new JButton( "Rafraîchir" );
		btn.setBounds(X, 530, W, H);
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		      }
		    });
		this.add( btn );
	}
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds(X, 590, W, H);
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  fenetre.showMenu( Fenetre.JOUER );
		      }
		    });
		this.add( btn );
	}
	
	@SuppressWarnings("unchecked")
	public void ajouterPartie(Object[] partie) {
		this.listeParties.add(partie);
	}
	
	public void supprimerPartie(int i) {
		this.listeParties.remove(i);
	}

}
