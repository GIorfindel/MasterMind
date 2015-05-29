package mastermind;

import java.util.Random;

public class Multijoueur extends Partie {
	private static final long serialVersionUID = 7290557457379419425L;

	private final static int COUPS_MAX = 100;
	
	private int coupsJ1;
	private int coupsJ2;
	private int compteur;
	private Pions comb;
	private Joueur joueur2;
	private boolean tourDeCreateur;
	
	private int etat;
	public static int ETAT_CHERCHE_JOUEUR2 = 0, ETAT_ATTENTE_JOUER = 1, ETAT_ETAT_CHOISIT_COMB_A_DEVINER_COMPT_1 = 2,
			ETAT_ETAT_CHOISIT_COMB_A_DEVINER_COMPT_2 = 3;
	
	public Multijoueur(String nom, Niveau niveau, Joueur joueur) {
		super(nom, niveau, joueur);
		this.coupsJ1 = 0;
		this.coupsJ2 = 0;
		this.etat = ETAT_CHERCHE_JOUEUR2;
		this.joueur2 = null;
	}
	
	public Joueur getJoueur2(){
		return this.joueur2;
	}
	
	public void setJoueur2( Joueur joueur2 ){
		this.joueur2 = joueur2;
		this.etat = ETAT_ATTENTE_JOUER;
	}
	
	public void kickJoueur2(){
		this.joueur2 = null;
		this.etat = ETAT_CHERCHE_JOUEUR2;
	}
	
	public int getEtat(){
		return this.etat;
	}
	
	public boolean getTourDeCreateur(){
		return this.tourDeCreateur;
	}
	
	public void switchTourDe(){
		this.tourDeCreateur = !this.tourDeCreateur;
	}
	
	public void commencerPartie(){
		Random rand = new Random();
		this.tourDeCreateur = rand.nextBoolean();
		this.etat = ETAT_ETAT_CHOISIT_COMB_A_DEVINER_COMPT_1;
	}
	
	public void compteur1Ecoule(){
		this.etat = ETAT_ETAT_CHOISIT_COMB_A_DEVINER_COMPT_2;
	}
	
	public void addCoupsJ1(){
		this.coupsJ1++;
	}
	
	public void addCoupsJ2(){
		this.coupsJ2++;
	}
}
