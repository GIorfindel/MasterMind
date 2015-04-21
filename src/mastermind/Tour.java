package mastermind;

public class Tour {

	private int coups;
	//*Variable qui stock le nombre de combinaisons soumises par le joueur
	
	private Couleur[] combinaison;
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
	
	public boolean testCombinaison(Couleur[] coul)
	{
		boolean ret = true;
		if (coul.length != this.combinaison.length)
			ret = false;
		else
		{
			for (int i=0;i<coul.length;i++)
			{
				if (coul[i] != this.combinaison[i])
					ret = false;
			}
		}
		return ret;
	}
	//*Permet de verifier si la combinaison soumise est la même que la combinaison à deviner
}
