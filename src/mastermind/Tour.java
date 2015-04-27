package mastermind;

public class Tour {

	private int coups;
	//*Variable qui stock le nombre de combinaisons soumises par le joueur
	
	private Pions combinaison;
	//*Variable qui stock la combinaison à deviner
	
	public int getCoups()
	{
		return this.coups;
	}
	//*Permet de recuperer le nombre de combinaisons soumises par le joueur
	
	public void setCoups(int nb)
	{
		this.coups = nb;
	}
	//*Permet de definir le nombre de combinaisons soumises par le joueur
	
	public Pions getComb()
	{
		return this.combinaison;
	}
	//*Permet de recuperer le nombre de combinaisons soumises par le joueur
	
	public void setComb(Pions comb)
	{
		this.combinaison = comb;
	}
		
	public boolean testCombinaison(Pions comb)
	{
		boolean ret = true;
		if (comb.getNbPion() != this.combinaison.getNbPion())
			ret = false;
		else
		{
			for (int i=0;i<comb.getNbPion();i++)
			{
				if (comb.getPion(i) != this.combinaison.getPion(i))
					ret = false;
			}
		}
		return ret;
	}
	//*Permet de verifier si la combinaison soumise est la même que la combinaison à deviner
}
