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
	
	public static final String ACCEUIL = "0", CONNEXION = "1";
	
	public Fenetre(){
		this.setTitle( "Mastermind" );
	    this.setSize( 960, 544 );
	    this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    this.setLocationRelativeTo( null );
	    this.setResizable( false );
	    
	    this.client = new Client( "192.168.0.15", 15000 );
	    this.cl = new CardLayout();
	    this.content = new JPanel();
	    content.setLayout(cl);
	    this.initPanel();
	}
	
	public Client getClient(){
		return this.client;
	}
	
	public void showMenu( final String menu ){
		this.cl.show( this.content, menu);
	}
	
	private void initPanel(){
		this.content.add( new Accueil( this ), ACCEUIL );
		this.content.add( new Connexion( this ), CONNEXION );
		
		this.getContentPane().add( this.content, BorderLayout.CENTER );
	    this.setVisible( true );
	}
}
