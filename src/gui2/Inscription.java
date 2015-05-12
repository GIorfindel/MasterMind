package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import mastermind.Joueur;
import mastermind.Paquet;

public class Inscription extends Menu{
	
	private Fenetre fenetre;
	private JLabel titre;
	private JLabel information;
	private JTextField identifiant;
	private JTextField mdp;
	private JButton valider;
	
	public Inscription( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init(){
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addLabelTitre();
		this.addBoutonRetour();
		this.addLabelInformation();
		this.addIdentifiant();
		this.addMDP();
		this.addValider();
	}
	
	public void clic(){
		if( this.fenetre.getClient().seConnecterAuServeur() ){
			this.PasGriser();
		}else{
			this.griser();
			this.information.setText( "Vous n'êtes pas connecté au réseau" );
		}
	}
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds( 405, 300, 150, 50 );
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.ACCUEIL );
		      }
		    });
		this.add( btn );
	}
	
	private void addLabelTitre() {
		this.titre = new JLabel("Inscription");
		this.titre.setFont(new Font("Tahoma", Font.PLAIN, 25));
		this.titre.setBounds(405, 43, 141, 70);
	    this.add(this.titre);
	}
	
	private void addLabelInformation(){
		this.information = new JLabel("Informations");
		this.information.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.information.setForeground( Color.red );
		this.information.setBounds( 345, 360, 400, 27 );
		this.add( this.information );
	}
	

	private void addIdentifiant(){
		JLabel lblIdentifiant = new JLabel("Identifiant");
	    lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    lblIdentifiant.setBounds(345, 120, 89, 27);
	    this.add(lblIdentifiant);
	    
	    this.identifiant = new JTextField();
	    lblIdentifiant.setLabelFor( this.identifiant );
	    this.identifiant.setBounds(492, 124, 176, 22);
	    this.add( this.identifiant );
	    this.identifiant.setColumns(10);
	}
	
	private void addMDP(){
		JLabel lblMotDePasse = new JLabel("Mot de passe");
	    lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblMotDePasse.setBounds(345, 178, 98, 16);
	    this.add(lblMotDePasse);
	    
	    this.mdp = new JTextField();
	    lblMotDePasse.setLabelFor(this.mdp);
	    this.mdp.setBounds(492, 176, 176, 22);
	    this.add(this.mdp);
	    this.mdp.setColumns(10);
	}
	
	private void addValider(){
		this.valider = new JButton("Valider");
		this.valider.setBounds(405, 240, 150, 50);
		this.valider.setForeground(Color.BLACK);
		this.valider.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if( fenetre.getClient().getConnecteAuServeur() ){
	    			String login = identifiant.getText();
	    			String motdp = mdp.getText();
	    			if( login != null && motdp != null && !login.equals("") && !motdp.equals("") ){
	    				Paquet p = Paquet.creeDEMANDE_INSCRIPTION( login, motdp );
	    				int id = p.getId();
	    				fenetre.getClient().envoyerPaquet(p);
	    				Paquet ps = fenetre.getClient().recevoirPaquet(5.0, id);
	    				if( ps != null ){
	    					if( ps.getNbObjet() == 0 ){
	    						information.setText("L'identifiant existe déjà");
	    					}else{
	    						Joueur j = (Joueur) ps.getObjet(0);
	    						fenetre.getClient().setJoueur( j );
	    					}
	    				}else{
	    					information.setText("Limite de temps dépassé, essayé plus tard");
	    				}
	    			}
	    		}
	    	}
	    });
	    this.add(this.valider);
	}
	
	private void griser(){
		this.mdp.setEnabled( false );
		this.identifiant.setEnabled( false );
		this.valider.setEnabled( false );
	}
	
	private void PasGriser(){
		this.mdp.setEnabled( true );
		this.identifiant.setEnabled( true );
		this.valider.setEnabled( true );
	}
	
}
