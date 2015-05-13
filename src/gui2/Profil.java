package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import mastermind.Joueur;

@SuppressWarnings("serial")
public class Profil extends Menu{
	
	private Fenetre fenetre;
	private JLabel identifiant;
	private JLabel avatar;
	
	public Profil( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init(){
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addLabelTitre();
		this.addIdentifiant();
		this.addAvatar();
		this.addBoutonRetour();
	}
	
	private void addLabelTitre(){
		JLabel titre = new JLabel("Profil");
		titre.setFont(new Font("Tahoma", Font.PLAIN, 25));
		titre.setBounds(405, 43, 141, 70);
	    this.add(titre);
	}
	
	private void addIdentifiant(){
		JLabel laIDenti = new JLabel("Identifiant: ");
		laIDenti.setFont(new Font("Tahoma", Font.PLAIN, 16));
		laIDenti.setBounds(405, 200, 141, 70);
	    this.add(laIDenti);
	    
	    this.identifiant = new JLabel("Aucun");
	    this.identifiant.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    this.identifiant.setBounds(440, 200, 141, 30);
	    this.add(this.identifiant);
	}
	
	private void addAvatar(){
		this.avatar = new JLabel();
		this.avatar.setBounds(0, 0, Joueur.WIDTH_AVATAR, Joueur.HEIGHT_AVATAR);
	}
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds( 405, 350, 150, 50 );
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		        fenetre.showMenu( Fenetre.ACCUEIL );
		      }
		    });
		this.add( btn );
	}
	
	public void clic(){
		this.identifiant.setText( this.fenetre.getClient().getJoueur().getIdentifiant() );
		if( this.fenetre.getClient().getJoueur().getAvatar() != null ){
			this.avatar.setIcon( this.fenetre.getClient().getJoueur().getAvatar() );
		}
	}
	
	public void decoServeur(){
		this.fenetre.showMenu( Fenetre.ACCUEIL );
	}
	
}
