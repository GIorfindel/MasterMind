package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Accueil extends JPanel{
	private static final long serialVersionUID = -5434763775202800235L;

	private Fenetre fenetre;
	
	private static int X = 405, Y = 170, W = 150, H = 50;
	
	public Accueil( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init(){
		this.setLayout( null );
		this.setBackground( Color.blue );
		this.addLabelMastermind();
		this.addBoutonConnexion();
	}
	
	private void addLabelMastermind(){
		JLabel lblMastermind = new JLabel( "Mastermind" );
		lblMastermind.setFont(new Font( "Tahoma", Font.PLAIN, 35 ) ); // Modification de la police
		lblMastermind.setBounds( 380, 43, 200, 50 );
		lblMastermind.setVerticalAlignment( SwingConstants.TOP );
		lblMastermind.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( lblMastermind );
	}
	
	private void addBoutonConnexion(){
		JButton btn = new JButton( "Connexion" );
		btn.setBounds( X, Y, W, H );
		Y += H;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.CONNEXION );
		      }
		    });
		this.add( btn );
	}

}
