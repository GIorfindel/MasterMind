package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Personnaliser extends Menu{

	private Fenetre fenetre;
	
	private static int X = 405, Y = 130, W = 150, H = 40;
	
	public Personnaliser( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init() {
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addLabelChoixMode();
		this.addBoutonRetour();
	}
	
	private void addLabelChoixMode(){
		JLabel lblMastermind = new JLabel( "Règles du jeu" );
		lblMastermind.setFont(new Font("Agency FB", Font.PLAIN, 50)); // Modification de la police
		lblMastermind.setBounds(175, 30, 600, 100);;
		lblMastermind.setVerticalAlignment( SwingConstants.TOP );
		lblMastermind.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( lblMastermind );
	}
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  fenetre.showMenu( Fenetre.UNJOUEUR );
		      }
		    });
		this.add( btn );
	}

}
