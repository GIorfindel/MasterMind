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
	
	public static final String ACCUEIL = "0", CONNEXION = "1", INSCRIPTION = "3", JOUER = "4", 
			UNJOUEUR = "5", DEUXJOUEURS = "6", REGLES = "7";
	private Menu accueil, connexion, inscription, jouer;
	
	public Fenetre(){
		this.setTitle( "Mastermind" );
	    this.setSize( 960, 544 );
	    this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    this.setLocationRelativeTo( null );
	    this.setResizable( false );
	    
	    this.client = new Client( "192.168.0.15", 15000 );
	    this.cl = new CardLayout();
	    this.content = new JPanel();
	    content.setLayout( cl );
	    this.initPanel();
	}
	
	public Client getClient(){
		return this.client;
	}
	
	public void showMenu( final String menu ){
		this.cl.show( this.content, menu);
		this.clicPanel( menu );
	}
	
	private void initPanel(){
		this.accueil = new Accueil( this );
		this.connexion = new Connexion( this );
		this.inscription = new Inscription( this );
		this.jouer = new Jouer( this );
		this.content.add( this.accueil, ACCUEIL );
		this.content.add( this.connexion, CONNEXION );
		this.content.add( this.inscription, INSCRIPTION );
		this.content.add( this.jouer, JOUER );


		
		this.getContentPane().add( this.content, BorderLayout.CENTER );
	    this.setVisible( true );
	}
	
	public void clicPanel( final String menu ){
		if( menu.equals( ACCUEIL ) ){
			this.accueil.clic();
		}else if( menu.equals( CONNEXION ) ){
			this.connexion.clic();
		}else if( menu.equals( INSCRIPTION ) ){
			this.inscription.clic();
		}
		else if( menu.equals( JOUER ) ){
			this.jouer.clic();
		}
	}
}
