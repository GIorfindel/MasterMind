package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DeuxJoueurs extends Menu{

	private Fenetre fenetre;
		
	public DeuxJoueurs( Fenetre fenetre ){
		this.fenetre = fenetre;
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
		lblMastermind.setFont(new Font("Agency FB", Font.PLAIN, 50)); // Modification de la police
		lblMastermind.setBounds(150, 30, 660, 100);
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
		Object[][] data = {
				{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Oui"},
	    		{"Supraime", "Difficile", "4", "10", "43", "Non"}
		};
	    
		String[] nomsColonnes = {"Nom de la partie", "Difficulté", "Pions max", "Coups max", "Couleurs max", "Couleurs multiples"};
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(100, 180, 770, 210);
	    this.add(scrollPane);
	    
	    // Création d'un tableau que l'utilisateur ne peut pas modifier
	    JTable table = new JTable(data, nomsColonnes){
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
	    btnRejoindre.setBounds(405, 410, 150, 50);
	    btnRejoindre.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		      }
		    });
	    this.add(btnRejoindre);
	}
	
	private void addBoutonCreer(){
	    JButton btnCrerUnePartie = new JButton("Créer une partie");
	    btnCrerUnePartie.setBounds(405, 470, 150, 50);
	    btnCrerUnePartie.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  fenetre.showMenu( Fenetre.CREER );
		      }
		    });
	    this.add(btnCrerUnePartie);
	}
	
	private void addBoutonRafraichir(){
		JButton btn = new JButton( "Rafraîchir" );
		btn.setBounds(405, 530, 150, 50);
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		      }
		    });
		this.add( btn );
	}
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds(405, 590, 150, 50);
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  fenetre.showMenu( Fenetre.JOUER );
		      }
		    });
		this.add( btn );
	}

}
