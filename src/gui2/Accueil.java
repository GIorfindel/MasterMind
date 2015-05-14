package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Accueil extends Menu{

	private Fenetre fenetre;
	private JButton profil;
	private JLabel connectServeur;
	private JLabel connectCompte;
	
	private static int X = 405, Y = 160, W = 150, H = 50;
	
	public Accueil( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init(){
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addLabelMastermind();
		this.addBoutonJouer();
		this.addBoutonConnexion();
		this.addBoutonInscription();
		this.addBoutonProfil();
		this.addInfoClient();
		this.addBoutonQuitter();

	}
	
	private void addLabelMastermind(){
		JLabel lblMastermind = new JLabel("Mastermind");
		lblMastermind.setFont(new Font("Agency FB", Font.PLAIN, 66)); // Modification de la police
		lblMastermind.setBounds(150, 30, 660, 100);
		lblMastermind.setVerticalAlignment( SwingConstants.TOP );
		lblMastermind.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( lblMastermind );
	}
	
	private void addBoutonJouer(){
		JButton btn = new JButton( "Jouer" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.JOUER );
		      }
		    });
		this.add( btn );
	}
	
	private void addBoutonConnexion(){
		JButton btn = new JButton( "Connexion" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.CONNEXION );
		      }
		    });
		this.add( btn );
	}
	
	private void addBoutonInscription(){
		JButton btn = new JButton( "Inscription" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.INSCRIPTION );
		      }
		    });
		this.add( btn );
	}
	
	private void addBoutonProfil(){
		this.profil = new JButton( "Profil" );
		this.profil.setBounds( X, Y, W, H );
		Y += H+10;
		this.profil.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		        fenetre.showMenu( Fenetre.PROFIL );
		      }
		    });
		this.profil.setEnabled(false);
		this.add( this.profil );
	}
	
	private void addBoutonQuitter(){
		JButton btn = new JButton( "Quitter" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.dispose();
		      }
		    });
		this.add( btn );
	}
	
	private void addInfoClient(){
		this.connectServeur = new JLabel("Connecté au serveur: non");
		this.connectServeur.setBounds(0,0,300,30);
		this.connectServeur.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(this.connectServeur);
		
		this.connectCompte = new JLabel("Joueur: invité");
		this.connectCompte.setBounds(0,30,300,30);
		this.connectCompte.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(this.connectCompte);
	}
	
	public void clic(){
		if( this.fenetre.getClient().connecterAuCompte() ){
			this.profil.setEnabled(true);
			this.connectCompte.setText("Joueur: " + this.fenetre.getClient().getJoueur().getIdentifiant());
		}else{
			this.profil.setEnabled(false);
			this.connectCompte.setText("Joueur: invité");
		}
		if(this.fenetre.getClient().getConnecteAuServeur()){
			this.connectServeur.setText("Connecté au serveur: oui");
		}else{
			this.connectServeur.setText("Connecté au serveur: non");
		}
	}

}
