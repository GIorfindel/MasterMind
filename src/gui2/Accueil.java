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
	private JButton profil, score, classement;
	private JLabel connectServeur;
	private JLabel connectCompte;
	
	private static int X = 405, Y = 160, W = 200, H = 50;
	
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
		this.addBoutonScores();
		this.addInfoClient();
		this.addBoutonClassement();
		this.addBoutonQuitter();

	}
	
	private void addLabelMastermind(){
		JLabel lblMastermind = new JLabel("Mastermind");
		lblMastermind.setFont(new Font("Agency FB", Font.PLAIN, 60)); // Modification de la police
		lblMastermind.setBounds(170, 50, 660, 100);
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
		this.profil.setToolTipText("vous devez-être connecté pour avoir accès à cette fonctionnalité");
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
		
		this.connectCompte = new JLabel("Joueur: Invité");
		this.connectCompte.setBounds(0,30,300,30);
		this.connectCompte.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(this.connectCompte);
	}
	
	private void addBoutonScores(){
		this.score = new JButton( "Scores" );
		score.setBounds( X, Y, W, H );
		Y += H+10;
		score.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.SCORE );
		      }
		    });
		this.score.setEnabled(false);
		this.score.setToolTipText("vous devez-être connecté pour avoir accès à cette fonctionnalité");
		this.add( score );
	}
	
	private void addBoutonClassement(){
		this.classement= new JButton( "Classement" );
		this.classement.setBounds( X, Y, W, H );
		Y += H+10;
		classement.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.CLASSEMENT );
		      }
		    });
		this.classement.setEnabled(false);
		this.classement.setToolTipText("vous devez-être connecté pour avoir accès à cette fonctionnalité");
		this.add( this.classement );
	}
	
	public void clic(){
		if( this.fenetre.getClient().connecterAuCompte() ){
			this.profil.setEnabled(true);
			this.profil.setToolTipText("");
			this.score.setEnabled(true);
			this.score.setToolTipText("");
			this.classement.setEnabled(true);
			this.classement.setToolTipText("");
			this.connectCompte.setText("Joueur: " + this.fenetre.getClient().getJoueur().getIdentifiant());
		}else{
			this.profil.setEnabled(false);
			this.connectCompte.setText("Joueur: Invité");
		}
		if(this.fenetre.getClient().getConnecteAuServeur()){
			this.connectServeur.setText("Connecté au serveur: oui");
		}else{
			this.connectServeur.setText("Connecté au serveur: non");
			this.classement.setEnabled(false);
			this.score.setEnabled(false);
		}
	}
	
	public void decoServeur(){
		this.clic();
	}

}
