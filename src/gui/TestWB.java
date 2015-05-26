package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestWB {

	private JFrame frmMastermind;
	CardLayout cl = new CardLayout();
	JPanel content = new JPanel();
	
	//Liste des noms de nos menus pour la pile de CardLayout
	String[] listeMenus = {"accueil", "connexion", "inscription", "profil", "tab_scores", "choix_mode", "choix_niveau", "personnaliser", "partie"};
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;


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
	    
	    /*******************************************************************/
	    /**************************** MENU ACCUEIL *************************/
	    /*******************************************************************/	    
	    //Création du panel
	    JPanel accueil = new JPanel();
	    accueil.setLayout(null); // Si on veut positionner les éléments comme on veut (pratique avec Window Builder mais c'est galère sinon)
	    accueil.setBackground(Color.WHITE);	

	    // Création des différents composants
	    JLabel lblMastermind = new JLabel("0");
	    lblMastermind.setVerticalAlignment(SwingConstants.TOP);
		lblMastermind.setFont(new Font("Agency FB", Font.PLAIN, 66)); // Modification de la police
		lblMastermind.setBounds(150, 13, 660, 100);
		lblMastermind.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    JButton btnJouer = new JButton("Jouer");
	    //Définition de l'action du btnJouer
	    btnJouer.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	        cl.show(content, listeMenus[5]);
	      }
	    });
		btnJouer.setBounds(405, 124, 150, 40);
		
	    JButton btnConnexion = new JButton("Se connecter");
	    //Définition de l'action du btnConnexion
	    btnConnexion.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	        cl.show(content, listeMenus[1]);
	      }
	    });
	    btnConnexion.setBounds(405, 184, 150, 40);
	    
	    JButton btnInscription = new JButton("S'inscrire");
	    //Définition de l'action du btnInscription
	    btnInscription.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	        cl.show(content, listeMenus[2]);
	      }
	    });
	    btnInscription.setBounds(405, 237, 150, 40);
	    
	    JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(405, 290, 150, 40);
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
	    
	    
	    
	    
	    /*******************************************************************/
	    /************************** MENU JOUER *****************************/
	    /*******************************************************************/	    
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

	    
	    
	    /*******************************************************************/
	    /************************** MENU DE CONNEXION **********************/
	    /*******************************************************************/	    
	    //Création du panel
	    JPanel connexion = new JPanel();
	    connexion.setBackground(Color.WHITE);		

	    // Création des différents composants
	    JButton retour1 = new JButton("Retour");
	    retour1.setBounds(405, 300, 150, 50);
	    //Définition de l'action du retour1
	    retour1.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	        cl.show(content, listeMenus[0]);
	      }
	    });
	    connexion.setLayout(null);
	    
	    // Ajout des composants au panel
	    connexion.add(retour1);

	    /*** FIN MENU CONNEXION ***/


	    
	    /*******************************************************************/
	    /************************** MENU INSCRIPTION ***********************/
	    /*******************************************************************/

	    //Création du panel
	    JPanel inscription = new JPanel();
	    inscription.setBackground(Color.WHITE);
	    
	    // Création des différents composants
	    JButton retour2 = new JButton("Retour");
	    retour2.setBounds(405, 300, 150, 50);
	    //Définition de l'action du retour2
	    retour2.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	        cl.show(content, listeMenus[0]);
	      }
	    });	    
	    inscription.setLayout(null);
	    // Ajout des composants au panel
	    inscription.add(retour2);

	    /*** FIN MENU INSCRIPTION ***/

	    
	    
	    
	    //On définit le layout
	    content.setLayout(cl);
	    
	    //On ajoute les menuS à la pile avec un nom pour les retrouver
	    content.add(accueil, listeMenus[0]);
	    content.add(connexion, listeMenus[1]);
	    
	    
	    
	    /****************************************************************************************/
	    /****** Code généré par Window Builder (à replacer dans les parties qui vont bien) ******/
	    /****************************************************************************************/
	    JLabel labelConnexion = new JLabel("Connexion");
	    labelConnexion.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    labelConnexion.setBounds(405, 43, 141, 70);
	    connexion.add(labelConnexion);

	    JLabel lblIdentifiant = new JLabel("Identifiant");
	    lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    lblIdentifiant.setBounds(345, 120, 89, 27);
	    connexion.add(lblIdentifiant);
	    
	    textField = new JTextField();
	    lblIdentifiant.setLabelFor(textField);
	    textField.setBounds(492, 124, 176, 22);
	    connexion.add(textField);
	    textField.setColumns(10);
	    
	    JLabel lblMotDePasse = new JLabel("Mot de passe");
	    lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblMotDePasse.setBounds(345, 178, 98, 16);
	    connexion.add(lblMotDePasse);
	    
	    textField_1 = new JTextField();
	    lblMotDePasse.setLabelFor(textField_1);
	    textField_1.setBounds(492, 176, 176, 22);
	    connexion.add(textField_1);
	    textField_1.setColumns(10);
	    
	    JButton btnValider = new JButton("Valider");
	    btnValider.setForeground(Color.BLACK);
	    btnValider.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
	    btnValider.setBounds(405, 240, 150, 50);
	    connexion.add(btnValider);
	    content.add(inscription, listeMenus[2]);
	    
	    JButton btnValider2 = new JButton("S'inscrire");
	    btnValider2.setForeground(Color.BLACK);
	    btnValider2.setBounds(405, 240, 150, 50);
	    inscription.add(btnValider2);
	    
	    JLabel labelInscription = new JLabel("Inscription");
	    labelInscription.setFont(new Font("Tahoma", Font.PLAIN, 25));
	    labelInscription.setBounds(405, 43, 132, 70);
	    inscription.add(labelInscription);
	    
	    JLabel labelIdentifiant2 = new JLabel("Identifiant");
	    labelIdentifiant2.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    labelIdentifiant2.setBounds(345, 120, 89, 27);
	    inscription.add(labelIdentifiant2);
	    
	    JLabel lblMotDePasse2 = new JLabel("Mot de passe");
	    lblMotDePasse2.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblMotDePasse2.setBounds(345, 178, 98, 16);
	    inscription.add(lblMotDePasse2);
	    
	    textField_2 = new JTextField();
	    textField_2.setColumns(10);
	    textField_2.setBounds(492, 124, 176, 22);
	    inscription.add(textField_2);
	    
	    textField_3 = new JTextField();
	    textField_3.setColumns(10);
	    textField_3.setBounds(492, 176, 176, 22);
	    inscription.add(textField_3);
	    content.add(jouer, listeMenus[5]);


	    frmMastermind.getContentPane().add(content, BorderLayout.CENTER);
	    
	    JPanel attenteJoueur = new JPanel();
	    content.add(attenteJoueur, "name_52729462803944");
	    attenteJoueur.setLayout(null);
	    
	    JLabel lblPartieEnCours = new JLabel("Partie en cours");
	    lblPartieEnCours.setHorizontalAlignment(SwingConstants.CENTER);
	    lblPartieEnCours.setFont(new Font("Agency FB", Font.PLAIN, 40));
	    lblPartieEnCours.setBounds(170, 50, 660, 100);
	    attenteJoueur.add(lblPartieEnCours);
	    
	    JLabel lblParamtresDeLa = new JLabel("Paramètres de la partie :");
	    lblParamtresDeLa.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblParamtresDeLa.setBounds(170, 190, 209, 30);
	    attenteJoueur.add(lblParamtresDeLa);
	    
	    JLabel lblNbPions = new JLabel("Nombre de pions :");
	    lblNbPions.setBounds(170, 260, 130, 50);
	    attenteJoueur.add(lblNbPions);
	    
	    JLabel lblDifficulte = new JLabel("Difficulté :");
	    lblDifficulte.setBounds(170, 220, 130, 50);
	    attenteJoueur.add(lblDifficulte);
	    
	    JLabel lblNbCoups = new JLabel("Nombre de coups :");
	    lblNbCoups.setBounds(170, 300, 130, 50);
	    attenteJoueur.add(lblNbCoups);
	    
	    JLabel lblNbCouleurs = new JLabel("Nombre de couleurs :");
	    lblNbCouleurs.setBounds(170, 340, 130, 50);
	    attenteJoueur.add(lblNbCouleurs);
	    
	    JButton btnModifier = new JButton("Modifier");
	    btnModifier.setBounds(170, 440, 200, 40);
	    attenteJoueur.add(btnModifier);
	    
	    JLabel lblJoueurs = new JLabel("Joueurs :");
	    lblJoueurs.setFont(new Font("Tahoma", Font.BOLD, 13));
	    lblJoueurs.setBounds(607, 190, 125, 50);
	    attenteJoueur.add(lblJoueurs);
	    
	    JLabel lblJoueur = new JLabel("Joueur 1");
	    lblJoueur.setBounds(607, 220, 56, 50);
	    attenteJoueur.add(lblJoueur);
	    
	    JLabel lblJoueur_1 = new JLabel("Joueur 2");
	    lblJoueur_1.setBounds(607, 260, 56, 50);
	    attenteJoueur.add(lblJoueur_1);
	    
	    JButton btnNewButton = new JButton("Lancer la partie");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    	}
	    });
	    btnNewButton.setBounds(607, 310, 200, 40);
	    attenteJoueur.add(btnNewButton);
	    
	    JLabel lblAttente = new JLabel("Attente d'un joueur...");
	    lblAttente.setBounds(434, 143, 150, 30);
	    attenteJoueur.add(lblAttente);
	    
	    JLabel lblNewLabel = new JLabel("Couleurs multiples :");
	    lblNewLabel.setBounds(170, 380, 130, 50);
	    attenteJoueur.add(lblNewLabel);
	}
}
