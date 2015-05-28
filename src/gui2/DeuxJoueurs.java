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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import mastermind.Joueur;
import mastermind.Multijoueur;
import mastermind.Niveau;
import mastermind.Paquet;


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


public class DeuxJoueurs extends Menu{

	private Fenetre fenetre;
	private ArrayList listeParties;
	private static int X = 405, W = 200, H = 40;
	private JTable table;
	protected String nomPartie = null;

	public DeuxJoueurs( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.listeParties = new ArrayList();
		
		// Création d'un tableau que l'utilisateur ne peut pas modifier
	    this.table = new JTable(new TabListeParties(this.listeParties)){
	    	public boolean isCellEditable(int row, int column) {
	    		return false;
	    	}
	    };
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
	
	private void addListeParties() {

	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(100, 180, 770, 210);
	    	    
	    // Permet de ne sélection qu'une seule ligne
	    this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    SelectionListener listener = new SelectionListener(this.table);
	    this.table.getSelectionModel().addListSelectionListener(listener);
	    //this.table.getColumnModel().getSelectionModel().addListSelectionListener(listener);
	    
	    this.table.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    this.table.setCellSelectionEnabled(false);
	    this.table.setRowSelectionAllowed(true);
	    this.table.setBackground(Color.WHITE);	     	
	    
	    Paquet p = Paquet.creeDEMANDE_LISTE_PARTIES();
	    int id = p.getId();
	    fenetre.getClient().envoyerPaquet( p );
	    Paquet rep = fenetre.getClient().recevoirPaquet(3, id);
	    if(rep==null){
	    	System.out.println("Limite de temps dépassé");
	    }else{
	    	int nbParties = p.getNbObjet();
	    	
	    	for(int i =0; i< nbParties; i++) {
	    		Multijoueur multi = (Multijoueur) p.getObjet(i);
	    		
	    		String nom_partie = multi.getNom();
	    		String	niveau = multi.getNiveau().toString();
	    		int	pions_max = multi.getNiveau().getPions();
	    		int	coups_max = multi.getNiveau().getCoupMax();
	    		int	couleurs_max = multi.getNiveau().getCouleurs();
	    		boolean	couleurs_multiples = multi.getNiveau().getDouble();
	    		
	    		Object[] parametres = {nom_partie, niveau, pions_max, coups_max, couleurs_max, couleurs_multiples};
	    		
	    		ajouterPartie(parametres);
	    	}
	    }
	    rafraichir();
	    
	    
	    scrollPane.setViewportView(this.table);
	    this.add(scrollPane);
	    
	}
	
	class SelectionListener implements ListSelectionListener {
		JTable table;
		
		SelectionListener(JTable table) {
			this.table = table;
		}
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {
				int first = e.getFirstIndex();
				int last = e.getLastIndex();
			} 
			
			else if (e.getSource() == table.getColumnModel().getSelectionModel() && table.getColumnSelectionAllowed()) {
				int first = e.getFirstIndex();
				int last = e.getLastIndex();
			}
			if (e.getValueIsAdjusting()) {
				int selectedRowIndex = table.getSelectedRow();
				int selectedColumnIndex = 0;
				Object selectedObject = (Object) table.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
				nomPartie = (String) selectedObject;
			}
		}
		
	}
	
	private void addBoutonRejoindre(){
	    JButton btnRejoindre = new JButton("Rejoindre la partie");
	    btnRejoindre.setBounds(X, 410, W, H);
	    btnRejoindre.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  if(!nomPartie.equals(null)) {
		    		  Paquet p = Paquet.creeDEMANDE_NOUV_JOUEUR2( nomPartie );
		    		  int id = p.getId();
		    		  fenetre.getClient().envoyerPaquet( p );
		    		  Paquet rep = fenetre.getClient().recevoirPaquet(3, id);
		    		  if(rep==null){
		    				System.out.println("Limite de temps dépassé");
		    		  }else{
		    			  if(rep.getNbObjet()==0){
		    				  System.out.println("Partie non trouvée ou Partie pleine");
		    			  }else{
		    				  Joueur j = (Joueur) p.getObjet(0);
		    				  Niveau n = (Niveau) p.getObjet(1);
		    				  fenetre.setInfoMultiAttente(n,j);
		    				  fenetre.showMenu(Fenetre.ATTENTEJOUEUR);
		    			  }
		    		  }
		    	  } else {
		    		  System.out.println("Aucune partie sélectionnée");
		    	  }
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
		    	  
		    	  Paquet p = Paquet.creeDEMANDE_LISTE_PARTIES();
		    	  int id = p.getId();
		    	  fenetre.getClient().envoyerPaquet( p );
		    	  Paquet rep = fenetre.getClient().recevoirPaquet(3, id);
		    	  if(rep==null){
		    		  System.out.println("Limite de temps dépassé");
		    	  }else{
		    		  int nbParties = p.getNbObjet();
		  	    	
		    		  for(int i =0; i< nbParties; i++) {
		    			  Multijoueur multi = (Multijoueur) p.getObjet(i);
		    			  
		    			  String nom_partie = multi.getNom();
		    			  String	niveau = multi.getNiveau().toString();
		    			  int	pions_max = multi.getNiveau().getPions();
		    			  int	coups_max = multi.getNiveau().getCoupMax();
		    			  int	couleurs_max = multi.getNiveau().getCouleurs();
		    			  boolean	couleurs_multiples = multi.getNiveau().getDouble();
		  	    		
		    			  Object[] parametres = {nom_partie, niveau, pions_max, coups_max, couleurs_max, couleurs_multiples};
		    			  
		    			  ajouterPartie(parametres);
		    		  }
		    		  
		    		  rafraichir();
		    	  }
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
	
	public void rafraichir() {
		TabListeParties model = (TabListeParties) this.table.getModel();
		model.fireTableDataChanged();
	}

}
