package gui2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Connexion extends Menu{
	private static final long serialVersionUID = -2364882675854659595L;
	
	private Fenetre fenetre;
	private JLabel information;
	
	public Connexion( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init(){
		this.setLayout( null );
		this.setBackground( Color.YELLOW );
		this.addBoutonRetour();
		this.addLabelInformation();
	}
	
	public void clic(){
		
	}
	
	public void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		int w = 100;
		btn.setBounds( (this.fenetre.getWidth()/2) - w/2, 300, w, 50 );
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.ACCEUIL );
		        fenetre.clicPanel( Fenetre.ACCEUIL );
		      }
		    });
		this.add( btn );
	}
	
	public void addLabelInformation(){
		this.information = new JLabel();
		int w = 200;
		this.information.setBounds( (this.fenetre.getWidth()/2) - w/2, 300, w, 50 );
		this.add( this.information );
	}
}
