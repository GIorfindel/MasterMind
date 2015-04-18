package mastermind;

public class Score {
	/* Le mode représente le mode multijoueur ou solo */
	private String mode;
	/* Le coups est le nombre de combinaisons que le joueur a fait pour trouver la bonne combinaisons */
	private int coups;
	/* */
	private boolean victoire;
	/* Le nombre de tour de la partie */
	private int tours;
	
	/* Constructeur par defaut */
	public Score(){
		this.mode = "defaut";
		this.coups = 0;
		this.victoire = false;
		this.tours = 0;
	}
	
	
	/* Constructeur par paramètre */
	public Score( String mode, int coups, boolean victoire, int tour ){
		this.mode = mode;
		this.coups = coups;
		this.victoire = victoire;
		this.tours = tour;
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
	/* Fin des methodes set et get des varaibles */
	
}
