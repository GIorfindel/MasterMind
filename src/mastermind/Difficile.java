package mastermind;

public class Difficile extends Niveau {
	private static final long serialVersionUID = 338367844102185989L;

	public Difficile(){
		this.pions = 4;
		this.couleurs = 8;
		this.doubl = true;
		this.coupMax = 12;
	}
	
	@Override public String toString()
	{
		return "Difficile";
	}
	
	public Couleur[] getCouleurAutorise(){
		Couleur[] cs = new Couleur[8];
		cs[0] = Couleur.Blanc;
		cs[1] = Couleur.Noir;
		cs[2] = Couleur.Bleu;
		cs[3] = Couleur.Cyan;
		cs[4] = Couleur.Jaune;
		cs[5] = Couleur.Orange;
		cs[6] = Couleur.Rose;
		cs[7] = Couleur.Rouge;
		return cs;
	}
}
