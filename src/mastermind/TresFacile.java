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
}
