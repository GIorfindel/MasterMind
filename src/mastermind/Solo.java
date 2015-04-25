package mastermind;

public class Solo extends Partie{
	
	/* Variables */
	private integer coups; //le nombre de coup que le joueur a fait
	private Tour tour; //le tour que l’on fait
	
public void setCoups( int coups ) {
	this.coups = coups;
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

}
