package mastermind;

public class TresFacile extends Niveau {
	private static final long serialVersionUID = 3688921558307634307L;

	public TresFacile(){
		this.pions = 3;
		this.couleurs = 5;
		this.doubl = false;
	}
	
	@Override public String toString()
	{
		return "TresFacile";
	}
	
	public Couleur[] getCouleurAutorise(){
		Couleur[] cs = new Couleur[5];
		cs[0] = Couleur.Blanc;
		cs[1] = Couleur.Noir;
		cs[2] = Couleur.Bleu;
		cs[3] = Couleur.Cyan;
		cs[4] = Couleur.Jaune;
		return cs;
	}
}
