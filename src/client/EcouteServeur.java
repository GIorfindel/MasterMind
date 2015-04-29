package client;

import java.io.IOException;
import java.io.InterruptedIOException;
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
				p = new Paquet( (Paquet) sInput.readObject() );
				this.reponseServeur = new Paquet( p );
				this.une_reponse = true;
			}catch (InterruptedIOException e) { // Si l'interruption a été gérée correctement.
	            Thread.currentThread().interrupt();
	            System.out.println("Interrompu via InterruptedIOException");
	            return;
	        }catch(IOException e) {
				this.close();
				e.printStackTrace();
				return;
			}catch(ClassNotFoundException e) {
				this.close();
				e.printStackTrace();
				return;
			}
		}
	}
	
	public void close(){
		this.interrupt();
		try{
			this.reponseServeur = null;
			if( this.sInput != null ){
				this.sInput.close();
			}
			this.une_reponse = false;
		}catch( Exception e ){
			//Impossible de fermer le flux, c'est genant !!!
			e.printStackTrace();
		}
	}
	
	//Ontenir le paquet, si y a pas de paquet return null
	public Paquet getReponseServeur(){
		this.une_reponse = false;
		Paquet p = new Paquet( this.reponseServeur );
		this.reponseServeur = null;
		return p;
	}
	
	public boolean getUneReponse(){
		return this.une_reponse;
	}
	
}
