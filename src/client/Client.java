package client;

import mastermind.Joueur;
import mastermind.Partie;

public class Client {
	
	private Serveur serveur;
	
	private Joueur joueur;
	private Partie partie;
	
	public Client( String addr_serveur, int port_serveur ){
		this.joueur = null;
		this.serveur = new Serveur( addr_serveur, port_serveur );
	}

}
