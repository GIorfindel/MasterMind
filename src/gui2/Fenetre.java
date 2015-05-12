package gui2;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.Client;

public class Fenetre extends JFrame {
	
	private Client client;
	private CardLayout cl;
	private JPanel content;
	
	public static final String ACCEUIL = "0", JOUER = "1";
	
	public Fenetre(){
		this.setTitle( "Mastermind" );
	    this.setSize( 960, 544 );
	    this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    this.setLocationRelativeTo( null );
	    this.setResizable( false );
	    
	    this.client = new Client( "192.168.0.15", 15000 );
	    this.cl = new CardLayout();
	    this.initPanel();
	}
	
	private void initPanel(){
		this.content.add( new Accueil( this.client ), ACCEUIL );
		
		this.getContentPane().add( this.content, BorderLayout.CENTER );
	    this.setVisible( true );
	}
}
