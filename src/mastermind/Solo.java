package mastermind;

public class Solo extends Partie{
	
	/* Variables */
	private Integer coups; //le nombre de coup que le joueur a fait
	private int nbTour;
	private Tour tour; //le tour que l’on fait
	
public Solo(){
	this.coups = 0;
	this.nbTour = 0;
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

public void setNbTour(){
	this.nbTour ++;
}

}
