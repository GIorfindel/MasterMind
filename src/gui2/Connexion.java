package gui2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
		this.setBackground( Color.WHITE );
		this.addBoutonRetour();
		this.addLabelInformation();
		this.addIdentifiant();
		this.addMDP();
		this.addValider();
	}
	
	public void clic(){
		
	}
	
	public void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds( 405, 300, 150, 50 );
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.ACCEUIL );
		      }
		    });
		this.add( btn );
	}
	
	public void addLabelInformation(){
		this.information = new JLabel("Informations");
		int w = 200;
		this.information.setBounds( (this.fenetre.getWidth()/2) - w/2, 300, w, 50 );
		this.add( this.information );
	}
	

	public void addIdentifiant(){
		JLabel lblIdentifiant = new JLabel("Identifiant");
	    lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    lblIdentifiant.setBounds(345, 120, 89, 27);
	    this.add(lblIdentifiant);
	    
	    JTextField textField = new JTextField();
	    lblIdentifiant.setLabelFor(textField);
	    textField.setBounds(492, 124, 176, 22);
	    this.add(textField);
	    textField.setColumns(10);
	}
	
	public void addMDP(){
		JLabel lblMotDePasse = new JLabel("Mot de passe");
	    lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblMotDePasse.setBounds(345, 178, 98, 16);
	    this.add(lblMotDePasse);
	    
	    JTextField textField_1 = new JTextField();
	    lblMotDePasse.setLabelFor(textField_1);
	    textField_1.setBounds(492, 176, 176, 22);
	    this.add(textField_1);
	    textField_1.setColumns(10);
	}
	
	public void addValider(){
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(405, 240, 150, 50);
	    btnValider.setForeground(Color.BLACK);
	    btnValider.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		//Envoi au serveur
	    	}
	    });
	    this.add(btnValider);
	}
	
}
