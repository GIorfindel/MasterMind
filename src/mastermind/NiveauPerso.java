package mastermind;

public class NiveauPerso extends Niveau {
	
	private Couleur[] couleurPoss;
	
	public NiveauPerso (int pions, int couleurs, boolean doubl, int coupMax, Couleur[] couleurPoss) {
		this.pions = pions;
		this.couleurs = couleurs;
		this.doubl = doubl;
		this.coupMax = coupMax;
		this.couleurPoss = couleurPoss;
	}
	public Couleur[] getCouleurAutorise(){
		return this.couleurPoss;
	}
	
}
