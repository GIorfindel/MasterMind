package mastermind;

public class Normal extends Niveau {
	private static final long serialVersionUID = -2028286672649281065L;

	public Normal(){
		this.pions = 4;
		this.couleurs = 8;
		this.doubl = false;
	}
	
	@Override public String toString()
	{
		return "Normal";
	}
}
