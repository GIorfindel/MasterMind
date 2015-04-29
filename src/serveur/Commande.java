package serveur;

import java.util.Scanner;

public class Commande extends Thread {
	private Serveur serveur;
	private Scanner scan;
	private boolean continuer;
	
	public Commande( Serveur serveur ){
		this.serveur = serveur;
		this.scan = null;
		this.continuer = false;
	}
	
	public void run(){
		this.scan = new Scanner(System.in);
		String com;
		this.continuer = true;
		while( this.continuer ){
			com = this.scan.nextLine();
			if( com.equals( "stop" ) ){
				this.serveur.close();
				this.scan.close();
				this.continuer = false;
			}
		}
		System.out.println( "Arret de la commande" );
	}
	
	public void close(){
		this.scan.close();
		this.continuer = false;
	}
}
