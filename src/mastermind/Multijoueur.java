package mastermind;

public class Multijoueur extends Partie {
	private static final long serialVersionUID = 7934630933946038006L;
	
	private static int COUPS_MAX = 100;
	
	private int coupsJ1;
	private int coupsJ2;
	private int compteur;
	private Pions comb;
	private Joueur joueur2;
	
	private int etat;
	private static int ETAT_CHERCHE_JOUEUR2 = 0, ETAT_CHOISIT_COMB = 1; 
	
	public Multijoueur(String nom, Niveau niveau, Joueur joueur) {
		super(nom, niveau, joueur);
		this.coupsJ1 = 0;
		this.coupsJ2 = 0;
		this.etat = ETAT_CHERCHE_JOUEUR2;
	}
}
