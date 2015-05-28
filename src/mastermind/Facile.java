package mastermind;

public class Facile extends Niveau {
	private static final long serialVersionUID = -1811140477631022948L;

	public Facile(){
		this.pions = 4;
		this.couleurs = 6;
		this.doubl = false;
	}
	
	@Override public String toString()
	{
		return "Facile";
	}
	
	public Couleur[] getCouleurAutorise(){
		Couleur[] cs = new Couleur[6];
		cs[0] = Couleur.Blanc;
		cs[1] = Couleur.Noir;
		cs[2] = Couleur.Bleu;
		cs[3] = Couleur.Cyan;
		cs[4] = Couleur.Jaune;
		cs[5] = Couleur.Orange;
		return cs;
	}
	
	public String getNomNiveau() {
		return "Facile";
	}
}
