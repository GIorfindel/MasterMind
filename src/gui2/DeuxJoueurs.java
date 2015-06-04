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
import mastermind.Joueur;
import mastermind.Multijoueur;
import mastermind.Niveau;
import mastermind.Paquet;



@SuppressWarnings("serial")
public class DeuxJoueurs extends Menu{

	private Fenetre fenetre;
	private ArrayList<Object[]> listeParties;
	private static int X = 405, W = 200, H = 40;
	private JTable table;
	protected String nomPartie = null;
	private JLabel information;

	public DeuxJoueurs( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.listeParties = new ArrayList<Object[]>();
		
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
		this.addInformation();

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
	
	private void addInformation() {
		this.information = new JLabel("");
		this.information.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.information.setForeground(Color.red);
		this.information.setBounds(100, 400, 660, 30);
		this.add(this.information);
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
	    
	    scrollPane.setViewportView(this.table);
	    this.add(scrollPane);
	    
	}
	
	private void addBoutonRejoindre(){
	    JButton btnRejoindre = new JButton("Rejoindre la partie");
	    btnRejoindre.setBounds(X, 410, W, H);
	    btnRejoindre.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  if(nomPartie != null) {
		    		  Paquet p = Paquet.creeDEMANDE_NOUV_JOUEUR2( nomPartie );
		    		  int id = p.getId();
		    		  fenetre.getClient().envoyerPaquet( p );
		    		  Paquet rep = fenetre.getClient().recevoirPaquet(3, id);
		    		  if(rep==null){
		    			  information.setText("Limite de temps dépassée");
		    		  }else{
		    			  if(rep.getNbObjet()==0){
		    				  information.setText("Partie non trouvée ou partie complète");
		    			  }else{
		    				  Joueur j = (Joueur) rep.getObjet(0);
		    				  Niveau n = (Niveau) rep.getObjet(1);
		    				  fenetre.setInfoMultiAttente(n,j);
		    				  fenetre.showMenu(Fenetre.ATTENTEJOUEUR);
		    			  }
		    		  }
		    	  } else {
		    		  information.setText("Aucune partie sélectionnée");
		    	  }
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
		    	  requeteParties();
		    	  rafraichir();
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
	
	public void requeteParties() {
	 this.listeParties.clear();
	 Paquet p = Paquet.creeDEMANDE_LISTE_PARTIES();
	    int id = p.getId();
	    fenetre.getClient().envoyerPaquet( p );
	    Paquet rep = fenetre.getClient().recevoirPaquet(3, id);
	    if(rep==null){
	    	information.setText("Limite de temps dépassée");
	    }else{
	    	int nbParties = rep.getNbObjet();
	    	if(nbParties == 0){
	    		information.setText("Aucune partie trouvée");
	    	}
	    	else {
	    		information.setText("");
		    	for(int i =0; i< nbParties; i++) {
		    		Multijoueur multi = (Multijoueur) rep.getObjet(i);
		    		
		    		String nom_partie = multi.getNom();
		    		String	niveau = multi.getNiveau().getNomNiveau();
		    		int	pions_max = multi.getNiveau().getPions();
		    		int	coups_max = multi.getNiveau().getCoupMax();
		    		int	couleurs_max = multi.getNiveau().getCouleurs();
		    		boolean	couleurs_multiples = multi.getNiveau().getDouble();
		    		String coul_mul;
		    		if(couleurs_multiples) {
		    			coul_mul = "Oui";
		    		}
		    		else {
		    			coul_mul = "Non";
		    		}
		    		
		    		Object[] parametres = {nom_partie, niveau, pions_max, coups_max, couleurs_max, coul_mul};
		    		
		    		ajouterPartie(parametres);
		    	}
	    	}
	    }
	}
	
	public void clic() {
		requeteParties();
	    rafraichir();
	}
	
	/**********************Listener pour la sélection*************************/
	class SelectionListener implements ListSelectionListener {
		JTable table;
		
		SelectionListener(JTable table) {
			this.table = table;
		}
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) {
				int selectedRowIndex = table.getSelectedRow();
				if( selectedRowIndex == -1 ){
					nomPartie = null;
					return;
				}
				int selectedColumnIndex = 0;
				Object selectedObject = (Object) table.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
				nomPartie = (String) selectedObject;
			}
		}
		
	}
	/******************************************************************************/
	

}
