package mastermind;

public class TresDifficile extends Niveau {
	private static final long serialVersionUID = -6527184611263400561L;

	public TresDifficile(){
		this.pions = 4;
		this.couleurs = 10;
		this.doubl = true;
		this.coupMax = 14;
	}
	
	@Override public String toString()
	{
		return "TresDifficile";
	}
	
	public Couleur[] getCouleurAutorise(){
		Couleur[] cs = new Couleur[10];
		cs[0] = Couleur.Blanc;
		cs[1] = Couleur.Noir;
		cs[2] = Couleur.Bleu;
		cs[3] = Couleur.Cyan;
		cs[4] = Couleur.Jaune;
		cs[5] = Couleur.Orange;
		cs[6] = Couleur.Rose;
		cs[7] = Couleur.Rouge;
		cs[8] = Couleur.Vert;
		cs[9] = Couleur.Violet;
		return cs;
	}
	
	public String getNomNiveau() {
		return "Très difficile";
	}
}
