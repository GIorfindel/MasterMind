package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Regles extends Menu{

	private Fenetre fenetre;
	
	private static int X = 405, Y = 570, W = 150, H = 50;
	
	public Regles( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init() {
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addLabelChoixMode();
		this.addRegles();
		this.addBoutonRetour();
	}
	
	private void addLabelChoixMode(){
		JLabel lblMastermind = new JLabel( "Règles du jeu" );
		lblMastermind.setFont(new Font("Agency FB", Font.PLAIN, 50)); // Modification de la police
		lblMastermind.setBounds(150, 30, 660, 100);
		lblMastermind.setVerticalAlignment( SwingConstants.TOP );
		lblMastermind.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( lblMastermind );
	}
	
	private void addRegles() {
		JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(100, 140, 770, 400);
	    this.add(scrollPane);
	    JLabel txtRegles = new JLabel();
	    txtRegles.setText("<html><center><h3>Instructions</h3></center> <br/> Le but du jeu est de trouver la bonne combinaison de couleur, g&eacute;n&eacute;r&eacute;e al&eacute;atoirement.<br/>Il est possible qu'une couleur apparaisse plusieurs fois dans la solution. <br/> Pour cela, vous avez &agrave; disposition, des boutons de diff&eacute;rentes couleurs.<br/> Lorsque vous cliquez sur l'un d'eux, la couleur choisie se met au bon endroit sur le plateau de jeu.<br/><br/>Une fois la ligne remplie, vous pouvez valider en cliquant sur le bouton correspondant.<br/><br/>Le plateau se met donc &agrave; jour, en affichant sur la droite de votre proposition, des petits pions : <ul><li>Noir: Bonne couleur, bonne place</li> <li>Blanc : Bonne couleur, mauvaise place </li> <li>Aucun pion : Mauvaise couleur</li></ul> La place des petits pions n'a aucune relation avec la place de vos couleurs, le premier petit pion<br/> n'indique pas forc&eacute;ment votre premi&egrave;re couleur.<br/><br/>Vous avez au maximum, 10 tentatives pour trouver la bonne combinaison.<br/><br/><center><h3>Bonne chance !</h3</center> </html>");
	    txtRegles.setHorizontalAlignment( SwingConstants.CENTER );;
	    scrollPane.setViewportView(txtRegles);
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
