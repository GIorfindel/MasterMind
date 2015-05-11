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
}
