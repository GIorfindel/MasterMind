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
				this.close();
			}
		}
	}
	
	public void close(){
		this.serveur.close();
		if( this.scan != null ){
			this.scan.close();
			this.scan = null;
		}
		this.continuer = false;
		this.serveur.afficher( "La commande s'arrête" );
	}
}

