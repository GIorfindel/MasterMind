package client;

import java.util.StringTokenizer;

import mastermind.Joueur;
import mastermind.Partie;

public class Client {
	
	private Serveur serveur;
	
	private Joueur joueur;
	private Partie partie;
	private InputClient inputClient;
	
	private static final int ARRET = 0, MENU = 1, CONNECTION = 2, PROFIL = 3;
	
	public Client( String addr_serveur, int port_serveur ){
		this.joueur = null;
		this.partie = null;
		this.serveur = new Serveur( addr_serveur, port_serveur );
		this.inputClient = null;
	}
	
	public void initClient(){
		this.serveur.connectionAuServeur();
		this.inputClient = new InputClient();
		this.inputClient.start();
		this.menus();
	}
	
	private String getChoixString(){//Obtient le choix en string du joueur, ATTENTION elle est BLOQUANTE
		String choix = null;
		do{
			choix = this.inputClient.getInputS();
		}while( choix !=null );
		return choix;
	}
	
	private int getChoixInt() throws NumberFormatException{
		String choix = this.getChoixString();
		return Integer.parseInt( choix );
	}
	
	private void afficheMenu(){
		String connecter = "non";
		if( this.serveur.getConnecter() ){
			connecter = "oui";
		}
		System.out.println( "Connecté au serveur : " + connecter );
		if( this.joueur != null ){
			System.out.println( "Identifiant : " + this.joueur.getIdentifiant() );
		}
		System.out.println("1	-Se connecter\n" +
							"3	-Profiln");
	}
	
	private int menu(){
		int choix = -1;
		boolean mauvaisChoix = true;
		if( !this.serveur.getConnecter() ){
			this.serveur.connectionAuServeur();
		}
		while( mauvaisChoix ){
			this.afficheMenu();
			try{
				choix = this.getChoixInt();
				if( choix == 1 ){//Se connecter
					choix = CONNECTION;
					mauvaisChoix = false;
				}else if( choix == 3 ){//Profil
					choix = PROFIL;
					mauvaisChoix = false;
				}
			}catch( NumberFormatException e ){//L'utilisateur n'a pas saisie un entier
				mauvaisChoix = true;
			}
			if( mauvaisChoix ){
				System.out.println( "Choix incorect, re-essayer" );
			}
		}
		return choix;
	}
	
	private String[] separeLesChoix( int nbElementsMax ){
		String choix = this.getChoixString();
		StringTokenizer st = new StringTokenizer( choix, " " );
		String[] entrees = new String[nbElementsMax];
		for( int x = 0; x < nbElementsMax; x++ ){
			entrees[x] = null;
		}
		int i = 0;
		while( st.hasMoreTokens() ){
			if( i == nbElementsMax ){
				return null;
			}
			entrees[i] = st.nextToken();
			i++;
		}
		return entrees;
	}
	
	private void afficheConnection(){
		System.out.println("Connection\n" + 
							"Faire: login ton_login mdp ton_mdp\n" +
							"Ou quit, pour retourner au menu" );
	}
	
	private int connection(){//Ne pas oublier le choix quit
		if( !this.serveur.getConnecter() ){
			if( !this.serveur.connectionAuServeur() ){
				System.out.println("Impossible de se connecter au serveur");
				return MENU;
			}
		}
		boolean continuer = true;
		int choixInt = -1;
		String login = null;
		String mdp = null;
		while( continuer ){
			this.afficheConnection();
			String[] choix = this.separeLesChoix( 4 );
			if( choix[0] != null && choix[0].equals( "quit" ) ){
				continuer = false;
				choixInt = MENU;
			}
			else if( choix[0] != null && choix[1] != null && choix[2] != null && choix[3] != null ){
				if( choix[0].equals("login") && choix[2].equals("mdp") && !choix[1].equals("") && !choix[3].equals("") ){
					login = choix[1];
					mdp = choix[3];
					continuer = false;
					choixInt = PROFIL;
				}
				
			}
			if( continuer ){
				System.out.println( "Choix incorect, re-essayer" );
			}
		}
		if( choixInt == MENU ){
			return MENU;
		}
		Paquet p = new Paquet( 2, Paquet.DEMANDE_CONNECTION );
		p.addObjet( new String( login ) );
		p.addObjet( new String( mdp ) );
		this.serveur.envoyerPaquet( p, true );
		Paquet pServeur = this.serveur.getAttentPaquet( 5.0 );//On attend(maximum 5 secondes) que le serveur nous repond
		if( pServeur == null ){
			System.out.println("Limite de temps depassé, veuillez essayer plus tard");
			return MENU;
		}
		if( pServeur.getType() == Paquet.REPONSE_CONNECTION ){
			if( pServeur.getNbObjet() == 0 ){
				System.out.println("Informations incorrect, veuillez re-essayer");
				return CONNECTION;
			}else{
				this.joueur = new Joueur( (Joueur) pServeur.getObjet( 0 ) );
				return PROFIL;
			}
		}else{
			System.out.println("Probleme inquietant, ce paquet ne vous est pas destiné");
			return MENU;
		}
	}
	
	private void afficheProfil(){
		System.out.println( "Identifiant :	" + this.joueur.getIdentifiant() );
		System.out.println( "Avatar :	" + this.joueur.getAvatar() );
		System.out.println( "Malus :	" + this.joueur.getMalus() );
		System.out.println("1	-Menu");
	}
	
	private int profil(){
		if( !this.serveur.getConnecter() ){
			System.out.println("vous n'êtes pas connecté au serveur");
			return MENU;
		}else if( this.joueur == null ) {
			System.out.println("Vous vous êtes pas connecté, à votre compte");
			return MENU;
		}
		boolean continuer = true;
		int choixInt = -1;
		while( continuer ){
			this.afficheProfil();
			String[] choix = this.separeLesChoix( 1 );
			if( choix[0] != null && choix[0].equals("quit") ){
				continuer = false;
				choixInt = MENU;
			}else if( choix[0] != null && choix[0].equals("1") ){
				continuer = false;
				choixInt = MENU;
			}
			if( continuer ){
				System.out.println( "Choix incorect, re-essayer" );
			}
		}
		return choixInt;
		
	}
	
	private void menus(){
		int menu = MENU;
		while( menu != ARRET ){
			switch (menu) {
			case MENU :
				menu = this.menu();
				break;
			case CONNECTION :
				menu = this.connection();
				break;
			case PROFIL :
				menu = this.profil();
				break;
			}
		}
		//Close(), envoyer un deco au serveur
	}

}
