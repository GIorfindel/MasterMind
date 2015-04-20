package mastermind;

public class Partie {
	
	/* Variables */
	private String nom; //Nom de la partie
	
	
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
		
}