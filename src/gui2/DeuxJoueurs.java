package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
		this.addBoutonRetour();

	}
	
	private void addLabelChoixMode(){
		JLabel lblMastermind = new JLabel( "Mode deux joueurs" );
		lblMastermind.setFont(new Font("Agency FB", Font.PLAIN, 50)); // Modification de la police
		lblMastermind.setBounds(175, 30, 600, 100);
		lblMastermind.setVerticalAlignment( SwingConstants.TOP );
		lblMastermind.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( lblMastermind );
	}
	
	private void addLabelPartiesDispo() {
	    JLabel lblPartiesDisponibles = new JLabel("Parties disponibles");
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
	    scrollPane.setBounds(100, 130, 770, 210);
	    this.add(scrollPane);
	    JTable table = new JTable(data, nomsColonnes);
	    table.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    scrollPane.setViewportView(table);
	    table.setBackground(Color.WHITE);
	}
	
	private void addBoutonRejoindre(){
	    JButton btnRejoindre = new JButton("Rejoindre la partie");
	    btnRejoindre.setBounds(174, 368, 150, 40);
	    btnRejoindre.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		      }
		    });
	    this.add(btnRejoindre);
	}
	
	private void addBoutonCreer(){
	    JButton btnCrerUnePartie = new JButton("Créer une partie");
	    btnCrerUnePartie.setBounds(174, 421, 150, 40);
	    btnCrerUnePartie.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  fenetre.showMenu( Fenetre.PERSONNALISER );
		      }
		    });
	    this.add(btnCrerUnePartie);
	}
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds(657, 421, 150, 40);
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  fenetre.showMenu( Fenetre.UNJOUEUR );
		      }
		    });
		this.add( btn );
	}

}
