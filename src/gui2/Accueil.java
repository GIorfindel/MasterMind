package gui2;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.Client;

public class Accueil extends JPanel{
	private Client client;
	
	private static int X = 405, Y = 170, W = 150, H = 50;
	
	public Accueil( Client client ){
		this.client = client;
		this.init();
	}
	
	private void init(){
		this.setLayout( null );
		this.setBackground( Color.blue );
		this.addLabelMastermind();
		this.addBoutonJouer();
	}
	
	private void addLabelMastermind(){
		JLabel lblMastermind = new JLabel( "Mastermind" );
		lblMastermind.setFont(new Font( "Tahoma", Font.PLAIN, 35 ) ); // Modification de la police
		lblMastermind.setBounds( 380, 43, 200, 50 );
		lblMastermind.setVerticalAlignment( SwingConstants.TOP );
		lblMastermind.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( lblMastermind );
	}
	
	private void addBoutonJouer(){
		JButton btnJouer = new JButton( "Jouer" );
		btnJouer.setBounds( X, Y, W, H );
	}

}
