package mastermind;

import java.io.Serializable;


//Un paquet permet d'envoyer des paramètres sur le réseau

public class Paquet implements Serializable{
	private static final long serialVersionUID = 673008779401442195L;
	private Object[] objets;
	private int nbObjet;
	public static final int SERVEUR_ETEINT = 0 ,
			JEMEDECO = 1,
			DEMANDE_CONNECTION = 2 , REPONSE_CONNECTION = 3,
			MODIFI_AVATAR = 4, DEMANDE_MAJ_JOUEUR = 5, REPONSE_MAJ_JOUEUR = 6,
			DECONNEXION_COMPTE = 7,
			DEMANDE_INSCRIPTION = 8, REPONSE_INSCRIPTION = 9,
			DEMANDE_SAVE_SOLO = 10, REPONSE_SAVE_SOLO = 11,
			DEMANDE_CHARGER_SOLO = 12, REPONSE_CHARGER_SOLO = 13, DEMANDE_NOMS_CHARGER_SOLO = 14, REPONSE_NOMS_CHARGER_SOLO = 15,
			DEMANDE_SUPP_SOLO = 16;
	private int type;
	
	/* Permet d'identifier un paquet parmi d'autre, si il est égale à -1, on le prend pas en compte
	 * C'est pour l'envoi de paquet et l'attente d'une réponse de ce paquet et pas une réponse d'un autre paquet
	 * Il faut que le paquet que l'on envoi qui a un id est le même id que le paquet qu'on recoi
	 */
	private int id;
	private static int ID_ACTUEL = 0;
	private final static int MAX_ID = 10;
	
	public Paquet( int nbObjet, int type, int id ){
		this.objets = new Object[nbObjet];
		this.nbObjet = 0;
		this.type = type;
		this.id = id;
	}
	
	private void addObjet( Object objet ){
		this.objets[this.nbObjet] = objet;
		this.nbObjet ++;
	}
	
	public int getNbObjet(){
		return this.nbObjet;
	}
	
	public Object getObjet( int indice ){
		return this.objets[indice];
	}
	
	public int getType(){
		return this.type;
	}
	
	public int getId(){
		return this.id;
	}
	
	public static int creerId(){
		if( ID_ACTUEL + 1 > MAX_ID ){
			return 0;
		}
		ID_ACTUEL += 1;
		return ID_ACTUEL;
	}
	
	public static Paquet creeSERVEUR_ETEINT(){
		return new Paquet( 0, SERVEUR_ETEINT, -1 );
	}
	
	public static Paquet creeJEMEDECO(){
		return new Paquet( 0, JEMEDECO, -1 );
	}
	
	public static Paquet creeDEMANDE_CONNECTION( String login, String mdp ){
		Paquet p = new Paquet( 2, DEMANDE_CONNECTION, creerId() );
		p.addObjet( login );
		p.addObjet( mdp );
		return p;
	}
	
	public static Paquet creeREPONSE_CONNECTION( Joueur j, int id ){
		if( j == null ){
			return new Paquet( 0, REPONSE_CONNECTION, id );
		}
		Paquet p = new Paquet( 1, REPONSE_CONNECTION, id );
		p.addObjet( j );
		return p;
	}
	
	public static Paquet creeMODIFI_AVATAR( String avatar ){
		Paquet p = new Paquet( 1, MODIFI_AVATAR, -1 );
		p.addObjet( avatar );
		return p;
	}
	
	public static Paquet creeDEMANDE_MAJ_JOUEUR(){
		return new Paquet( 0, DEMANDE_MAJ_JOUEUR, creerId() );
	}
	
	public static Paquet creeREPONSE_MAJ_JOUEUR( Joueur j, int id ){
		if( j == null ){
			return new Paquet( 0, REPONSE_MAJ_JOUEUR, id );
		}
		Paquet p = new Paquet( 1, REPONSE_MAJ_JOUEUR, id );
		p.addObjet( j );
		return p;
	}
	
	public static Paquet creeDECONNEXION_COMPTE(){
		return new Paquet( 0, DECONNEXION_COMPTE, -1 );
	}
	
	public static Paquet creeDEMANDE_INSCRIPTION( String login, String mdp ){
		Paquet p = new Paquet( 2, DEMANDE_INSCRIPTION, creerId() );
		p.addObjet( login );
		p.addObjet( mdp );
		return p;
	}
	
	public static Paquet creeREPONSE_INSCRIPTION( Joueur j, int id ){
		if( j == null ){//login déjà utilisé
			return new Paquet( 0, REPONSE_INSCRIPTION, id );
		}
		Paquet p = new Paquet( 1, REPONSE_INSCRIPTION, id );
		p.addObjet( j );
		return p;
	}
	
	public static Paquet creeDEMANDE_SAVE_SOLO( Solo solo ){
		Paquet p = new Paquet( 1, DEMANDE_SAVE_SOLO, creerId() );
		p.addObjet( solo );
		return p;
	}
	
	public static Paquet creeREPONSE_SAVE_SOLO( boolean reussi, int id ){
		if( reussi ){
			return new Paquet( 0, REPONSE_SAVE_SOLO, id);
		}
		Paquet p =new Paquet( 1, REPONSE_SAVE_SOLO, id);// on met rien dans les objets,c'est juste un code pour dire qu'il y a déjà le meme nom de partie dans la BDD
		p.addObjet("");
		return p;
	}
	
	public static Paquet creeDEMANDE_NOMS_CHARGER_SOLO(){
		return new Paquet( 0, DEMANDE_NOMS_CHARGER_SOLO, creerId() );
	}
	
	public static Paquet creeREPONSE_NOMS_CHARGER_SOLO( int id, String[] noms ){
		if( noms == null ){
			return new Paquet( 0, REPONSE_NOMS_CHARGER_SOLO, id );
		}else{
			Paquet p = new Paquet( 1, REPONSE_NOMS_CHARGER_SOLO, id );
			p.addObjet( noms );
			return p;
		}
	}
	
	public static Paquet creeDEMANDE_CHARGER_SOLO( String nom_parite ){
		Paquet p = new Paquet( 1, DEMANDE_CHARGER_SOLO, creerId() );
		p.addObjet(nom_parite);
		return p;
	}
	
	public static Paquet creeREPONSE_CHARGER_SOLO( Solo solo, int id ){
		if( solo == null ){
			return new Paquet( 0, REPONSE_CHARGER_SOLO, id );
		}
		Paquet p = new Paquet( 1, REPONSE_CHARGER_SOLO, id );
		p.addObjet( solo );
		return p;
	}
	
	public static Paquet creeDEMANDE_SUPP_SOLO( String nom, Joueur j ){
		Paquet p = new Paquet( 2, DEMANDE_SUPP_SOLO, -1 );
		p.addObjet( nom );
		p.addObjet( j );
		return p;
	}
}
