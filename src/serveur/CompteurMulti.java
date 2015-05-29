package serveur;

public class CompteurMulti extends Thread{
	public final static int COMPTEUR_1 = 0;
	public final static int COMPTEUR_2 = 1;
	
	private Client client;
	private int type;
	private boolean continuer;
	
	public CompteurMulti( Client c, int typeCompteur ){
		this.client = c;
		this.type = typeCompteur;
		this.continuer = false;
	}
	
	public void run(){
		this.continuer = true;
		if( this.type == COMPTEUR_1 ){
			this.compteur1();
		}else if( this.type == COMPTEUR_2 ){
			this.compteur2();
		}
	}
	
	public void close(){
		this.continuer = false;
	}
	
	private double getTempsSeconde(){
		return System.currentTimeMillis()/1000.0;
	}
	
	private void compteur1(){
		double temps_max = this.getTempsSeconde() + 60.0;
		while( this.getTempsSeconde() < temps_max && this.continuer ){
			try {
				Thread.sleep( 200 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if( this.getTempsSeconde() > temps_max && this.continuer ){
			this.client.compteur1TempsAtteint();
			this.continuer = false;
		}
	}
	
	private void compteur2(){
		double temps1 = this.getTempsSeconde() + 10;
		int s = 60;
		while( this.continuer ){
			try {
				Thread.sleep( 200 );
				if( this.getTempsSeconde() > temps1 ){
					this.client.compteur2AjouteCoups();
					s -= 10;
					if( s == 0 ){
						this.client.compteur2TempsAtteint();
						this.continuer = false;
					}
					temps1 = this.getTempsSeconde() + 10;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
