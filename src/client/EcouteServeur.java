package client;

import java.io.IOException;
import java.io.ObjectInputStream;

public class EcouteServeur extends Thread {
	private ObjectInputStream sInput;
	private Paquet reponseServeur;
	
	//En complement avec attent du serveur
	private boolean une_reponse;
	
	public EcouteServeur( ObjectInputStream sInput ){
		this.reponseServeur = null;
		this.sInput = sInput;
		this.une_reponse = false;
	}
	
	public void run() {
		while(true) {
			try {
				this.reponseServeur = (Paquet) sInput.readObject();
				this.une_reponse = true;
			}
			catch(IOException e) {//Faire Close_EcouteSErveur()
				this.close();
				e.printStackTrace();
				return;
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(){
		this.stop();
		try{
			this.reponseServeur = null;
			this.sInput.close();
			this.une_reponse = false;
		}catch( Exception e ){
			//Impossible de fermer le flux, c'est genant !!!
			e.printStackTrace();
		}
	}
	
	public Paquet getReponseServeur(){
		this.une_reponse = false;
		return this.reponseServeur;
	}
	
	public boolean getUneReponse(){
		return this.une_reponse;
	}
	
}
