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
		lblMastermind.setBounds(175, 30, 700, 100);;
		lblMastermind.setVerticalAlignment( SwingConstants.TOP );
		lblMastermind.setHorizontalAlignment( SwingConstants.CENTER );
		this.add( lblMastermind );
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addNbPions() {
	
		JLabel labelNbPions = new JLabel("Nombre de pions");
	    labelNbPions.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    labelNbPions.setBounds(151, 130, 182, 50);
	    this.add(labelNbPions);
	    
	    JComboBox selectNbPions = new JComboBox();
	    selectNbPions.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    selectNbPions.setModel(new DefaultComboBoxModel(new String[] {"3", "4", "5", "6", "7", "8"}));
	    selectNbPions.setBounds(440, 140, 61, 34);
	    this.add(selectNbPions);
	}
	
	private void addCouleurs() {
	
		JLabel lblCouleurs = new JLabel("Choix des couleurs");
	    lblCouleurs.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    lblCouleurs.setBounds(151, 180, 200, 50);
	    this.add(lblCouleurs);
	    
	    JCheckBox checkRouge = new JCheckBox("");
	    checkRouge.setBackground(Color.WHITE);
	    checkRouge.setBounds(440, 189, 25, 25);
	    this.add(checkRouge);
	    
	    JCheckBox checkBleu = new JCheckBox("");
	    checkBleu.setBackground(Color.WHITE);
	    checkBleu.setBounds(534, 189, 25, 25);
	    this.add(checkBleu);
	    
	    JCheckBox checkVert = new JCheckBox("");
	    checkVert.setBackground(Color.WHITE);
	    checkVert.setBounds(628, 189, 25, 25);
	    this.add(checkVert);
	    
	    JCheckBox checkOrange = new JCheckBox("");
	    checkOrange.setBackground(Color.WHITE);
	    checkOrange.setBounds(722, 189, 25, 25);
	    this.add(checkOrange);
	    
	    JCheckBox checkBlanc = new JCheckBox("");
	    checkBlanc.setBackground(Color.WHITE);
	    checkBlanc.setBounds(816, 189, 25, 25);
	    this.add(checkBlanc);
	    
	    JCheckBox checkJaune = new JCheckBox("");
	    checkJaune.setBackground(Color.WHITE);
	    checkJaune.setBounds(440, 230, 25, 25);
	    this.add(checkJaune);
	    
	    JCheckBox checkMauve = new JCheckBox("");
	    checkMauve.setBackground(Color.WHITE);
	    checkMauve.setBounds(534, 230, 25, 25);
	    this.add(checkMauve);
	    
	    JCheckBox checkViolet = new JCheckBox("");
	    checkViolet.setBackground(Color.WHITE);
	    checkViolet.setBounds(628, 230, 25, 25);
	    this.add(checkViolet);
	    
	    JCheckBox checkNoir = new JCheckBox("");
	    checkNoir.setBackground(Color.WHITE);
	    checkNoir.setBounds(722, 230, 25, 25);
	    this.add(checkNoir);
	    
	    JCheckBox checkBVert = new JCheckBox("");
	    checkBVert.setBackground(Color.WHITE);
	    checkBVert.setBounds(816, 230, 25, 25);
	    this.add(checkBVert);
	    
        JLabel vert = new JLabel();
        vert.setLabelFor(checkVert);
        vert.setHorizontalAlignment(SwingConstants.CENTER);
        vert.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/3.png")));
	    vert.setBounds(654, 180, 42, 40);
        this.add(vert);
    
	    JLabel rouge = new JLabel();
	    rouge.setLabelFor(checkRouge);
	    rouge.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/1.png")));
	    rouge.setHorizontalAlignment(SwingConstants.CENTER);
	    rouge.setBounds(471, 180, 42, 40);
	    this.add(rouge);
	    
	    JLabel bleu = new JLabel();
	    bleu.setLabelFor(checkBleu);
	    bleu.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/2.png")));
	    bleu.setHorizontalAlignment(SwingConstants.CENTER);
	    bleu.setBounds(565, 180, 42, 40);
	    this.add(bleu);
	    
	    JLabel orange = new JLabel();
	    orange.setLabelFor(checkOrange);
	    orange.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/4.png")));
	    orange.setHorizontalAlignment(SwingConstants.CENTER);
	    orange.setBounds(753, 180, 42, 40);
	    this.add(orange);
	    
	    JLabel blanc = new JLabel();
	    blanc.setLabelFor(checkBlanc);
	    blanc.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/6.png")));
	    blanc.setHorizontalAlignment(SwingConstants.CENTER);
	    blanc.setBounds(847, 180, 42, 40);
	    this.add(blanc);
	    
	    JLabel jaune = new JLabel();
	    jaune.setLabelFor(checkJaune);
	    jaune.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/5.png")));
	    jaune.setHorizontalAlignment(SwingConstants.CENTER);
	    jaune.setBounds(471, 219, 42, 40);
	    this.add(jaune);
	    
	    JLabel mauve = new JLabel();
	    mauve.setLabelFor(checkMauve);
	    mauve.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/7.png")));
	    mauve.setHorizontalAlignment(SwingConstants.CENTER);
	    mauve.setBounds(565, 219, 42, 40);
	    this.add(mauve);
	    
	    JLabel violet = new JLabel();
	    violet.setLabelFor(checkViolet);
	    violet.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/8.png")));
	    violet.setHorizontalAlignment(SwingConstants.CENTER);
	    violet.setBounds(654, 219, 42, 40);
	    this.add(violet);
	    
	    JLabel noir = new JLabel();
	    noir.setLabelFor(checkNoir);
	    noir.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/10.png")));
	    noir.setHorizontalAlignment(SwingConstants.CENTER);
	    noir.setBounds(753, 220, 42, 40);
	    this.add(noir);
	    
	    JLabel bVert = new JLabel();
	    bVert.setLabelFor(checkBVert);
	    bVert.setIcon(new ImageIcon(Personnaliser.class.getResource("/ressources/11.png")));
	    bVert.setHorizontalAlignment(SwingConstants.CENTER);
	    bVert.setBounds(847, 219, 42, 40);
	    this.add(bVert);
	}
	
	private void addCouleursMultiples() {
		JLabel lblCouleursMultiples = new JLabel("Couleurs multiples");
	    lblCouleursMultiples.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    lblCouleursMultiples.setBounds(151, 265, 200, 50);
	    this.add(lblCouleursMultiples);
	    
	    final JRadioButton rdbtnDsactiver = new JRadioButton("Désactiver");
	    final JRadioButton rdbtnActiver = new JRadioButton("Activer");

	    rdbtnActiver.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    rdbtnActiver.setBackground(Color.WHITE);
	    rdbtnActiver.setBounds(440, 278, 120, 25);
	    rdbtnActiver.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  rdbtnActiver.setSelected(true);
		    	  rdbtnDsactiver.setSelected(false);
		      }
		    });
	    this.add(rdbtnActiver);
	    
	    rdbtnDsactiver.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    rdbtnDsactiver.setBackground(Color.WHITE);
	    rdbtnDsactiver.setBounds(571, 278, 130, 25);
	    rdbtnDsactiver.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  rdbtnDsactiver.setSelected(true);
		    	  rdbtnActiver.setSelected(false);
		      }
		    });
	    this.add(rdbtnDsactiver);
	    
	}
    
    private void addNbCoupsMax() {
    	JLabel lblNombreDeCoups = new JLabel("Nombre de coups maximum");
        lblNombreDeCoups.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    lblNombreDeCoups.setBounds(151, 315, 245, 50);
        this.add(lblNombreDeCoups);
        
        JTextField txtNbCoupsMax = new JTextField();
        txtNbCoupsMax.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    txtNbCoupsMax.setBounds(440, 324, 61, 34);
        this.add(txtNbCoupsMax);
        txtNbCoupsMax.setColumns(3);
    }
    
    
    private void addBoutonValider() {
    
	    JButton btnValider = new JButton("Valider");
	    btnValider.setBounds(405, 391, 150, 40);
	    btnValider.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){				
	    	}
	    });
	    this.add(btnValider);
    }
	
	private void addBoutonRetour(){
		JButton btn = new JButton( "Retour" );
		btn.setBounds(405, 444, 150, 40);
		btn.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){				
		    	  fenetre.showMenu( Fenetre.UNJOUEUR );
		      }
		    });
		this.add( btn );
	}

}
