package mastermind;

import java.io.Serializable;

public class Partie implements Serializable{
	private static final long serialVersionUID = 4274082376020017127L;
	/* Variables */
	protected String nom; //Nom de la partie 
	protected Niveau niveau;
	protected Joueur joueur;
	
	/* Constructeur selon les paramètres */
	public Partie( String nom, Niveau niveau, Joueur joueur ) {
		this.nom = nom;
		this.niveau = niveau;
		this.joueur = joueur;
	}
	
	
	
	/* Méthodes */
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom( String nom ) {
		this.nom = nom;
	}
	
	public Niveau getNiveau() {
		return this.niveau;
	}
	
	public void setNiveau( Niveau niveau ) {
		this.niveau = niveau;
	}
	
	public Joueur getJoueur() {
		return this.joueur;
	}
	
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
		
}