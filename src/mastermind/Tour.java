package mastermind;

import java.util.ArrayList;

public class Tour {

	private int coups;
	//*Variable qui stock le nombre de combinaisons soumises par le joueur
	
	private Pions combinaison;
	//*Variable qui stock la combinaison à deviner
	
	private ArrayList<Pions> essais;
	//Variable qui stocke les essais pour trouver la combinaison
	
	private ArrayList<Pions> aides;
	//Variable qui stocke l'aide pour chaque essai
	
	public Tour(int nbPions){
		this.coups = 0;
		this.combinaison = new Pions(nbPions);
		this.essais = new ArrayList<Pions>();
		this.aides = new ArrayList<Pions>();
	}
	
	public ArrayList<Pions> getAides(){
		return this.aides;
	}
	//Retourne la liste des aides
	
	public void setAides( ArrayList<Pions> aides ){
		this.aides = aides;
	}
	
	public void addAide( Pions pions ){
		this.aides.add(pions);
	}
	//Ajoute une nouvelle aide
	
	public ArrayList<Pions> getEssais(){
		return this.essais;
	}
	//Retourne la liste des essais
	
	public void setEssais( ArrayList<Pions> essais ){
		this.essais = essais;
	}
	//Fixe les essais
	
	public void addEssai( Pions pions ){
		this.essais.add(pions);
	}
	//Ajoute un nouveau essai
	
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
