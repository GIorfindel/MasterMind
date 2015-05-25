package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MScore extends Menu{
	
	private Fenetre fenetre;
	
	public MScore( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init(){
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addLabelTitre();
		this.addBoutonRetour();
	}
	
	private void addLabelTitre(){
		JLabel titre = new JLabel("Score");
		titre.setFont(new Font("Agency FB", Font.PLAIN, 40));
		titre.setBounds(170, 50, 660, 100);
		titre.setVerticalAlignment( SwingConstants.TOP );
		titre.setHorizontalAlignment( SwingConstants.CENTER );
	    this.add(titre);
	}
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds(170, 200, 660, 100);
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.ACCUEIL );
		      }
		    });
		this.add( btn );
	}
}
