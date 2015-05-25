package mastermind;

import java.io.Serializable;

public class Score implements Serializable{
	private static final long serialVersionUID = -3270491984915029590L;

	/* Le mode représente le mode multijoueur ou solo */
	private String mode;
	
	public static String MODE_SOLO = "solo";
	public static String MODE_MULTI = "multi";
	
	/* Le coups est le nombre de combinaisons que le joueur a fait pour trouver la bonne combinaisons */
	private int coups;
	
	/* Si le joueur a gagné ou perdu la partie */
	private boolean victoire;
	
	/* Le nombre de tour de la partie */
	private int tours;
	
	/* Le niveau auquel le joueur a joué */
	private Niveau niveau;
	
	/* Constructeur par defaut */
	public Score(){
		this.mode = "defaut";
		this.coups = 0;
		this.victoire = false;
		this.tours = 0;
	}
	
	
	/* Constructeur par paramètre */
	public Score( String mode, int coups, boolean victoire, int tour,Niveau n ){
		this.mode = mode;
		this.coups = coups;
		this.victoire = victoire;
		this.tours = tour;
		this.niveau = n;
	}
	
	/* Methodes set et get des varaibles */
	
	public String getMode(){
		return this.mode;
	}
	
	public void setMode( String mode ){
		this.mode = mode;
	}
	
	public int getCoups(){
		return this.coups;
	}
	
	public void setCoups( int coups ){
		this.coups = coups;
	}
	
	public boolean getVictoire(){
		return this.victoire;
	}
	
	public void setVictoire( boolean victoire ){
		this.victoire = victoire;
	}
	
	public int getTours(){
		return this.tours;
	}
	
	public void setTours( int tours ){
		this.tours = tours;
	}
	
	public Niveau getNiveau(){
		return this.niveau;
	}
	
	public void setNiveau( Niveau niveau ){
		this.niveau = niveau;
	}
	
	/* Fin des methodes set et get des varaibles */
	
}
