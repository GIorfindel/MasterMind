package mastermind;

public class Pions {
	private Couleur[] combinaison;
	private int nbPions;
	
	public Pions( int nbPions ){
		this.combinaison = new Couleur[nbPions];
		this.nbPions = 0;
	}
	
	public Pions( Couleur[] coul ){
		this.combinaison = coul;
		this.nbPions = coul.length;
	}
	
	public void addPion( Couleur couleur ){
		this.combinaison[this.nbPions] = couleur;
		this.nbPions ++;
	}
	
	public void addPion( Couleur couleur, int indice ){
		this.combinaison[indice] = couleur;
		this.nbPions ++;
	}
	
	public Couleur getPion( int indice ){
		return this.combinaison[indice];
	}
	
	public int getNbPion(){
		return this.nbPions;
	}
	
	public Couleur[] getCombinaison(){
		return this.combinaison;
	}
	
	public boolean equals(Pions comb){
		if (comb.getNbPion() != this.getNbPion())
			return false;
		else
		{
			for (int i=0; i<comb.getNbPion();i++)
			{
				if (comb.getPion(i) != this.getPion(i))
					return false;
			}
		}
		return true;
	}
}
