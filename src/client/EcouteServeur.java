package client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;

public class EcouteServeur extends Thread {
	private ObjectInputStream sInput;
	private Paquet reponseServeur;
	
	public EcouteServeur( ObjectInputStream sInput ){
		this.reponseServeur = null;
		this.sInput = sInput;
	}
	
	public void interrupt() {
        super.interrupt();
        try {
            this.sInput.close(); // Fermeture du flux si l'interruption n'a pas fonctionné.
        } catch (IOException e) {
        	e.printStackTrace();
        }
    } 
	
	public void run() {
		Paquet p;
		while(true) {
			try {
				this.reponseServeur = new Paquet( (Paquet) sInput.readObject() );
			}catch (InterruptedIOException e) { // Si l'interruption a été gérée correctement.
	            Thread.currentThread().interrupt();
	            e.printStackTrace();
	            return;
	        }catch(IOException e) {//Socket Fermé
				return;
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	public void close(){
		this.interrupt();
		this.reponseServeur = null;
	}
	
	//Ontenir le paquet, si y a pas de paquet return null
	public Paquet getReponseServeur(){
		if( this.reponseServeur != null ){
			Paquet p = new Paquet( this.reponseServeur );
			this.reponseServeur = null;
			return p;
		}
		return null;
	}
	
}
