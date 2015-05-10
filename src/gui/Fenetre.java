package gui;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Fenetre extends JFrame{

  CardLayout cl = new CardLayout();
  JPanel content = new JPanel();
  //Liste des noms de nos conteneurs pour la pile de cartes
  String[] listeMenus = {"accueil", "connexion", "inscription", "profil", "tab_scores", "choix_mode", "choix_niveau", "personnaliser", "partie"};

  public Fenetre(){
    this.setTitle("Mastermind");
    this.setSize(480, 640);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
		
   
    
    // ATTENTION : On ne peut PAS utiliser UN MÊME COMPOSANT pour DEUX PANEL DIFFERENTS)
    
    /*** MENU ACCUEIL ***/
    //Création du panel
    JPanel accueil = new JPanel();
    accueil.setLayout(null);
    accueil.setBackground(Color.blue);	

    // Création des différents composants
    
    JLabel lblMastermind = new JLabel("Mastermind");
	lblMastermind.setFont(new Font("Tahoma", Font.PLAIN, 35));
	lblMastermind.setBounds(134, 55, 193, 49);
	lblMastermind.setVerticalAlignment(SwingConstants.TOP);
	lblMastermind.setHorizontalAlignment(SwingConstants.RIGHT);
    
    JButton btnJouer = creerBoutonMenu("Jouer",5);
	btnJouer.setBounds(185, 241, 112, 25);
    JButton btnConnexion = creerBoutonMenu("Se connecter", 1);
    btnConnexion.setBounds(185, 301, 112, 25);
    JButton btnInscription = creerBoutonMenu("S'inscrire", 2);
    btnInscription.setBounds(185, 361, 112, 25);
    JButton btnQuitter = new JButton("Quitter");
	btnQuitter.setBounds(185, 421, 112, 25);
    //Définition de l'action du btn_quitter
    btnQuitter.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){				
    	 dispose();
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
    JButton retour0 = creerBoutonMenu("Retour", 0);
    
    // Ajout des composants au panel
    jouer.add(retour0);
    /*** FIN MENU JOUER ***/

    
    
    /*** MENU CONNEXION ***/
    //Création du panel
    JPanel connexion = new JPanel();
    connexion.setBackground(Color.red);		

    // Création des différents composants
    JButton retour1 = creerBoutonMenu("Retour", 0);
    
    // Ajout des composants au panel
    connexion.add(retour1);

    /*** FIN MENU CONNEXION ***/


    
    
    /*** MENU INSCRIPTION ***/
    //Création du panel
    JPanel inscription = new JPanel();
    inscription.setBackground(Color.green);
    
    // Création des différents composants
    JButton retour2 = creerBoutonMenu("Retour", 0);
    
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


    this.getContentPane().add(content, BorderLayout.CENTER);
    this.setVisible(true);
  }
  
  // Fonction permettant de créer un btn qui agira sur le CardLayout
  public JButton creerBoutonMenu (String nom, int menuDestination) {
	    JButton btn = new JButton(nom);
	    //Définition de l'action du btn2
	    btn.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	        cl.show(content, listeMenus[menuDestination]);
	      }
	    });
	    return btn;
  }
}
