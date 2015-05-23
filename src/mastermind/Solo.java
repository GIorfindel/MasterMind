package mastermind;

import java.io.Serializable;

public class Solo extends Partie implements Serializable{
	private static final long serialVersionUID = 457371753046172924L;
	/* Variables */
	private Integer coups; //le nombre de coup que le joueur a fait
	private int nbTour;
	private Tour tour; //le tour que l’on fait
	
public Solo( String nom, Niveau niveau, Joueur joueur ){
	super( nom, niveau, joueur );
	this.coups = 0;
	this.nbTour = 0;
	this.tour = new Tour( this.niveau.getPions() );
}
	
public void setCoups( int coups ) {
	this.coups = coups;
	this.nbTour = 0;
	}
	
public int getCoups() {
	return this.coups;
	}
	
public void setTour( Tour tour ) {
	this.tour = tour;
	}
	
public Tour getTour() {
	return this.tour;
	}

public int getNbTour(){
	return this.nbTour;
}

public void setNbTour( int nbTour ){
	this.nbTour = nbTour;
}

public void addNbTour(){
	this.nbTour ++;
}

public void addCoups(){
	this.coups ++;
}


public void nouveauTour( Couleur[] couleurPossible ){
	this.coups += this.tour.getCoups();
	this.nbTour ++;
	this.tour.nouveauTour( this.niveau.genererCombinaisonAle( couleurPossible ) );
}

public void reset(){
	this.coups = 0;
	this.nbTour = 0;
}
}
