package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mastermind.Couleur;
import mastermind.Niveau;
import mastermind.NiveauPerso;

@SuppressWarnings("serial")
public class Personnaliser extends Menu{

	private Fenetre fenetre;
	
	private JComboBox selectNbPions;
	private JCheckBox checkRouge;
	private JCheckBox checkBleu;
	private JCheckBox checkVert;
	private JCheckBox checkOrange;
	private JCheckBox checkBlanc;
	private JCheckBox checkJaune;
	private JCheckBox checkRose;
	private JCheckBox checkViolet;
	private JCheckBox checkNoir;
	private JCheckBox checkCyan;
	
	private JRadioButton rdbtnDsactiver;
    private JRadioButton rdbtnActiver;
    
    private JTextField txtNbCoupsMax;
    
    private JLabel information;
	
	@SuppressWarnings("unused")
	private static int X = 405, Y = 130, W = 150, H = 40;
	
	public Personnaliser( Fenetre fenetre ){
		this.fenetre = fenetre;
		this.init();
	}
	
	private void init() {
		this.setLayout( null );
		this.setBackground( Color.WHITE );
		this.addLabelChoixMode();
		this.addInformation();
		this.addNbPions();
		this.addCouleurs();
		this.addCouleursMultiples();
		this.addNbCoupsMax();
		this.addBoutonValider();
		this.addBoutonRetour();
	}
	
	private void addLabelChoixMode(){
		JLabel lblMastermind = new JLabel( "Personnaliser une partie" );
		lblMastermind.setFont(new Font("Agency FB", Font.PLAIN, 50)); // Modification de la police
		lblMastermind.setBounds(150, 30, 660, 100);
		lblMastermind.setVerticalAlignment( SwingConstants.TOP );
		lblMastermind.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( lblMastermind );
	}
	
	private void addInformation(){
		this.information = new JLabel();
		this.information.setBounds(150, 100, 500, 30);
		this.information.setFont(new Font("Tahoma", Font.PLAIN, 17));
		this.information.setForeground(Color.red);
		this.add(this.information);
	}
	
	private void addNbPions() {
	
		JLabel labelNbPions = new JLabel("Nombre de pions");
	    labelNbPions.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    labelNbPions.setBounds(151, 130, 182, 50);
	    this.add(labelNbPions);
	    
	    this.selectNbPions = new JComboBox();
	    this. selectNbPions.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    this. selectNbPions.setModel(new DefaultComboBoxModel(new String[] {"3", "4", "5", "6", "7", "8"}));
	    this.selectNbPions.setBounds(440, 140, 61, 34);
	    this.add(this.selectNbPions);
	}
	
	private void addCouleurs() {
	
		JLabel lblCouleurs = new JLabel("Choix des couleurs");
	    lblCouleurs.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    lblCouleurs.setBounds(151, 210, 200, 50);
	    this.add(lblCouleurs);
	    
	    this.checkRouge = new JCheckBox("");
	    this.checkRouge.setBackground(Color.WHITE);
	    this.checkRouge.setBounds(440, 210, 25, 25);
	    this.add(this.checkRouge);
	    
	    this.checkBleu = new JCheckBox("");
	    this.checkBleu.setBackground(Color.WHITE);
	    this.checkBleu.setBounds(534, 210, 25, 25);
	    this.add(this.checkBleu);
	    
	    this.checkVert = new JCheckBox("");
	    this.checkVert.setBackground(Color.WHITE);
	    this.checkVert.setBounds(628, 210, 25, 25);
	    this.add(this.checkVert);
	    
	    this.checkOrange = new JCheckBox("");
	    this.checkOrange.setBackground(Color.WHITE);
	    this.checkOrange.setBounds(722, 210, 25, 25);
	    this.add(this.checkOrange);
	    
	    this.checkBlanc = new JCheckBox("");
	    this.checkBlanc.setBackground(Color.WHITE);
	    this.checkBlanc.setBounds(816, 210, 25, 25);
	    this.add(this.checkBlanc);
	    
	    this.checkJaune = new JCheckBox("");
	    this.checkJaune.setBackground(Color.WHITE);
	    this.checkJaune.setBounds(440, 250, 25, 25);
	    this.add(this.checkJaune);
	    
	    this.checkRose = new JCheckBox("");
	    this.checkRose.setBackground(Color.WHITE);
	    this.checkRose.setBounds(534, 250, 25, 25);
	    this.add(this.checkRose);
	    
	    this.checkViolet = new JCheckBox("");
	    this.checkViolet.setBackground(Color.WHITE);
	    this.checkViolet.setBounds(628, 250, 25, 25);
	    this.add(this.checkViolet);
	    
	    this.checkNoir = new JCheckBox("");
	    this.checkNoir.setBackground(Color.WHITE);
	    this.checkNoir.setBounds(722, 250, 25, 25);
	    this.add(this.checkNoir);
	    
	    this.checkCyan = new JCheckBox("");
	    this.checkCyan.setBackground(Color.WHITE);
	    this.checkCyan.setBounds(816, 250, 25, 25);
	    this.add(this.checkCyan);
	    
        JLabel vert = new JLabel();
        vert.setLabelFor(checkVert);
        vert.setHorizontalAlignment(SwingConstants.CENTER);
        vert.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/3.png")));
	    vert.setBounds(654, 200, 42, 40);
        this.add(vert);
    
	    JLabel rouge = new JLabel();
	    rouge.setLabelFor(checkRouge);
	    rouge.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/1.png")));
	    rouge.setHorizontalAlignment(SwingConstants.CENTER);
	    rouge.setBounds(471, 200, 42, 40);
	    this.add(rouge);
	    
	    JLabel bleu = new JLabel();
	    bleu.setLabelFor(checkBleu);
	    bleu.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/2.png")));
	    bleu.setHorizontalAlignment(SwingConstants.CENTER);
	    bleu.setBounds(565, 200, 42, 40);
	    this.add(bleu);
	    
	    JLabel orange = new JLabel();
	    orange.setLabelFor(checkOrange);
	    orange.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/4.png")));
	    orange.setHorizontalAlignment(SwingConstants.CENTER);
	    orange.setBounds(753, 200, 42, 40);
	    this.add(orange);
	    
	    JLabel blanc = new JLabel();
	    blanc.setLabelFor(checkBlanc);
	    blanc.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/6.png")));
	    blanc.setHorizontalAlignment(SwingConstants.CENTER);
	    blanc.setBounds(847, 200, 42, 40);
	    this.add(blanc);
	    
	    JLabel jaune = new JLabel();
	    jaune.setLabelFor(checkJaune);
	    jaune.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/5.png")));
	    jaune.setHorizontalAlignment(SwingConstants.CENTER);
	    jaune.setBounds(471, 240, 42, 40);
	    this.add(jaune);
	    
	    JLabel mauve = new JLabel();
	    mauve.setLabelFor(checkRose);
	    mauve.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/7.png")));
	    mauve.setHorizontalAlignment(SwingConstants.CENTER);
	    mauve.setBounds(565, 240, 42, 40);
	    this.add(mauve);
	    
	    JLabel violet = new JLabel();
	    violet.setLabelFor(checkViolet);
	    violet.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/8.png")));
	    violet.setHorizontalAlignment(SwingConstants.CENTER);
	    violet.setBounds(654, 240, 42, 40);
	    this.add(violet);
	    
	    JLabel noir = new JLabel();
	    noir.setLabelFor(checkNoir);
	    noir.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/10.png")));
	    noir.setHorizontalAlignment(SwingConstants.CENTER);
	    noir.setBounds(753, 240, 42, 40);
	    this.add(noir);
	    
	    JLabel cyan = new JLabel();
	    cyan.setLabelFor(checkCyan);
	    cyan.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/11.png")));
	    cyan.setHorizontalAlignment(SwingConstants.CENTER);
	    cyan.setBounds(847, 240, 42, 40);
	    this.add(cyan);
	}
	
	private void addCouleursMultiples() {
		JLabel lblCouleursMultiples = new JLabel("Couleurs multiples");
	    lblCouleursMultiples.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    lblCouleursMultiples.setBounds(151, 310, 200, 50);
	    this.add(lblCouleursMultiples);
	    
	    this.rdbtnDsactiver = new JRadioButton("Désactiver");
	    this.rdbtnActiver = new JRadioButton("Activer");

	    this.rdbtnActiver.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    this.rdbtnActiver.setBackground(Color.WHITE);
	    this.rdbtnActiver.setBounds(440, 320, 120, 25);
	    this.rdbtnActiver.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  rdbtnActiver.setSelected(true);
		    	  rdbtnDsactiver.setSelected(false);
		      }
		    });
	    this.add(this.rdbtnActiver);
	    
	    this.rdbtnDsactiver.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    this.rdbtnDsactiver.setBackground(Color.WHITE);
	    this.rdbtnDsactiver.setBounds(570, 320, 130, 25);
	    this.rdbtnDsactiver.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  rdbtnDsactiver.setSelected(true);
		    	  rdbtnActiver.setSelected(false);
		      }
		    });
	    this.add(this.rdbtnDsactiver);
	    
	}
    
    private void addNbCoupsMax() {
    	JLabel lblNombreDeCoups = new JLabel("Nombre de coups maximum");
        lblNombreDeCoups.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    lblNombreDeCoups.setBounds(151, 370, 245, 50);
        this.add(lblNombreDeCoups);
        
        this.txtNbCoupsMax = new JTextField();
        this.txtNbCoupsMax.setFont(new Font("Tahoma", Font.PLAIN, 17));
        this.txtNbCoupsMax.setBounds(440, 375, 60, 35);
        this.add(this.txtNbCoupsMax);
        this.txtNbCoupsMax.setColumns(3);
    }
    
    
    private void addBoutonValider() {
    
	    JButton btnValider = new JButton("Valider");
	    btnValider.setBounds(405, 440, 150, 50);
	    btnValider.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		String s = (String) selectNbPions.getSelectedItem();
	    		int nbPions;
	    		try{
	    			nbPions = Integer.parseInt(s);
	    		}catch( NumberFormatException e ){
	    			information.setText("Nombre de pion invalide");
	    			return;
	    		}
	    		
	    		int nbCouleurPoss = 0;
	    		if( checkBlanc.isSelected() ){
	    			nbCouleurPoss++;
	    		}
	    		if( checkBleu.isSelected() ){
	    			nbCouleurPoss++;
	    		}
	    		if( checkCyan.isSelected() ){
	    			nbCouleurPoss++;
	    		}
	    		if( checkJaune.isSelected() ){
	    			nbCouleurPoss++;
	    		}
	    		if( checkNoir.isSelected() ){
	    			nbCouleurPoss++;
	    		}
	    		if( checkOrange.isSelected() ){
	    			nbCouleurPoss++;
	    		}
	    		if( checkRose.isSelected() ){
	    			nbCouleurPoss++;
	    		}
	    		if( checkRouge.isSelected() ){
	    			nbCouleurPoss++;
	    		}
	    		if( checkVert.isSelected() ){
	    			nbCouleurPoss++;
	    		}
	    		if( checkViolet.isSelected() ){
	    			nbCouleurPoss++;
	    		}
	    		if(nbCouleurPoss == 0){
	    			information.setText("Nombre de couleur invalide");
	    			return;
	    		}
	    		Couleur[] couleurPossib = new Couleur[nbCouleurPoss];
	    		if( checkBlanc.isSelected() ){
	    			nbCouleurPoss--;
	    			couleurPossib[nbCouleurPoss] = Couleur.Blanc;
	    		}
	    		if( checkBleu.isSelected() ){
	    			nbCouleurPoss--;
	    			couleurPossib[nbCouleurPoss] = Couleur.Bleu;
	    		}
	    		if( checkCyan.isSelected() ){
	    			nbCouleurPoss--;
	    			couleurPossib[nbCouleurPoss] = Couleur.Cyan;
	    		}
	    		if( checkJaune.isSelected() ){
	    			nbCouleurPoss--;
	    			couleurPossib[nbCouleurPoss] = Couleur.Jaune;
	    		}
	    		if( checkNoir.isSelected() ){
	    			nbCouleurPoss--;
	    			couleurPossib[nbCouleurPoss] = Couleur.Noir;
	    		}
	    		if( checkOrange.isSelected() ){
	    			nbCouleurPoss--;
	    			couleurPossib[nbCouleurPoss] = Couleur.Orange;
	    		}
	    		if( checkRose.isSelected() ){
	    			nbCouleurPoss--;
	    			couleurPossib[nbCouleurPoss] = Couleur.Rose;
	    		}
	    		if( checkRouge.isSelected() ){
	    			nbCouleurPoss--;
	    			couleurPossib[nbCouleurPoss] = Couleur.Rouge;
	    		}
	    		if( checkVert.isSelected() ){
	    			nbCouleurPoss--;
	    			couleurPossib[nbCouleurPoss] = Couleur.Vert;
	    		}
	    		if( checkViolet.isSelected() ){
	    			nbCouleurPoss--;
	    			couleurPossib[nbCouleurPoss] = Couleur.Violet;
	    		}
	    		
	    		boolean doubl = false;
	    		if( !rdbtnActiver.isSelected() && !rdbtnDsactiver.isSelected() ){
	    			information.setText("Choisisser le choix multiple");
	    			return;
	    		}
	    		if( rdbtnActiver.isSelected() ){
	    			doubl = true;
	    		}
	    		if( !doubl && couleurPossib.length < nbPions ){
	    			information.setText("Il y a trop de pion par rapport aux couleurs");
    				return;
    			}
	    		
	    		String ch = txtNbCoupsMax.getText();
	    		int coupMax;
	    		try{
	    			coupMax = Integer.parseInt(ch);
	    		}catch( NumberFormatException e ){
	    			information.setText("Coups max invalide");
	    			return;
	    		}
	    		quitter();
	    		Niveau n = new NiveauPerso(nbPions, couleurPossib.length, doubl, coupMax, couleurPossib);
	    		fenetre.setNiveauSolo( n );
	    		fenetre.showMenu(Fenetre.SOLO);
	    	}
	    });
	    this.add(btnValider);
    }
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds(405, 500, 150, 50);
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  quitter();
		    	  fenetre.showMenu( Fenetre.UNJOUEUR );
		      }
		    });
		this.add( btn );
	}
	
	public void quitter(){
		information.setText("");
	}

}
