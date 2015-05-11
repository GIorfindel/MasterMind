package client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;

import mastermind.Paquet;


public class EcouteServeur extends Thread {
	private ObjectInputStream recoi;
	//Le paquet que nous a envoyé le serveur
	private Paquet paquet;
	private Client client;
	private boolean continuer;
	
	public EcouteServeur( ObjectInputStream recoi , Client client ){
		this.paquet = null;
		this.recoi = recoi;
		this.client = client;
		this.continuer = false;
	}
	
	public void run() {
		Paquet memo;
		this.continuer = true;
		while( this.continuer ) {
			try {
				if( this.recoi != null ){
					memo = (Paquet) this.recoi.readObject();
					this.gererPaquet( memo );
				}
				
			}catch( EOFException e ){//Le serveur s'arrete brutalement
				this.client.closeServeur();
			}catch(IOException e) {
				if( this.continuer ){ //  sinon c'est qu'on arrete ce thread
		        	e.printStackTrace();
				}
				this.client.closeServeur();
				return;
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
				this.client.closeServeur();
				return;
			}
		}
	}
	
	//Permet de ferme l'écoute du serveur
	public void close(){
		this.continuer = false;
		try{
			if( this.recoi != null ){
				this.recoi.close();
				this.recoi = null;
			}
		}catch( IOException e ){
			e.printStackTrace();
		}
		this.paquet = null;
	}
	
	//Selon le type de paquet, on le traite puis on le met à null, ou on le sauavegarde dans paquet
	public void gererPaquet( Paquet p ){
		if( p.getType() == Paquet.SERVEUR_ETEINT ){
			//On gere au niveau du client que le serveur est éteint
			this.client.closeServeur();
		}else{
			this.paquet = p;
		}
	}
	
	//Obtenir le paquet, si y a pas de paquet return null
	public Paquet getPaquet( int id ){
		if( this.paquet != null && this.paquet.getId() == id ){
			Paquet memo = this.paquet;
			//On met le paquet à null, pour faire de la place à un autre paquet.
			this.paquet = null;
			return memo;
		}
		this.paquet = null;
		return null;
	}
	
}

