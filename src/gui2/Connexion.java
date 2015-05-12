package gui2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Connexion extends JPanel{
	private static final long serialVersionUID = -2364882675854659595L;
	
	private Fenetre fenetre;
	
	public Connexion( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init(){
		this.setLayout( null );
		this.setBackground( Color.YELLOW );
		this.addBoutonRetour();
	}
	
	public void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds( 200, 0, 200, 50 );
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.ACCEUIL );
		      }
		    });
		this.add( btn );
	}
}
