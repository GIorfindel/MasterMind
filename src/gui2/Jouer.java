package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Jouer extends Menu{

	private Fenetre fenetre;
	
	private static int X = 405, Y = 170, W = 150, H = 50;
	
	public Jouer( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init() {
		this.setLayout( null );
		this.setBackground( Color.blue );
		this.addLabelChoixMode();
		this.addBoutonUnJoueur();
		this.addBouton2Joueurs();
		this.addBoutonRegles();
		this.addBoutonRetour();

	}
	
	private void addLabelChoixMode(){
		JLabel lblMastermind = new JLabel( "Choix du mode de jeu" );
		lblMastermind.setFont(new Font( "Tahoma", Font.PLAIN, 27 ) ); // Modification de la police
		lblMastermind.setBounds( 380, 43, 200, 50 );
		lblMastermind.setVerticalAlignment( SwingConstants.TOP );
		lblMastermind.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( lblMastermind );
	}
	
	private void addBoutonUnJoueur(){
		JButton btn = new JButton( "Un joueur" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.UNJOUEUR );
		      }
		    });
		this.add( btn );
	}
	
	private void addBouton2Joueurs(){
		JButton btn = new JButton( "Deux joueurs" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.DEUXJOUEURS );
		      }
		    });
		this.add( btn );
	}
	
	private void addBoutonRegles(){
		JButton btn = new JButton( "Règles du jeu" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.REGLES );
		      }
		    });
		this.add( btn );
	}
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  fenetre.showMenu( Fenetre.ACCUEIL );
		      }
		    });
		this.add( btn );
	}

}
