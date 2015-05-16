package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mastermind.Joueur;
import mastermind.Paquet;

@SuppressWarnings("serial")
public class Connexion extends Menu{
	
	private Fenetre fenetre;
	private JLabel titre;
	private JLabel information;
	private JTextField identifiant;
	private JTextField mdp;
	private JButton valider;
	
	private static int X = 405, W = 200, H = 40;

	
	public Connexion( Fenetre fenetre ){
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
			this.information.setText( "" );
		}else{
			this.griser();
			this.information.setText( "Vous n'êtes pas connecté au réseau" );
		}
	}
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds( X, 340, W, H );
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.ACCUEIL );
		      }
		    });
		this.add( btn );
	}
	
	private void addLabelTitre() {
		this.titre = new JLabel("Se connecter");
		this.titre.setFont(new Font("Agency FB", Font.PLAIN, 40));
		this.titre.setBounds(170, 50, 660, 100);
		this.titre.setVerticalAlignment( SwingConstants.TOP );
		this.titre.setHorizontalAlignment( SwingConstants.CENTER );
	    this.add(this.titre);
	}
	
	private void addLabelInformation(){
		this.information = new JLabel("Informations");
		this.information.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.information.setForeground( Color.red );
		this.information.setBounds( 380, 400, 400, 27 );
		this.add( this.information );
	}
	
	private void addIdentifiant(){
		JLabel lblIdentifiant = new JLabel("Identifiant");
	    lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblIdentifiant.setBounds(300, 160, 100, 27);
	    this.add(lblIdentifiant);
	    
	    this.identifiant = new JTextField();
	    lblIdentifiant.setLabelFor( this.identifiant );
	    this.identifiant.setBounds(450, 164, 176, 22);
	    this.add( this.identifiant );
	    this.identifiant.setColumns(10);
	}
	
	private void addMDP(){
		JLabel lblMotDePasse = new JLabel("Mot de passe");
	    lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblMotDePasse.setBounds(300, 218, 200, 27);
	    this.add(lblMotDePasse);
	    
	    this.mdp = new JTextField();
	    lblMotDePasse.setLabelFor(this.mdp);
	    this.mdp.setBounds(450, 222, 176, 22);
	    this.add(this.mdp);
	    this.mdp.setColumns(10);
	}
	
	private void addValider(){
		this.valider = new JButton("Valider");
		this.valider.setBounds(X, 280, W, H);
		this.valider.setForeground(Color.BLACK);
		this.valider.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if( fenetre.getClient().getConnecteAuServeur() ){
	    			String login = identifiant.getText();
	    			String motdp = mdp.getText();
	    			if( login != null && motdp != null && !login.equals("") && !motdp.equals("") ){
	    				Paquet p = Paquet.creeDEMANDE_CONNECTION( login, motdp );
	    				int id = p.getId();
	    				fenetre.getClient().envoyerPaquet(p);
	    				Paquet ps = fenetre.getClient().recevoirPaquet(5.0, id);
	    				if( ps != null ){
	    					if( ps.getNbObjet() == 0 ){
	    						information.setText("Informations incorect");
	    					}else{
	    						Joueur j = (Joueur) ps.getObjet(0);
	    						fenetre.getClient().setJoueur( j );
	    						fenetre.showMenu( Fenetre.PROFIL );
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
	
	public void decoServeur(){
		this.griser();
		this.information.setText( "Vous n'êtes pas connecté au réseau" );
	}
	
}
