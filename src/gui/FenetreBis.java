package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

 
 
public class FenetreBis extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/************************************************************************/
	/**********************DECLARATION DE VARIABLE***************************/
	/************************************************************************/
	
	//conteneur principal
	private JPanel container= new JPanel();
	//conteneur des boutons
	private JPanel bouton = new JPanel();
	private JPanel boutonEff = new JPanel();
	//conteneur du plateau de jeu
	private JLabel[][] plateau=new JLabel[10][4];
	private JLabel[][] plateauRes=new JLabel[10][4];
	private JLabel[] plateauSolution = new JLabel[4];
	
	//texte a afficher
	private JLabel texte = new JLabel("<html><center>Vous pouvez ajouter <br/> un pion.</center></html>");
	private JLabel ligNotFull = new JLabel("<html><center>Veuillez remplir toute<br/>la ligne avant de valider !</center></html>");
	private JLabel texteFin = new JLabel();
	private JLabel txtSolution = new JLabel("<html>Solution</html>");
	private JLabel texteBienv = new JLabel("<html><center>Bienvenue dans le jeu du mastermind !<br/>Le code &agrave; d&eacute;couvrir est g&eacute;n&eacute;r&eacute; al&eacute;atoirement.<br/> Vous devez trouver le code<br/> &agrave; l'aide des boutons ci-dessous.</center></html>");
	Font police = new Font("Times New Roman",Font.BOLD,12);
	
	//Images de chaques pions
	private ImageIcon iconeRed= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/1.png"));
	private ImageIcon iconeBlue= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/2.png"));
	private ImageIcon iconeGreen= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/3.png"));
	private ImageIcon iconeOrange= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/4.png"));
	private ImageIcon iconeYellow= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/5.png"));
	private ImageIcon iconeWhite= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/6.png"));
	private ImageIcon iconeFushia= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/7.png"));
	private ImageIcon iconePurple= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/8.png"));
	private ImageIcon iconeBlack= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/10.png"));
	private ImageIcon iconeTurquoise= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/11.png"));
	private ImageIcon iconeGrey= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/9.png"));
	private ImageIcon iconeResBlack= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/test.png"));
	private ImageIcon iconeResWhite= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/res_white.png"));
	private ImageIcon iconeVoid= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/void.png"));
	private ImageIcon iconeVoid2= new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/void2.png"));
	
	//Buttons de chaque pions
	private JButton boutonRed = new JButton(iconeRed);
	private JButton boutonBlue = new JButton(iconeBlue);
	private JButton boutonGreen = new JButton(iconeGreen);
	private JButton boutonOrange = new JButton(iconeOrange);
	private JButton boutonYellow = new JButton(iconeYellow);
	private JButton boutonWhite = new JButton(iconeWhite);
	private JButton boutonFushia = new JButton(iconeFushia);
	private JButton boutonPurple = new JButton(iconePurple);
	private JButton boutonBlack = new JButton(iconeBlack);
	private JButton boutonTurquoise = new JButton(iconeTurquoise);

	//boutons effacer , valider, et nouvelle partie;
	private JButton effDernier = new JButton("<html>Effacer<br/>Dernier<html>");
	private JButton effLigne = new JButton("<html>Effacer<br/>&nbsp;&nbsp;Ligne<html>");
	private JButton valider = new JButton("Valider");
	private JButton newGame = new JButton("Nouvelle Partie");
	
	//differents elements du Menu dans le jeu
	private JMenuBar menu = new JMenuBar();
	private JMenu jeuMenu = new JMenu("Jeu");
	private JMenu difficultMenu = new JMenu("<html>Difficult&eacute</html>");
	private JMenu helpMenu = new JMenu("?");
	
	private JMenuItem newMenu = new JMenuItem("Nouveau Jeu");
	private JMenuItem closeMenu = new JMenuItem("Fermer");
	
	private JMenuItem instrucMenu = new JMenuItem("Instructions");
	private JMenuItem aboutMenu = new JMenuItem("<html>&Agrave; propos");
	
	private JRadioButtonMenuItem veasyMenu = new JRadioButtonMenuItem("<html>Tr&eacute;s Facile</html>");
	private JRadioButtonMenuItem easyMenu = new JRadioButtonMenuItem("Facile");
	private JRadioButtonMenuItem averageMenu = new JRadioButtonMenuItem("Moyen");
	private JRadioButtonMenuItem hardMenu = new JRadioButtonMenuItem("Difficile");
	private JRadioButtonMenuItem vhardMenu = new JRadioButtonMenuItem("<html>Tr&eacute;s Difficile</html>");
	
	ButtonGroup buttonGroup = new ButtonGroup();
	
	
	
	//bouton menu
	JButton boutonJouer = new JButton("Jouer");
	JButton boutonSeconnecter = new JButton("Se connecter");
	JButton boutonSincrire = new JButton("S'inscrire");
	JButton boutonQuitter = new JButton("Quitter le jeu");

	//bouton dificult
	JButton boutonvEasy = new JButton("<html>Tr&eacute;s Facile</html>");
	JButton boutonEasy = new JButton("Facile");
	JButton boutonAverage = new JButton("Moyen");
	JButton boutonHard = new JButton("Difficile");
	JButton boutonvHard = new JButton("<html>Tr&eacute;s Difficile</html>");
	
	
	//tour de l'essai
	int essai=0;
	//pion a poser
	int posPion=0;
	
	//choix de la difficulte du jeu 1 très facile,2 facile, 3 moyen , 4 difficile, 5 très difficile
	private int dificult;
	
	//images
	private ImageIcon[] images;
	
	//jeu
	Grille g = new Grille();
	int[] code;
	int[] test=new int[4];
	int nbPionN=0,nbPionB=0;
	/************************************************************************/
	/*******************INITIALISATION DE LA FENETRE*************************/
	/************************************************************************/
	
	public FenetreBis(){
		 //Titre de la fenetre
		 this.setTitle("Mastermind");
		 
		 //taille de la fenetre
	     this.setSize(600,800);
	     
	     //Place de la fenetre
	     this.setLocationRelativeTo(null);   
	     
	     //Action sur la croix pour fermer la fenetre
	     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     
	     images =new ImageIcon[12];
			for (int i=1;i<12;i++){
				
				images[i] = new ImageIcon(FenetreBis.class.getClass().getResource("/ressources/"+i+".png"));

			}
	     //initialisation de la fenetre
	     initDebut();
	     
		
	     
	     
	     //Choix du Panel de la fenetre
	     this.setContentPane(container);
	     
	     //Interdiction du redimensionnement de la fenetre
	     this.setResizable(true);
	     
	     //Permet de rendre visible la fenetre
	     this.setVisible(true);
	}

    
	/************************************************************************/
	/*********INITIALISATION DES COMPOSANTS DE LA FENETRE********************/
	/************************************************************************/
	
    private void initComposant(){
    	code=g.genereCode(dificult);
    	//bouton en GridLayout
    	bouton.setLayout(new GridLayout(2,4,5,5));
    	boutonEff.setLayout(new GridLayout(1,2,5,5));
    	//position des boutons
    	bouton.setBounds(350, 255, 200,100);
    	boutonEff.setBounds(350,360,200,50);
    	valider.setBounds(350,415,200,50);
    	newGame.setBounds(350,415,200,50);
    	
    	//position du texte
    	texte.setBounds(400, 200,200, 60);
    	texteBienv.setBounds(350, -30, 200, 200);
    	txtSolution.setBounds(55,385,100,20);
    	texte.setFont(police);
    	texteBienv.setFont(police);
    	txtSolution.setFont(police);
    	ligNotFull.setFont(police);
    	
    	//initialisation du plateau par des cases vides
     	for(int i=0;i<10;i++){
    		for(int j=0;j<4;j++){
    			plateau[i][j]= new JLabel(iconeVoid);
    		}
    	}
     	//initialisation de la position de chaque cases sur le plateau
    	for(int i=0;i<10;i++){
    		for(int j=0;j<4;j++){
    			plateau[i][j].setBounds(j*35+10, i*35+10, 30, 30);
    		}
    	}
    	
    	//initialisation du plateauRes par des cases vides
     	for(int i=0;i<10;i++){
    		for(int j=0;j<4;j++){
    			plateauRes[i][j]= new JLabel(iconeVoid2);
    		}
    	}
     	//initialisation de la position de chaque cases sur le plateauRes
    	for(int i=0;i<10;i++){
    		for(int j=0;j<4;j++){
    			if(j%2==0)
    				plateauRes[i][j].setBounds(j*7+155, i*35+16, 12, 12);
    			else if(j%2==1)
    				plateauRes[i][j].setBounds(j*7+155-7, i*35+30, 12, 12);
    		}
    	}
    	
    	
    	
    	//initialisation du plateauSolution par des cases cach&eacute;es
     	for(int i=0;i<4;i++){
    			plateauSolution[i]= new JLabel(iconeGrey);
    	}
 
     	//initialisation de la position de chaque cases sur le plateauSolution
    	for(int i=0;i<4;i++){
    			plateauSolution[i].setBounds(i*35+10, 415, 30, 30);
    	}
	
    	//ajout de l'action des différents boutons
    	boutonRed.addActionListener(new RedListener());
    	boutonBlue.addActionListener(new BlueListener());
    	boutonGreen.addActionListener(new GreenListener());
    	boutonOrange.addActionListener(new OrangeListener());
    	boutonWhite.addActionListener(new WhiteListener());
    	boutonYellow.addActionListener(new YellowListener());
    	boutonFushia.addActionListener(new FushiaListener());
    	boutonPurple.addActionListener(new PurpleListener());
		boutonBlack.addActionListener(new BlackListener());
		boutonTurquoise.addActionListener(new TurquoiseListener());
    	effDernier.addActionListener(new EffDernierListener());    
    	effLigne.addActionListener(new EffLigneListener()); 
    	valider.addActionListener(new ValiderListener()); 
    	newGame.addActionListener(new NewGameListener());
    	
    	
    	//ajout des boutons au JPanel
    	bouton.add(boutonRed);
    	bouton.add(boutonBlue);
    	bouton.add(boutonGreen);
    	bouton.add(boutonOrange);
    	bouton.add(boutonWhite);
    	bouton.add(boutonYellow);
    	bouton.add(boutonFushia);
    	bouton.add(boutonPurple);
		bouton.add(boutonBlack);
		bouton.add(boutonTurquoise);
    	
    	if(dificult<=4){
		boutonTurquoise.setEnabled(false);
		boutonBlack.setEnabled(false);
    	}
		if(dificult<=2){
		boutonPurple.setEnabled(false);
		boutonFushia.setEnabled(false);
    	}
    	if(dificult==1){
		boutonYellow.setEnabled(false);
    	}
    	//ajout bouton effacer
    	boutonEff.add(effDernier);
    	boutonEff.add(effLigne);
    	//ajout du plateau a la fenetre
    	for(int i=0;i<10;i++){
    		for(int j=0;j<4;j++){
    	    	container.add(plateau[i][j]);
    		}
    	}
    	
    	//ajout du plateauRes a la fenetre
    	for(int i=0;i<10;i++){
    		for(int j=0;j<4;j++){
    	    	container.add(plateauRes[i][j]);
    		}
    	}
    
    	//ajout du plateauSolution a la fenetre
    	for(int i=0;i<4;i++){
    			container.add(plateauSolution[i]);
    		}

    	//initialisation menu
    	jeuMenu.add(newMenu);
    	
		buttonGroup.add(veasyMenu);
    	buttonGroup.add(easyMenu);
    	buttonGroup.add(averageMenu);
    	buttonGroup.add(hardMenu);
		buttonGroup.add(vhardMenu);
    	
		difficultMenu.add(veasyMenu);
    	difficultMenu.add(easyMenu);
    	difficultMenu.add(averageMenu);
    	difficultMenu.add(hardMenu);
		difficultMenu.add(vhardMenu);
    	
    	jeuMenu.add(difficultMenu);
    	jeuMenu.add(closeMenu);
    	
    	helpMenu.add(instrucMenu);
    	helpMenu.add(aboutMenu);
    	
    	menu.add(jeuMenu);
    	menu.add(helpMenu);
    	
    	//action des elements du menu
    	newMenu.addActionListener(new NewGameListener());
    	closeMenu.addActionListener(new CloseMenuListener());
		veasyMenu.addActionListener(new VEasyListener());
    	veasyMenu.addActionListener(new NewGameListener());
    	easyMenu.addActionListener(new EasyListener());
    	easyMenu.addActionListener(new NewGameListener());
    	averageMenu.addActionListener(new AverageListener());
    	averageMenu.addActionListener(new NewGameListener());
    	hardMenu.addActionListener(new HardListener());
    	hardMenu.addActionListener(new NewGameListener());
		vhardMenu.addActionListener(new VHardListener());
    	vhardMenu.addActionListener(new NewGameListener());
    	instrucMenu.addActionListener(new InstrucListener());
    	aboutMenu.addActionListener(new AboutListener());
    	
    	
    	//ajout des boutons a la fenetre
    	container.add(bouton);
    	container.add(boutonEff);
    	container.add(valider);
    	container.add(texte);
    	container.add(texteBienv);
    	container.add(txtSolution);
    	this.setJMenuBar(menu);

    }
    

	/************************************************************************/
    /*********INITIALISATION DE LA FENETRE DU DEBUT**************************/
	/************************************************************************/
     public void initDebut(){
    	 
    	bouton.setLayout(new GridLayout(5,1,5,5));
     	bouton.setBounds(this.getWidth()/6, this.getHeight()/3, 400,100);
		
	
		
		
		bouton.add(boutonJouer);
        // appeler JouerListener avec la selection de niveau		
		boutonJouer.addActionListener(new JouerListener());
     	bouton.add(boutonSeconnecter);
		// appeler la classe se connecter
     	bouton.add(boutonSincrire);
		// appeler la classe sincrire
     	bouton.add(boutonQuitter);
		boutonQuitter.addActionListener(new CloseMenuListener());
		
    	
    	container.setLayout(null);
    	container.add(bouton);
     	
    	 
    	 
     }
    
    
    
    
    
    
	/************************************************************************/
	/**********************CLASSES POUR LES BOUTONS**************************/
	/************************************************************************/
    
    /**
     * classe qui écoute notre boutonRed
     */
    class RedListener  implements ActionListener{
            public void actionPerformed(ActionEvent arg0) {
            	container.remove(ligNotFull);
            	container.revalidate();
            	container.repaint();
            	if(posPion<4){
	            	plateau[essai][posPion].setIcon(iconeRed);
	            	test[posPion]=1;
	            	posPion++;
            	}
            	if(posPion>=4){
            		texte.setBounds(240, 200,200, 60);
            		texte.setText("<html><center>Ligne compl&egrave;te !<br/>Appuyez sur valider !</center></html>");
            	}
            }
            
    }
    /**
     * classe qui écoute notre boutonBlue
     */
    class BlueListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	if(posPion<4){
	        	plateau[essai][posPion].setIcon(iconeBlue);
	        	test[posPion]=2;
	        	posPion++;
        	}
        	if(posPion>=4){
        		texte.setBounds(240, 200,200, 60);
        		texte.setText("<html><center>Ligne compl&egrave;te !<br/>Appuyez sur valider !</center></html>");
        	}
        }
            
    }
    
    /**
     * classe qui écoute notre boutonGreen
     */
    class GreenListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	if(posPion<4){
        		plateau[essai][posPion].setIcon(iconeGreen);
        		test[posPion]=3;
        		posPion++;
        	}
        	if(posPion>=4){
        		texte.setBounds(240, 200,200, 60);
        		texte.setText("<html><center>Ligne compl&egrave;te !<br/>Appuyez sur valider !</center></html>");
        	}
        }
    }
    
    /**
     * classe qui écoute notre boutonOrange
     */
    class OrangeListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	if(posPion<4){
        		plateau[essai][posPion].setIcon(iconeOrange);
        		test[posPion]=4;
        		posPion++;
        	}
        	if(posPion>=4){
        		texte.setBounds(240, 200,200, 60);
        		texte.setText("<html><center>Ligne compl&egrave;te !<br/>Appuyez sur valider !</center></html>");
        	}
        }
    }
    
    /**
     * classe qui écoute notre boutonYellow
     */
    class YellowListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	if(posPion<4){
        		plateau[essai][posPion].setIcon(iconeYellow);
        		test[posPion]=5;
        		posPion++;
        	}
        	if(posPion>=4){
        		texte.setBounds(240, 200,200, 60);
        		texte.setText("<html><center>Ligne compl&egrave;te !<br/>Appuyez sur valider !</center></html>");
        	}
        }
            
    }
    
    /**
     * classe qui écoute notre boutonWhite
     */
    class WhiteListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	if(posPion<4){
        		plateau[essai][posPion].setIcon(iconeWhite);
        		test[posPion]=6;
        		posPion++;
        	}
        	if(posPion>=4){
        		texte.setBounds(240, 200,200, 60);
        		texte.setText("<html><center>Ligne compl&egrave;te !<br/>Appuyez sur valider !</center></html>");
        	}
        }
            
    }
    

    
    /**
     * classe qui écoute notre boutonFushia
     */
    class FushiaListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	if(posPion<4){
        		plateau[essai][posPion].setIcon(iconeFushia);
        		test[posPion]=7;
        		posPion++;
        	}
        	if(posPion>=4){
        		texte.setBounds(240, 200,200, 60);
        		texte.setText("<html><center>Ligne compl&egrave;te !<br/>Appuyez sur valider !</center></html>");
        	}
        }
    }
    
    /**
     * classe qui écoute notre boutonPurple
     */
    class PurpleListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	if(posPion<4){
        		plateau[essai][posPion].setIcon(iconePurple);
        		test[posPion]=8;
        		posPion++;
        	}
        	if(posPion>=4){
        		texte.setBounds(240, 200,200, 60);
        		texte.setText("<html><center>Ligne compl&egrave;te !<br/>Appuyez sur valider !</center></html>");
        	}
        }
    }
	
	/**
     * classe qui écoute notre boutonBlack
     */
    class BlackListener  implements ActionListener{
            public void actionPerformed(ActionEvent arg0) {
            	container.remove(ligNotFull);
            	container.revalidate();
            	container.repaint();
            	if(posPion<4){
	            	plateau[essai][posPion].setIcon(iconeBlack);
	            	test[posPion]=9;
	            	posPion++;
            	}
            	if(posPion>=4){
            		texte.setBounds(240, 200,200, 60);
            		texte.setText("<html><center>Ligne compl&egrave;te !<br/>Appuyez sur valider !</center></html>");
            	}
            }
            
    }
	
	/**
     * classe qui écoute notre boutonTurquoise
     */
    class TurquoiseListener  implements ActionListener{
            public void actionPerformed(ActionEvent arg0) {
            	container.remove(ligNotFull);
            	container.revalidate();
            	container.repaint();
            	if(posPion<4){
	            	plateau[essai][posPion].setIcon(iconeTurquoise);
	            	test[posPion]=10;
	            	posPion++;
            	}
            	if(posPion>=4){
            		texte.setBounds(240, 200,200, 60);
            		texte.setText("<html><center>Ligne compl&egrave;te !<br/>Appuyez sur valider !</center></html>");
            	}
            }
            
    }
    
    /**
     * classe qui écoute notre effDernier
     */
    class EffDernierListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	if(posPion>0){
        	posPion--;
        	plateau[essai][posPion].setIcon(iconeVoid);
        	test[posPion]=0;
        	texte.setBounds(240, 200,200, 60);
        	texte.setText("<html><center>Vous pouvez ajouter <br/> un pion.</center></html>");
        	}
        }	
    }
    
    /**
     * classe qui écoute notre effLigne
     */
    class EffLigneListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	for(int i=posPion-1;i>=0;i--){
        		plateau[essai][i].setIcon(iconeVoid);
        		test[i]=0;
        		
        	}
        	posPion=0;
        	texte.setBounds(240, 200,200, 60);
        	texte.setText("<html><center>Vous pouvez ajouter <br/> un pion.</center></html>");
        }
    }
    
    /**
     * classe qui écoute notre valider
     */
    class ValiderListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	if(g.lineFull(test)){

        		if(essai<10){	
        			nbPionN=g.compte(g.caseN(code, test));
        			nbPionB=g.compte(g.caseB(code, test));
        			if(nbPionN==4){
        				for(int i=0;i<4;i++){
        					plateauRes[essai][i].setIcon(iconeResBlack);	
        				}
        				for(int i=0;i<4;i++){
        					plateauSolution[i].setIcon(images[code[i]]);        			
        				}

        				container.remove(bouton);
        				container.remove(boutonEff);
        				container.remove(valider);
        				container.remove(texte);
        				container.revalidate();
        				container.repaint();

        				texteFin=new JLabel("<html><center>Bien jou&eacute; !</center></html>");
        				texteFin.setBounds(260, 385, 100, 25);
        				container.add(texteFin);
        				container.add(newGame);




        			}
        			else{
        				for(int i=0;i<nbPionN;i++){
        					plateauRes[essai][i].setIcon(iconeResBlack);
        				}
        				for(int i=nbPionN;i<nbPionB+nbPionN;i++){
        					plateauRes[essai][i].setIcon(iconeResWhite);
        				}
        				essai++;
        				posPion=0;
        				texte.setBounds(240, 200,200, 60);
        				texte.setText("<html><center>Vous pouvez ajouter <br/> un pion.</center></html>");
        				for(int i=0;i<4;i++){
        					test[i]=0;

        				}
        			}

        		}
        		if(essai==10){
        			for(int i=0;i<4;i++){
        				plateauSolution[i].setIcon(images[code[i]]);        			
        			}
        			container.remove(bouton);
        			container.remove(boutonEff);
        			container.remove(valider);
        			container.remove(texte);
        			container.revalidate();
        			container.repaint();

        			texteFin=new JLabel("<html><center>Dommage !</center></html>");
        			texteFin.setBounds(260, 385, 100, 25);
        			container.add(texteFin);
        			container.add(newGame);

        		}
        	}
        	else{
        		ligNotFull.setBounds(228, 155,250, 80);
        		container.add(ligNotFull);
        		container.revalidate();
        		container.repaint();
        	}
        }
        	
    }
    
    
    
    /**
     * classe qui écoute notre newGame
     */
    class NewGameListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
			if(arg0.getSource()== veasyMenu){
        		code=g.genereCode(1);
			}
        	else if(arg0.getSource()== easyMenu){
        		code=g.genereCode(2);
        	}
        	else if(arg0.getSource()== averageMenu){
        		code=g.genereCode(3);
        	}
        	else if(arg0.getSource()== hardMenu){
        		code=g.genereCode(4);
        	}
			else if(arg0.getSource()== vhardMenu){
        		code=g.genereCode(5);
        	}
        	else{
        		code=g.genereCode(dificult);
        	}
        	
        	essai=0;
        	posPion=0;
        	for(int i=0;i<10;i++){
        		for(int j=0;j<4;j++){
        			plateau[i][j].setIcon(iconeVoid);
        		}       			
    		}
        	for(int i=0;i<4;i++){
    			test[i]=0;        			
    		}
        	for(int i=0;i<4;i++){
    			plateauSolution[i].setIcon(iconeGrey);        			
    		}
        	for(int i=0;i<10;i++){
        		for(int j=0;j<4;j++){
    				plateauRes[i][j].setIcon(iconeVoid2);
    			}
			}

        	texte.setBounds(240, 200,200, 60);
        	texte.setText("<html><center>Vous pouvez ajouter <br/> un pion.</center></html>");
    		container.remove(texteFin);
    		container.remove(newGame);        	
       		container.add(bouton);
    		container.add(boutonEff);
    		container.add(valider);
    		container.add(texte);
    		container.revalidate();
    		container.repaint();

        }
    }
    
    /**
     * classe qui écoute notre veasy
     */
	 
	 class VEasyListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	dificult=1;
			boutonTurquoise.setEnabled(false);
			boutonBlack.setEnabled(false);
        	boutonPurple.setEnabled(false);
        	boutonFushia.setEnabled(false);
        	boutonYellow.setEnabled(false);
        	boutonWhite.setEnabled(true);
        	container.revalidate();
        	container.repaint();

        }
    }
	
	/**
     * classe qui écoute notre easy
     */
	 
    class EasyListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	dificult=2;
			boutonTurquoise.setEnabled(false);
			boutonBlack.setEnabled(false);
        	boutonPurple.setEnabled(false);
        	boutonFushia.setEnabled(false);
        	boutonYellow.setEnabled(true);
        	boutonWhite.setEnabled(true);
        	container.revalidate();
        	container.repaint();

        }
    }
    
    /**
     * classe qui écoute notre average
     */
    class AverageListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	dificult=3;
			boutonTurquoise.setEnabled(false);
			boutonBlack.setEnabled(false);
        	boutonPurple.setEnabled(true);
        	boutonFushia.setEnabled(true);
        	boutonYellow.setEnabled(true);
        	boutonWhite.setEnabled(true);
        	container.revalidate();
        	container.repaint();
        }
    }
    
    /**
     * classe qui écoute notre hard
     */
    class HardListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	dificult=4;
			boutonTurquoise.setEnabled(false);
			boutonBlack.setEnabled(false);
        	boutonPurple.setEnabled(true);
        	boutonFushia.setEnabled(true);
        	boutonYellow.setEnabled(true);
        	boutonWhite.setEnabled(true);
        	container.revalidate();
        	container.repaint();

        }
    }
	
	/**
     * classe qui écoute notre vhard
     */
    class VHardListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	container.remove(ligNotFull);
        	container.revalidate();
        	container.repaint();
        	dificult=5;
			boutonTurquoise.setEnabled(true);
			boutonBlack.setEnabled(true);
        	boutonPurple.setEnabled(true);
        	boutonFushia.setEnabled(true);
        	boutonYellow.setEnabled(true);
        	boutonWhite.setEnabled(true);
        	container.revalidate();
        	container.repaint();

        }
    }

	
	
    /**
     * classe qui écoute jouer
     */
    class JouerListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
			
		bouton.add(boutonvEasy);
     	bouton.add(boutonEasy);
     	bouton.add(boutonAverage);
     	bouton.add(boutonHard);
		bouton.add(boutonvHard);
     	
		boutonvEasy.addActionListener(new JouerListener());
     	boutonEasy.addActionListener(new JouerListener());
    	boutonAverage.addActionListener(new JouerListener());
    	boutonHard.addActionListener(new JouerListener());
		boutonvHard.addActionListener(new JouerListener());
		
        	if(arg0.getSource()==boutonvEasy){
        		dificult=1;
        		veasyMenu.setSelected(true);
        	}
			if(arg0.getSource()==boutonEasy){
        		dificult=2;
        		easyMenu.setSelected(true);
        	}
			if(arg0.getSource()==boutonAverage){
				dificult=3;	
				averageMenu.setSelected(true);
			}
			if(arg0.getSource()==boutonHard){
				dificult=4;
				hardMenu.setSelected(true);
			}
			if(arg0.getSource()==boutonvHard){
				dificult=5;
				vhardMenu.setSelected(true);
			}
			
			bouton.removeAll();
			container.revalidate();
			container.repaint();
    		initComposant();

        }
    }
    
  
    /**
     * classe qui écoute notre menu instruction
     */
    class InstrucListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	JOptionPane.showMessageDialog(null, "<html><center><h3>Instructions</h3></center> <br/> Le but du jeu est de trouver la bonne combinaison de couleur, g&eacute;n&eacute;r&eacute;e al&eacute;atoirement.<br/>Il est possible qu'une couleur apparaisse plusieurs fois dans la solution. <br/> Pour cela, vous avez &agrave; disposition, des boutons de diff&eacute;rentes couleurs.<br/> Lorsque vous cliquez sur l'un d'eux, la couleur choisie se met au bon endroit sur le plateau de jeu.<br/><br/>Une fois la ligne remplie, vous pouvez valider en cliquant sur le bouton correspondant.<br/><br/>Le plateau se met donc &agrave; jour, en affichant sur la droite de votre proposition, des petits pions : <ul><li>Noir: Bonne couleur, bonne place</li> <li>Blanc : Bonne couleur, mauvaise place </li> <li>Aucun pion : Mauvaise couleur</li></ul> La place des petits pions n'a aucune relation avec la place de vos couleurs, le premier petit pion<br/> n'indique pas forc&eacute;ment votre premi&egrave;re couleur.<br/><br/>Vous avez au maximum, 10 tentatives pour trouver la bonne combinaison.<br/><br/><center><h3>Bonne chance !</h3</center> </html>", "Instructions", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    
    
    /**
     * classe qui écoute notre menu a propos
     */
    class AboutListener  implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	JOptionPane.showMessageDialog(null, "<html> <b><u>Developpeurs</u> : <ul><li>Cristopher GOODMAN</li><li>Florian CHOISELLE</li><li>Benoit DEGORSAS</li><li>Saifeddine BERHOUMA</li></ul></html>", "A propos", JOptionPane.INFORMATION_MESSAGE);
    }
}   
    
    
    class CloseMenuListener implements ActionListener{
    	public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
    }
    
        


}
