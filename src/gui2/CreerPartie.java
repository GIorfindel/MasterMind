package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import mastermind.Niveau;
import mastermind.Paquet;

@SuppressWarnings("serial")
public class CreerPartie extends Menu{

	private Fenetre fenetre;
	
	private static int X = 405, Y = 160, W = 200, H = 50;
	
	public CreerPartie( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init() {
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addLabelChoixNiveau();
		this.addBoutonTresFacile();
		this.addBoutonFacile();
		this.addBoutonIntermediaire();
		this.addBoutonDifficile();
		this.addBoutonTresDifficile();
//		this.addBoutonPersonnaliser(); On fait pas cette fonctionnalité pour l'instant, trop compliqué à sauvegarder
		this.addBoutonRetour();

	}
	
	private void addLabelChoixNiveau(){
		JLabel lblMastermind = new JLabel( "Choisir une difficulté" );
		lblMastermind.setFont(new Font("Agency FB", Font.PLAIN, 40)); // Modification de la police
		lblMastermind.setBounds(170, 50, 660, 100);
		lblMastermind.setVerticalAlignment( SwingConstants.TOP );
		lblMastermind.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( lblMastermind );
	}
	
	private void addBoutonTresFacile(){
		JButton btn = new JButton( "Très facile" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_CREE_MULTI( Niveau.niveauString( "TresFacile" ) ) );
		    	  fenetre.setInfoMultiAttente( Niveau.niveauString( "TresFacile" ), null );
		    	  fenetre.showMenu( Fenetre.ATTENTEJOUEUR );
		      }
		    });
		this.add( btn );
	}
	
	private void addBoutonFacile(){
		JButton btn = new JButton( "Facile" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_CREE_MULTI( Niveau.niveauString( "Facile" ) ) );
		    	  fenetre.setInfoMultiAttente( Niveau.niveauString( "Facile" ), null );
		    	  fenetre.showMenu( Fenetre.ATTENTEJOUEUR );
		      }
		    });
		this.add( btn );
	}
	
	private void addBoutonIntermediaire(){
		JButton btn = new JButton( "Intermédiaire" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_CREE_MULTI( Niveau.niveauString( "Normal" ) ) );
		    	  fenetre.setInfoMultiAttente( Niveau.niveauString( "Normal" ), null );
		    	  fenetre.showMenu( Fenetre.ATTENTEJOUEUR );
		      }
		    });
		this.add( btn );
	}
	
	private void addBoutonDifficile(){
		JButton btn = new JButton( "Difficile" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_CREE_MULTI( Niveau.niveauString( "Difficile" ) ) );
		    	  fenetre.setInfoMultiAttente( Niveau.niveauString( "Difficile" ), null );
		    	  fenetre.showMenu( Fenetre.ATTENTEJOUEUR );
		      }
		    });
		this.add( btn );
	}

	private void addBoutonTresDifficile(){
		JButton btn = new JButton( "Très difficile" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  fenetre.getClient().envoyerPaquet( Paquet.creeDEMANDE_CREE_MULTI( Niveau.niveauString( "TresDifficile" ) ) );
		    	  fenetre.setInfoMultiAttente( Niveau.niveauString( "TresDifficile" ), null );
		    	  fenetre.showMenu( Fenetre.ATTENTEJOUEUR );
		      }
		    });
		this.add( btn );
	}
	
	private void addBoutonPersonnaliser(){
		JButton btn = new JButton( "Personnaliser" );
		btn.setBounds( X, Y, W, H );
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.PERSONNALISERMULTI );
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
		    	  fenetre.showMenu( Fenetre.DEUXJOUEURS );
		      }
		    });
		this.add( btn );
	}

}
