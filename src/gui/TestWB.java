package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TestWB {

	private JFrame frmMastermind;
	CardLayout cl = new CardLayout();
	  JPanel content = new JPanel();
	  //Liste des noms de nos conteneurs pour la pile de cartes
	  String[] listeMenus = {"accueil", "connexion", "inscription", "profil", "tab_scores", "choix_mode", "choix_niveau", "personnaliser", "partie"};


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestWB window = new TestWB();
					window.frmMastermind.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestWB() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMastermind = new JFrame();
		frmMastermind.setTitle("Mastermind");
	    frmMastermind.setSize(960, 544);
	    frmMastermind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frmMastermind.setLocationRelativeTo(null);
	    frmMastermind.setResizable(false);
			
	   
	    
	    // ATTENTION : On ne peut PAS utiliser UN MÊME COMPOSANT pour DEUX PANEL DIFFERENTS)
	    
	    /*** MENU ACCUEIL ***/
	    //Création du panel
	    JPanel accueil = new JPanel();
	    accueil.setLayout(null); // Si on veut positionner les éléments comme on veut (pratique avec Window Builder mais c'est galère sinon)
	    accueil.setBackground(Color.blue);	

	    // Création des différents composants
	    JLabel lblMastermind = new JLabel("Mastermind");
		lblMastermind.setFont(new Font("Tahoma", Font.PLAIN, 35)); // Modification de la police
		lblMastermind.setBounds(380, 43, 200, 50);
		lblMastermind.setVerticalAlignment(SwingConstants.TOP);
		lblMastermind.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    JButton btnJouer = new JButton("Jouer");
	    //Définition de l'action du btnJouer
	    btnJouer.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	        cl.show(content, listeMenus[5]);
	      }
	    });
		btnJouer.setBounds(405, 170, 150, 50);
		
	    JButton btnConnexion = new JButton("Se connecter");
	    //Définition de l'action du btnConnexion
	    btnConnexion.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	        cl.show(content, listeMenus[1]);
	      }
	    });
	    btnConnexion.setBounds(405, 230, 150, 50);
	    
	    JButton btnInscription = new JButton("S'inscrire");
	    //Définition de l'action du btnInscription
	    btnInscription.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	        cl.show(content, listeMenus[2]);
	      }
	    });
	    btnInscription.setBounds(405, 290, 150, 50);
	    
	    JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(405, 350, 150, 50);
	    //Définition de l'action du btnQuitter
	    btnQuitter.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	    	  frmMastermind.dispose();
	      }
	    });
	    
	    // Ajout des composants au panel
	    accueil.add(lblMastermind);
	    accueil.add(btnJouer);
	    accueil.add(btnConnexion);
	    accueil.add(btnInscription);
	    accueil.add(btnQuitter);
	    /*** FIN MENU ACCUEIL ***/
	    
	    
	    
	    
	    /*** MENU JOUER ***/
	    //Création du panel
	    JPanel jouer = new JPanel();
	    jouer.setBackground(Color.yellow);	
	    
	    // Création des différents composants
	    JButton retour0 = new JButton("Retour");
	    //Définition de l'action du retour0
	    retour0.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	        cl.show(content, listeMenus[0]);
	      }
	    });
	    
	    // Ajout des composants au panel
	    jouer.add(retour0);
	    /*** FIN MENU JOUER ***/

	    
	    
	    /*** MENU CONNEXION ***/
	    //Création du panel
	    JPanel connexion = new JPanel();
	    connexion.setBackground(Color.red);		

	    // Création des différents composants
	    JButton retour1 = new JButton("Retour");
	    //Définition de l'action du retour1
	    retour1.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	        cl.show(content, listeMenus[0]);
	      }
	    });
	    
	    // Ajout des composants au panel
	    connexion.add(retour1);

	    /*** FIN MENU CONNEXION ***/


	    
	    
	    /*** MENU INSCRIPTION ***/
	    //Création du panel
	    JPanel inscription = new JPanel();
	    inscription.setBackground(Color.green);
	    
	    // Création des différents composants
	    JButton retour2 = new JButton("Retour");
	    //Définition de l'action du retour2
	    retour2.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	        cl.show(content, listeMenus[0]);
	      }
	    });	    
	    // Ajout des composants au panel
	    inscription.add(retour2);

	    /*** FIN MENU INSCRIPTION ***/

	    
	    
	    
	    //On définit le layout
	    content.setLayout(cl);
	    
	    //On ajoute les menuS à la pile avec un nom pour les retrouver
	    content.add(accueil, listeMenus[0]);
	    content.add(connexion, listeMenus[1]);
	    content.add(inscription, listeMenus[2]);
	    content.add(jouer, listeMenus[5]);


	    frmMastermind.getContentPane().add(content, BorderLayout.CENTER);
	}

}
