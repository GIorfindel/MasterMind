package mastermind;

import java.io.Serializable;
import java.util.ArrayList;

public class Tour  implements Serializable{
	private static final long serialVersionUID = -5410884151276152464L;

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
	
	public void addAide( Pions pions ){
		this.aides.add(pions);
	}
	
	public void addEssai( Pions pions ){
		this.essais.add(pions);
	}
	
	public Pions getEssai( int i ){
		return this.essais.get( i );
	}
	
	public void nouvelleCombinaison(){
		this.combinaison = new Pions( this.combinaison.getNbPion() );
	}
	
	public void addPionDansCombinaison( Couleur Pion ){
		this.combinaison.addPion( Pion );
	}
	
	public int getCoups(){
		return this.coups;
	}
	
	public ArrayList<Pions> getEssais(){
		return this.essais;
	}
	
	public void setEssais( ArrayList<Pions> essais ){
		this.essais = essais;
	}
	
	public void setCoups( int coups ){
		this.coups = coups;
	}
	
	public Pions getComb(){
		return this.combinaison;
	}
	
	public void setComb(Pions comb){
		this.combinaison = comb;
	}
	
	public void valideAide( Pions comb ){
		Pions aide = new Pions( this.combinaison.getNbPion() );
		for ( int i = 0; i < comb.getNbPion(); i++ ){
			if( comb.getPion( i ) == combinaison.getPion( i ) ){
				aide.addPion( Couleur.Noir );
			}else{
				for ( int j = 0; j < this.combinaison.getNbPion(); j++ ){
					if ( comb.getPion( i ) == combinaison.getPion( j ) ){
						aide.addPion( Couleur.Blanc );
						break;
					}
				}
			}
		}
		this.addAide( aide );
	}
	
	//Utile pour la partie multijoueur
	public static Pions getAide( Pions comb, Pions combTrouve ){
		Pions aide = new Pions( combTrouve.getNbPion() );
		for ( int i = 0; i < comb.getNbPion(); i++ ){
			if( comb.getPion( i ) == combTrouve.getPion( i ) ){
				aide.addPion( Couleur.Noir );
			}else{
				for ( int j = 0; j < combTrouve.getNbPion(); j++ ){
					if ( comb.getPion( i ) == combTrouve.getPion( j ) ){
						aide.addPion( Couleur.Blanc );
						break;
					}
				}
			}
		}
		return aide;
	}
	
	//Vérifie si le joueur à trouvé la combinaison et ajoute des indications dans la variable aides
	public boolean testCombinaison(Pions comb){
		this.addEssai(comb);
		this.coups ++;
		this.valideAide( comb );
		return this.combinaison.equals(comb);
	}
	
	
	//Pour chaque essai il ajoute l'aide, utile pour chargerSolo.
	public void ajouteAides(){
		for( int comb = 0; comb < this.essais.size(); comb++ ){
			this.valideAide( this.essais.get( comb ) );
		}
	}
	
	public static int comptePionsCouleur( Pions pions, Couleur couleur ){
		int compt = 0;
		for( int i= 0; i < pions.getNbPion(); i++ ){
			if( pions.getPion( i ) == couleur ){
				compt ++;
			}
		}
		return compt;
	}
	
	//Pions blanc: Bonne couleur mais mal placé
	public int getNombreBonneCouleur( int coups ){
		return comptePionsCouleur( this.aides.get( coups ), Couleur.Blanc );
	}
	
	//Pions noir: Bonne couleur et bien placé
	public int getNombreBonnePosition( int coups ){
		return comptePionsCouleur( this.aides.get( coups ), Couleur.Noir );
	}
	
	public void nouveauTour( Pions combinaison ){
		this.coups = 0;
		this.combinaison = combinaison;
		this.essais = new ArrayList<Pions>();
		this.aides = new ArrayList<Pions>();
	}
}
