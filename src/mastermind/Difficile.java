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
}
