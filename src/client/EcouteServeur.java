package client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;

import mastermind.Joueur;
import mastermind.Paquet;
import mastermind.Pions;


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
		}else if( p.getType() == Paquet.TU_ES_KICK ){
			this.client.getFenetre().tuEsKick();
		}else if( p.getType() == Paquet.NOUV_JOUEUR2 ){
			this.client.getFenetre().joueur2Arrive( (Joueur) p.getObjet(0) );
		}else if( p.getType() == Paquet.JOUEUR2_PARTI ){
			this.client.getFenetre().joueur2Pars();
		}else if( p.getType() == Paquet.JOUEUR1_PARTI ){
			this.client.getFenetre().joueur1Pars();
		}else if( p.getType() == Paquet.PARTIE_LANCER ){
			this.client.getFenetre().partieLancer();
		}else if( p.getType() == Paquet.CHOISIT_COMB_A_DEVINER ){
			this.client.getFenetre().choisitCombADeviner();
		}else if( p.getType() == Paquet.CHOISITPAS_COMB_A_DEVINER ){
			this.client.getFenetre().choisitPasCombADeviner();
		}else if( p.getType() == Paquet.COMPTEUR1_RATE ){
			this.client.getFenetre().compteur1Rate();
		}else if( p.getType() == Paquet.COMPTEUR1_RATE_ADVER ){
			this.client.getFenetre().compteur1RateAdv();
		}else if( p.getType() == Paquet.PERDU_COUP_CMPT2 ){
			this.client.getFenetre().perduCoupsCmpt2();
		}else if( p.getType() == Paquet.ADV_PERDU_COUP_CMPT2 ){
			this.client.getFenetre().advPerduCoupsCmpt2();
		}else if( p.getType() == Paquet.PERDU_CMPT2 ){
			this.client.getFenetre().perduCmpt2();
		}else if( p.getType() == Paquet.ADV_PERDU_CMPT2 ){
			this.client.getFenetre().advPerduCmpt2();
		}else if( p.getType() == Paquet.COMB_FIXE ){
			this.client.getFenetre().combFixe((Pions)p.getObjet(0));
		}else if( p.getType() == Paquet.CHOISI_ESSAI ){
			this.client.getFenetre().choisitEssai();
		}else if( p.getType() == Paquet.ENVOI_ESSAI_ADV ){
			this.client.getFenetre().envoiEssaiAdv( (Pions) p.getObjet(0) );
		}else if( p.getType() == Paquet.TU_AS_GAGNE ){
			this.client.getFenetre().tuAsGagne();
		}else if( p.getType() == Paquet.TU_AS_PERDU ){
			this.client.getFenetre().tuAsPerdu();
		}
		else{
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

