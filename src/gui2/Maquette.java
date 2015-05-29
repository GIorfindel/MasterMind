package gui2;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mastermind.Couleur;
import mastermind.Niveau;
import mastermind.Pions;
import mastermind.Tour;

@SuppressWarnings("serial")
public class Maquette extends JPanel{
	
	private static final int WIDTH_AIDE_VIDE = 12;
	private static final int HEIGHT_AIDE_VIDE = 12;
	
	private static final int WIDTH_PION_VIDE = 30;
	private static final int HEIGHT_PION_VIDE = 30;
	
	private static final int WIDTH_PION_SOLU_VIDE = 30;
	private static final int HEIGHT_PION_SOLU_VIDE = 30;

	private Niveau niveau;
		
	private JLabel[][] plateau;
	private JLabel[][] plateauAides;
	private JLabel[] plateauSolution;
	
	private ImageIcon pionsVide;
	private ImageIcon aideVide;
	private ImageIcon solucePion;
	
	private ImageIcon rouge;
	private ImageIcon jaune;
	private ImageIcon vert;
	private ImageIcon bleu;
	private ImageIcon orange;
	private ImageIcon blanc;
	private ImageIcon violet;
	private ImageIcon cyan;
	private ImageIcon rose;
	private ImageIcon noir;
	
	private ImageIcon aide_noir;
	private ImageIcon aide_blanc;
	
	public Maquette( Niveau niveau ){
		this.setLayout(null);
		this.niveau = niveau;
		this.init();
	}
	
	private void init(){
		this.plateau = new JLabel[this.niveau.getCoupMax()][this.niveau.getPions()];
		this.plateauAides = new JLabel[this.niveau.getCoupMax()][this.niveau.getPions()];
		this.plateauSolution = new JLabel[this.niveau.getPions()];
		
		this.pionsVide = new ImageIcon( "images/maquette/pionVide.png" );
		this.aideVide = new ImageIcon( "images/maquette/aideVide.png" );
		this.solucePion = new ImageIcon( "images/maquette/pionSoluce.png" );
		
		int x_aide = this.niveau.getPions() * 35 + 10;
		
     	for(int i=0;i< this.niveau.getCoupMax();i++){
    		for(int j=0;j< this.niveau.getPions();j++){
    			this.plateau[i][j]= new JLabel( this.pionsVide );
    			this.plateau[i][j].setBounds(j*35+10, i*35+10, WIDTH_PION_VIDE, HEIGHT_PION_VIDE);
    			this.add(this.plateau[i][j]);
    			
    			this.plateauAides[i][j]= new JLabel( this.aideVide );
    			if( j%2 == 0 ){
    				this.plateauAides[i][j].setBounds(j*7+x_aide, i*35+16, WIDTH_AIDE_VIDE, HEIGHT_AIDE_VIDE);
    			}else{
    				this.plateauAides[i][j].setBounds(j*7+x_aide-7, i*35+30, WIDTH_AIDE_VIDE, HEIGHT_AIDE_VIDE);
    			}
    			this.add(this.plateauAides[i][j]);
    		}
    	}
     	
     	for(int i=0;i<this.niveau.getPions();i++){
			this.plateauSolution[i]= new JLabel( this.solucePion );
			this.plateauSolution[i].setBounds(i*35+10, this.niveau.getCoupMax()*35+20, WIDTH_PION_SOLU_VIDE, HEIGHT_PION_SOLU_VIDE);
			this.add(this.plateauSolution[i]);
     	}
     	
     	this.rouge = new ImageIcon( "images/maquette/rouge.png" );
     	this.jaune = new ImageIcon( "images/maquette/jaune.png" );
     	this.vert = new ImageIcon( "images/maquette/vert.png" );
     	this.bleu = new ImageIcon( "images/maquette/bleu.png" );
     	this.orange = new ImageIcon( "images/maquette/orange.png" );
     	this.blanc = new ImageIcon( "images/maquette/blanc.png" );
     	this.violet = new ImageIcon( "images/maquette/violet.png" );
     	this.cyan = new ImageIcon( "images/maquette/cyan.png" );
     	this.rose = new ImageIcon( "images/maquette/rose.png" );
     	this.noir = new ImageIcon( "images/maquette/noir.png" );
     	
     	this.aide_blanc = new ImageIcon( "images/maquette/aide_blanc.png" );
     	this.aide_noir = new ImageIcon( "images/maquette/aide_noir.png" );
     	
     	
	}
	
	//essai va de 0 à getCoupMax() exclu. indicePion va de 0 à getPions() exclu
	public void addPion( int essai, int indicePion, Couleur c ){
		this.plateau[this.niveau.getCoupMax() - essai - 1][indicePion].setIcon( this.getImage(c) );
	}
	
	public void addPions( int essai, Pions p ){
		for( int i =0; i<p.getNbPion();i++ ){
			this.addPion(essai, i, p.getPion(i));
		}
	}
	
	//essai va de 0 à getCoupMax() exclu. indicePion va de 0 à getPions() exclu
	public void popPion( int essai, int indicePion ){
		this.plateau[this.niveau.getCoupMax() - essai - 1][indicePion].setIcon( this.pionsVide );
	}
	
	//essai va de 0 à getCoupMax() exclu.
	public void popEssai( int essai ){
		for( int i= 0; i < this.niveau.getPions(); i++ ){
			this.popPion(essai, i);
		}
	}
	
	public void addAide( int essai, int nbNoir, int nbBlanc ){
		int compt = 0;
		for( int i= 0; i < nbNoir; i++ ){
			this.plateauAides[this.niveau.getCoupMax() - essai - 1][compt].setIcon( this.aide_noir );
			compt++;
		}
		for( int i= 0; i < nbBlanc; i++ ){
			this.plateauAides[this.niveau.getCoupMax() - essai - 1][compt].setIcon( this.aide_blanc );
			compt++;
		}
	}
	
	public void dessineSolution( Pions soluce ){
		for( int i = 0; i < soluce.getNbPion(); i++ ){
			this.plateauSolution[i].setIcon( this.getImage( soluce.getPion(i) ) );
		}
	}
	
	public void reset(){
		for(int i=0;i< this.niveau.getCoupMax();i++){
    		for(int j=0;j< this.niveau.getPions();j++){
    			this.plateau[i][j].setIcon(this.pionsVide);
    			this.plateauAides[i][j].setIcon(this.aideVide);
    		}
		}
		for( int i = 0; i < this.niveau.getPions(); i++ ){
			this.plateauSolution[i].setIcon(this.solucePion);
		}
	}
	
	//Utile pour dessiner la partie chargé
	public void setMaquette( Tour tour ){
		Pions pions = null;
		for( int essai = 0; essai < tour.getCoups(); essai++ ){
			pions = tour.getEssai(essai);
			for( int pion = 0; pion < pions.getNbPion(); pion++ ){
				this.addPion(essai, pion, pions.getPion(pion) );
			}
			this.addAide(essai, tour.getNombreBonnePosition( essai ), tour.getNombreBonneCouleur(essai));
		}
	}
	
	
	
	private ImageIcon getImage( Couleur c ){
		if( c == Couleur.Blanc ){
			return this.blanc;
		}else if( c == Couleur.Bleu ){
			return this.bleu;
		}else if( c == Couleur.Cyan ){
			return this.cyan;
		}else if( c == Couleur.Jaune ){
			return this.jaune;
		}else if( c == Couleur.Noir ){
			return this.noir;
		}else if( c == Couleur.Orange ){
			return this.orange;
		}else if( c == Couleur.Rose ){
			return this.rose;
		}else if( c == Couleur.Rouge ){
			return this.rouge;
		}else if( c == Couleur.Vert ){
			return this.vert;
		}else if( c == Couleur.Violet ){
			return this.violet;
		}
		return null;
	}

}
