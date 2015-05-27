package mastermind;

public class Multijoueur extends Partie {
	private static final long serialVersionUID = 7934630933946038006L;
	
	private final static int COUPS_MAX = 100;
	
	private int coupsJ1;
	private int coupsJ2;
	private int compteur;
	private Pions comb;
	private Joueur joueur2;
	
	private int etat;
	public static int ETAT_CHERCHE_JOUEUR2 = 0, ETAT_ATTENTE_JOUER = 1;
	
	public Multijoueur(String nom, Niveau niveau, Joueur joueur) {
		super(nom, niveau, joueur);
		this.coupsJ1 = 0;
		this.coupsJ2 = 0;
		this.etat = ETAT_CHERCHE_JOUEUR2;
		this.joueur2 = null;
		this.niveau = niveau;
	}
	
	public Joueur getJoueur2(){
		return this.joueur2;
	}
	
	public void setJoueur2( Joueur joueur2 ){
		this.joueur2 = joueur2;
		this.etat = ETAT_ATTENTE_JOUER;
	}
	
	public void kickJoueur2(){
		this.joueur2 = null;
		this.etat = ETAT_CHERCHE_JOUEUR2;
	}
	
	public int getEtat(){
		return this.etat;
	}
}
