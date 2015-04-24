package mastermind;

public class Solo extends Partie{
	
	/* Variables */
	private Integer coups; //le nombre de coup que le joueur a fait
	private Tour tour; //le tour que l’on fait
	
public void setCoups( Integer coups ) {
	this.coups = coups;
	}
	
public Integer getCoups() {
	return this.coups;
	}
	
public void setTour( Tour tour ) {
	this.tour = tour;
	}
	
public Tour getTour() {
	return this.tour;
	}

}
