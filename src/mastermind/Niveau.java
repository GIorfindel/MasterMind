package mastermind;

public abstract class Niveau {
	protected int pions;
	/*definie le nombre de pions à deviner*/
	protected int couleurs;
	/*definie le nombre de couleurs utilisables*/
	protected boolean doubl;
	/*definie si l'utilisation de deux couleurs differentes dans la combinaison est autorisee*/
	protected int coupMax;
	/*definie le nombre de combinaisons maximal que peut soumettre l'utilisateur pour un tour*/
	
	
	
	public int getPions()
	{
		return this.pions;
	}
	//*permet de retourner le nombre de pions à deviner*/
	public void setPions(int nb)
	{
		this.pions = nb;
	}
	//*permet de definir le nombre de pions à deviner*/
	
	
	public int getCouleurs()
	{
		return this.couleurs;
	}
	//*permet de retourner le nombre de couleurs utilisables*/
	public void setCouleurs(int nb)
	{
		this.couleurs = nb;
	}
	//*permet de definir le nombre de couleurs utilisables*/
	
	
	public boolean getDouble()
	{
		return this.doubl;
	}
	//*permet de retourner la variable autorisant ou non les doubles*/
	public void setDouble(boolean b)
	{
		this.doubl= b;
	}
	//*permet d'autoriser ou non les doubles*/
	
	
	public int getCoupMax()
	{
		return this.coupMax;
	}
	//*permet de retourner le nombre de combinaisons maximal que l'on peut soumettre*/
	public void setCoupMax(int nb)
	{
		this.coupMax = nb;
	}
	//*permet de definir le nombre de combinaisons maximal que l'on peut soumettre*/
}
