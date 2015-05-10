package gui;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame{

  CardLayout cl = new CardLayout();
  JPanel content = new JPanel();
  //Liste des noms de nos conteneurs pour la pile de cartes
  String[] listeMenus = {"accueil", "connexion", "inscription", "profil", "tab_scores", "choix_mode", "choix_niveau", "personnaliser", "partie"};

  public Fenetre(){
    this.setTitle("Mastermind");
    this.setSize(640, 480);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
		
   
    
    // ATTENTION : On ne peut PAS utiliser UN MÊME COMPOSANT pour DEUX PANEL DIFFERENTS)
    
    /*** MENU ACCUEIL ***/
    //Création du panel
    JPanel accueil = new JPanel();
    accueil.setBackground(Color.blue);	

    // Création des différents composants
    JButton bouton_jouer = creerBoutonMenu("Jouer",5);
    JButton bouton_connexion = creerBoutonMenu("Se connecter", 1);
    JButton bouton_inscription = creerBoutonMenu("S'inscrire", 2);
    JButton bouton_quitter = new JButton("Quitter");
    //Définition de l'action du bouton_quitter
    bouton_quitter.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){				
    	 dispose();
      }
    });
    
    // Ajout des composants au panel
    accueil.add(bouton_jouer);
    accueil.add(bouton_connexion);
    accueil.add(bouton_inscription);
    accueil.add(bouton_quitter);
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
  
  // Fonction permettant de créer un bouton qui agira sur le CardLayout
  public JButton creerBoutonMenu (String nom, int menuDestination) {
	    JButton bouton = new JButton(nom);
	    //Définition de l'action du bouton2
	    bouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent event){				
	        cl.show(content, listeMenus[menuDestination]);
	      }
	    });
	    return bouton;
  }
}
