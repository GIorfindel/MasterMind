package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class UnJoueur extends Menu{

	private Fenetre fenetre;
	
	private static int X = 405, Y = 130, W = 150, H = 40;
	
	public UnJoueur( Fenetre fenetre ){
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
		this.addBoutonPersonnaliser();
		this.addBoutonRetour();

	}
	
	private void addLabelChoixNiveau(){
		JLabel lblMastermind = new JLabel( "Choix du niveau de difficulté" );
		lblMastermind.setFont(new Font("Agency FB", Font.PLAIN, 50)); // Modification de la police
		lblMastermind.setBounds(175, 30, 600, 100);
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
		        fenetre.showMenu( Fenetre.UNJOUEUR );
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
		        fenetre.showMenu( Fenetre.DEUXJOUEURS );
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
		        fenetre.showMenu( Fenetre.REGLES );
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
		        fenetre.showMenu( Fenetre.REGLES );
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
		        fenetre.showMenu( Fenetre.REGLES );
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
		        fenetre.showMenu( Fenetre.REGLES );
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
		    	  fenetre.showMenu( Fenetre.JOUER );
		      }
		    });
		this.add( btn );
	}

}
