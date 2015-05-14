package gui2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Personnaliser extends Menu{

	private Fenetre fenetre;
	
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
		lblMastermind.setBounds(175, 30, 600, 100);;
		lblMastermind.setVerticalAlignment( SwingConstants.TOP );
		lblMastermind.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( lblMastermind );
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addNbPions() {
	
		JLabel labelNbPions = new JLabel("Nombre de pions");
	    labelNbPions.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    labelNbPions.setBounds(151, 136, 182, 50);
	    this.add(labelNbPions);
	    
	    JComboBox selectNbPions = new JComboBox();
	    selectNbPions.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    selectNbPions.setEditable(true);
	    selectNbPions.setModel(new DefaultComboBoxModel(new String[] {"3", "4", "5", "6", "7", "8"}));
	    selectNbPions.setBounds(440, 145, 61, 34);
	    this.add(selectNbPions);
	}
	
	private void addCouleurs() {
	
		JLabel lblCouleurs = new JLabel("Choix des couleurs");
	    lblCouleurs.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    lblCouleurs.setBounds(151, 186, 200, 50);
	    this.add(lblCouleurs);
    
	    JCheckBox checkRouge = new JCheckBox("Rouge");
	    checkRouge.setBounds(440, 201, 90, 25);
	    this.add(checkRouge);
	    
	    JCheckBox checkBleu = new JCheckBox("Bleu");
	    checkBleu.setBounds(534, 201, 90, 25);
	    this.add(checkBleu);
	    
	    JCheckBox checkVert = new JCheckBox("Vert");
	    checkVert.setBounds(628, 201, 90, 25);
	    this.add(checkVert);
	    
	    JCheckBox checkOrange = new JCheckBox("Orange");
	    checkOrange.setBounds(722, 201, 90, 25);
	    this.add(checkOrange);
	    
	    JCheckBox checkBlanc = new JCheckBox("Blanc");
	    checkBlanc.setBounds(816, 201, 90, 25);
	    this.add(checkBlanc);
	    
	    JCheckBox checkJaune = new JCheckBox("Jaune");
	    checkJaune.setBounds(440, 236, 90, 25);
	    this.add(checkJaune);
	    
	    JCheckBox checkMauve = new JCheckBox("Mauve");
	    checkMauve.setBounds(534, 236, 90, 25);
	    this.add(checkMauve);
	    
	    JCheckBox checkViolet = new JCheckBox("Violet");
	    checkViolet.setBounds(628, 236, 90, 25);
	    this.add(checkViolet);
	    
	    JCheckBox checkNoir = new JCheckBox("Noir");
	    checkNoir.setBounds(722, 236, 90, 25);
	    this.add(checkNoir);
	    
	    JCheckBox checkBVert = new JCheckBox("Emeraude");
	    checkBVert.setBounds(816, 236, 90, 25);
	    this.add(checkBVert);
	}
	
	private void addCouleursMultiples() {
		JLabel lblCouleursMultiples = new JLabel("Couleurs multiples");
	    lblCouleursMultiples.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    lblCouleursMultiples.setBounds(151, 280, 200, 50);
	    this.add(lblCouleursMultiples);
	    
	    JRadioButton rdbtnActiver = new JRadioButton("Activer");
	    rdbtnActiver.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    rdbtnActiver.setBounds(440, 293, 115, 25);
	    this.add(rdbtnActiver);
	    
	    JRadioButton rdbtnDsactiver = new JRadioButton("Désactiver");
	    rdbtnDsactiver.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    rdbtnDsactiver.setBounds(568, 293, 115, 25);
	    this.add(rdbtnDsactiver);
	    
	}
    
    private void addNbCoupsMax() {
    	JLabel lblNombreDeCoups = new JLabel("Nombre de coups maximum");
        lblNombreDeCoups.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNombreDeCoups.setBounds(151, 341, 245, 50);
        this.add(lblNombreDeCoups);
        
        JTextField txtNbCoupsMax = new JTextField();
        txtNbCoupsMax.setFont(new Font("Tahoma", Font.PLAIN, 17));
        txtNbCoupsMax.setBounds(440, 350, 61, 34);
        this.add(txtNbCoupsMax);
        txtNbCoupsMax.setColumns(3);
    }
    
    
    private void addBoutonValider() {
    
	    JButton btnValider = new JButton("Valider");
	    btnValider.setBounds(405, 403, 150, 40);
	    btnValider.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){				
	    	}
	    });
	    this.add(btnValider);
    }
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds(405, 456, 150, 40);
		Y += H+10;
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  fenetre.showMenu( Fenetre.UNJOUEUR );
		      }
		    });
		this.add( btn );
	}

}
