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
}
