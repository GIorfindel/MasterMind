package mastermind;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Pions  implements Serializable{
	private static final long serialVersionUID = 4295593891497008282L;
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
	
	public boolean pionMemeCouleur( Couleur pion ){
		for( int i = 0; i < this.nbPions; i++ ){
			if( this.getPion( i ) == pion ){
				return true;
			}
		}
		return false;
	}
	
	public int nbCouleur( Couleur pion ){
		Pions p = new Pions(this.nbPions + 1);
		p.addPion(pion);
		Set<Couleur> unicColors = new HashSet<Couleur>();
		for (Couleur c : p.getCombinaison()) unicColors.add(c);
		int unicNB = unicColors.size();
		return unicNB - 1 ;
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
	
	public String toString(){
		String s = "Combinaison :";
		for( int pion = 0; pion < this.nbPions; pion++ ){
			s += this.combinaison[pion] + " ";
		}
		return s;
	}
}
