package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import mastermind.Joueur;
import mastermind.Paquet;

@SuppressWarnings("serial")
public class Profil extends Menu{
	
	private Fenetre fenetre;
	private JLabel identifiant;
	private JLabel avatar;
	private JLabel nomAvatar;
	private File imageChoisit;
	private JButton valideAvatar;
	
	public Profil( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
		this.imageChoisit = null;
	}
	
	private void init(){
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addLabelTitre();
		this.addIdentifiant();
		this.addAvatar();
		this.addChoisitAvatar();
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
		laIDenti.setBounds(405, 100, 100, 30);
	    this.add(laIDenti);
	    
	    this.identifiant = new JLabel("Aucun");
	    this.identifiant.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    this.identifiant.setBounds(510, 100, 100, 30);
	    this.add(this.identifiant);
	}
	
	public void addChoisitAvatar(){
		JButton btn = new JButton( "Choisir un avatar" );
		btn.setBounds( 405, 150, 150, 20 );
		btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  JFileChooser choix = new JFileChooser();
		    	  choix.setFileSelectionMode( JFileChooser.FILES_ONLY );
		    	  choix.setFileFilter( new FiltreImage() );
		    	  int retour = choix.showOpenDialog( fenetre );
		    	  if( retour == JFileChooser.APPROVE_OPTION ){
		    		  imageChoisit = choix.getSelectedFile();
		    		  nomAvatar.setText( imageChoisit.getName() );
		    		  valideAvatar.setEnabled(true);
		    	  }else{
		    		  valideAvatar.setEnabled(false);
		    		  imageChoisit = null;
		    		  nomAvatar.setText("Aucune image selectioné");
		    	  }
		      }
		    });
		this.add(btn);
		
		this.nomAvatar = new JLabel("Aucune image selectioné");
		this.nomAvatar.setBounds( 600, 150, 200, 30 );
		this.nomAvatar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.add( this.nomAvatar );
		
		this.valideAvatar = new JButton( "Valider" );
		this.valideAvatar.setBounds( 405, 180, 100, 20 );
		this.valideAvatar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.valideAvatar.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  if( imageChoisit != null ){
		    		  try {
		    			  BufferedImage b = ImageIO.read( imageChoisit );
		    			  ImageIcon i = new ImageIcon( getScaledImage( b, Joueur.WIDTH_AVATAR, Joueur.HEIGHT_AVATAR) );
		    			  fenetre.getClient().getJoueur().setAvatar( i );
		    			  avatar.setIcon( fenetre.getClient().getJoueur().getAvatar() );
		    			  fenetre.getClient().envoyerPaquet( Paquet.creeMODIFI_AVATAR( i ) );
					} catch (IOException e) {
						e.printStackTrace();
					}
		    	  }
		      }
		    });
		this.add(this.valideAvatar);
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
	
	private void addAvatar(){
		this.avatar = new JLabel();
		this.avatar.setBounds(0, 0, Joueur.WIDTH_AVATAR, Joueur.HEIGHT_AVATAR);
		this.add(this.avatar);
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
		this.imageChoisit = null;
		this.nomAvatar.setText("Aucune image selectioné");
		this.valideAvatar.setEnabled(false);
	}
	
	public void decoServeur(){
		this.fenetre.showMenu( Fenetre.ACCUEIL );
	}
	
}
