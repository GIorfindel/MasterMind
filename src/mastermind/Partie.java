package mastermind;

public class Partie {
	
	/* Variables */
	private String nom; //Nom de la partie
	private Niveau niveau;
	private Joueur jouer;
	
	/* Constructeur par défaut */
	public Partie() {
		this.nom = "Partie";
	}
	
	/* Constructeur selon les paramètres */
	public Partie(String nom) {
		this.nom = nom;
	}
	
	
	
	/* Méthodes */
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom( String nom ) {
		this.nom = nom;
	}
	
	public Niveau getNiveau() {
		return this.niveau;
	}
	
	public void setNiveau( Niveau niveau ) {
		this.niveau = niveau;
	}
	
	public Joueur getJoueur() {
		return this.joueur;
	}
	
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	public void gagner( int coups, Niveau niveau, Joueur joueur) {
		if (coups < niveau.getCoupMax()) { // Si le nombre de coups est inférieur à coupsMax && si la combinaison est exacte
			/*Le joueur gagne la partie*/
		}
	}

	public void perdre( int coups, Niveau niveau, Joueur joueur) {
		if (coups == niveau.getCoupMax()) { // Si le nombre de coups est égal à coupsMax && si la combinaison est inexacte 
			/*Le joueur perd la partie*/
		}
	}
	
	//Créé une partie personnalisée
	public void personnaliserNiveau(int pions, int couleurs, boolean doubl) {
		niveau.couleurs = couleurs;
		niveau.pions = pions;
		niveau.doubl = doubl;
	}
		
}