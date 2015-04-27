package mastermind;

import java.util.HashSet;
import java.util.Set;

public abstract class Niveau {
	protected int pions;
	/*definie le nombre de pions à deviner*/
	protected int couleurs;
	/*definie le nombre de couleurs utilisables*/
	protected boolean doubl;
	/*definie si l'utilisation de deux couleurs differentes dans la combinaison est autorisee*/
	protected int coupMax=10;
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
	
	public boolean validePions(Pions comb)
	{
		if(comb.getNbPion()==this.pions)
		{
			return true;
		}
		//Si la combinaison contient le même nombre de couleurs que dans la definition de la classe, on renvoie vrai
		else
		{
			return false;
		}
		//Sinon on renvoie faux
	}
	//*permet de vérifier que la combinaison comporte le bon nombre de pions*/

	public boolean valideCouleurs(Pions comb)
	{
		Couleur[] coul = comb.getCombinaison();
		Set<Couleur> unicColors = new HashSet<Couleur>();
		//On va utiliser un HashSet qui ne peut pas contenir de doublons
		for (Couleur c : coul) unicColors.add(c);
		//On ajoute les couleurs au HashSet, si un element est deja present il ne sera pas insere
		int unicNB = unicColors.size();
		//On recupere la taille du HashSet qui correspond au nombre de couleurs utilises
		if (unicNB>this.couleurs)
		{
			return false;
		}
		//Si le nombre de couleurs utilise est superieur a la valeur specifiee dans la classe, on renvoie faux
		else
		{
			return true;
		}
		//Sinon on renvoie vrai
	}
	//*permet de vérifier que la combinaison comporte le bon nombre de couleurs*/
	
	public boolean valideDoubl(Pions comb)
	{
		Couleur[] coul = comb.getCombinaison();
		Set<Couleur> unicColors = new HashSet<Couleur>();
		for (Couleur c : coul) unicColors.add(c);
		int unicNB = unicColors.size();
		if (unicNB<coul.length && this.doubl==false)
		{
			return false;
		}
		//Si le nombre de couleur sans doublons est inferieur a la longeur de la combinaison alors il y a des doublons, si les doublons ne sont pas autorises on renvoie faux
		else
		{
			return true;
		}
		//Sinon on renvoie vrai
	}
	//*permet de vérifier que la combinaison respecte bien la defintion du niveau pour les doubles*/
	
	public boolean valideCombinaison(Pions comb)
	{
		return (this.valideCouleurs(comb) && this.valideDoubl(comb) && this.validePions(comb));
	}
	//*Permet de verifier la validite d'une combinaison
	
	public static Niveau niveauString( String niveau ){
		if( niveau.equals("TresFacile") ){
			return new TresFacile();
		}else if( niveau.equals("Facile") ){
			return new Facile();
		}else if( niveau.equals("Normal") ){
			return new Normal();
		}else if( niveau.equals("Difficile") ){
			return new Difficile();
		}else{
			return new TresDifficile();
		}
	}
}
