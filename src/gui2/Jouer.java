package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import mastermind.Couleur;
import mastermind.Niveau;
import mastermind.NiveauPerso;
import mastermind.Paquet;
import mastermind.Solo;

@SuppressWarnings("serial")
public class Jouer extends Menu{

	private Fenetre fenetre;
	private JButton unjoueur, deuxjoueurs, charger, regles;
	private JLabel connectServeur, connectCompte, information;
	private Solo solo;
	
	private static int X = 405, Y = 160, W = 200, H = 50;
	
	public Jouer( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init() {
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addLabelChoixMode();
		this.addBoutonUnJoueur();
		this.addBouton2Joueurs();
		this.addBoutonCharger();
		this.addBoutonRegles();
		this.addBoutonRetour();
		this.addInfoClient();

	}
	
	private void addLabelChoixMode(){
		JLabel lblMastermind = new JLabel( "Choix du mode" );
		lblMastermind.setFont(new Font("Agency FB", Font.PLAIN, 40)); // Modification de la police
		lblMastermind.setBounds(170, 50, 660, 100);
		lblMastermind.setVerticalAlignment( SwingConstants.TOP );
		lblMastermind.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( lblMastermind );
	}
	
	private void addBoutonUnJoueur(){
		this.unjoueur = new JButton( "Un joueur" );
		this.unjoueur .setBounds( X, Y, W, H );
		Y += H+10;
		this.unjoueur .addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.UNJOUEUR );
		      }
		    });
		this.add( this.unjoueur  );
	}
	
	private void addBouton2Joueurs(){
		this.deuxjoueurs  = new JButton( "Deux joueurs" );
		this.deuxjoueurs.setBounds( X, Y, W, H );
		Y += H+10;
		this.deuxjoueurs.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.DEUXJOUEURS );
		      }
		    });
		this.add( this.deuxjoueurs );
	}
	
	private void addBoutonCharger(){
		this.charger = new JButton( "Charger" );
		this.charger.setBounds( X, Y, W, H );
		Y += H+10;
		this.charger.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if( fenetre.getClient().connecterAuCompte()){
					Paquet p = Paquet.creeDEMANDE_CHARGER_SOLO();
					int id = p.getId();
					fenetre.getClient().envoyerPaquet( p );
					Paquet ps = fenetre.getClient().recevoirPaquet(5.0, id );
					
					if(ps != null) {
						
						if(ps.getNbObjet() == 0) {
							information = new JLabel("Partie introuvable");
						}
						
						else {
							Solo s = Paquet.getSolo(ps);
							fenetre.setSoloCharger(s);
							fenetre.showMenu( Fenetre.SOLO );
						}
					}else{
						information = new JLabel("Impossible de charger votre partie");
					}
				}
			}
		});
		this.add( this.charger );
	}
	
	private void addBoutonRegles(){
		this.regles = new JButton( "Règles du jeu" );
		this.regles.setBounds( X, Y, W, H );
		Y += H+10;
		this.regles.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.REGLES );
		      }
		    });
		this.add( this.regles );
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
			this.charger.setEnabled(true);
			this.deuxjoueurs.setEnabled(true);
			this.connectCompte.setText("Joueur: " + this.fenetre.getClient().getJoueur().getIdentifiant());
		}else{
			this.charger.setEnabled(false);
			this.deuxjoueurs.setEnabled(false);
			this.connectCompte.setText("Joueur: Invité");
		}
		if(this.fenetre.getClient().getConnecteAuServeur()){
			this.connectServeur.setText("Connecté au serveur: oui");
		}else{
			this.connectServeur.setText("Connecté au serveur: non");
		}
	}

}
