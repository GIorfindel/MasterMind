package mastermind;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import client.Client;


//Un paquet permet d'envoyer des paramètres sur le réseau

/*
 * Un paquet a:
 * 		-des objets : ce que l'on veut mettre dedans
 * 		-Un type : c'est la raison du paquet (ex: demande de connexion, inscription)
 * 		-Un id : C'est pour le différencier d'un autre paquet
 * 
 * Pour créer un paquet, envoyer et attendre une réponse du serveur : 
 * 	Paquet p = Paquet.creeDEMANDE_......( parametres )
 * 						  REPONSE_.......( parametres )
 *  int id = p.getID()
 * 	if( client.envoyerPaquet( p, id ) == true ) paquet envoyé
 *  Paquet pServeur = client.recevoirPaquet( X seconde , id )
 *  if( pServeur == null ) Limite de temps écoulé, donc paquet pas recu
 *  
 *  Si tous c'est bien fait, vous avez votre réponse (pServeur.getObjet( indice ))
 */

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
			DEMANDE_SAVE_SOLO = 10,
			DEMANDE_CHARGER_SOLO = 11, REPONSE_CHARGER_SOLO = 12,
			DEMANDE_NOUV_SCORE = 13,
			DEMANDE_STATS = 27, REPONSE_STATS = 28,
			DEMANDE_CREE_MULTI = 14, DEMANDE_NOUV_JOUEUR2 = 15, REPONSE_NOUV_JOUEUR2 = 16, DEMANDE_JOUER_MULTI = 17, DEMANDE_KICKER_JOUEUR2 = 18,
			TU_ES_KICK = 19, NOUV_JOUEUR2 = 20, DEMANDE_JOUEUR2_PARTI= 21, JOUEUR2_PARTI = 22, DEMANDE_JOUEUR1_PARTI = 23, JOUEUR1_PARTI = 24,
			DEMANDE_LISTE_PARTIES = 25, REPONSE_LISTE_PARTIES = 26;
	private int type;
	
	/* Permet d'identifier un paquet parmi d'autre, si il est égale à -1, on le prend pas en compte
	 * C'est pour l'envoi de paquet et l'attente d'une réponse de ce paquet et pas une réponse d'un autre paquet
	 * Il faut que le paquet que l'on envoi qui a un id est le même id que le paquet qu'on recoi
	 */
	private int id;
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
	
	private static int creerId(){
		if( Client.ID_ACTUEL + 1 > MAX_ID ){
			Client.ID_ACTUEL = 0;
			return 0;
		}
		Client.ID_ACTUEL += 1;
		return Client.ID_ACTUEL;
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
	
	public static Paquet creeMODIFI_AVATAR( ImageIcon img ){
		Paquet p = new Paquet( 1, MODIFI_AVATAR, -1 );
		p.addObjet( img );
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
		Paquet p = new Paquet( 8, DEMANDE_SAVE_SOLO, creerId() );
		p.addObjet( solo.getJoueur() );
		p.addObjet( solo.getNom() );
		p.addObjet( solo.getNiveau() );
		p.addObjet( new Integer(solo.getCoups()) );
		p.addObjet( new Integer(solo.getNbTour()) );
		p.addObjet( new Integer(solo.getTour().getCoups()) );
		p.addObjet( solo.getTour().getComb() );
		Pions[] essais = new Pions[ solo.getTour().getEssais().size() ];
		for( int i=0;i<solo.getTour().getEssais().size();i++ ){
			essais[i] = solo.getTour().getEssai(i);
		}
		p.addObjet( essais );
		return p;
	}
	
	public static Solo getSolo( Paquet p ){
		Joueur j = (Joueur) p.getObjet(0);
		String nom_partie = (String) p.getObjet(1);
		Niveau n = (Niveau) p.getObjet(2);
		int coups_totau = ((Integer) p.getObjet(3)).intValue();
		int nbTour = ((Integer) p.getObjet(4)).intValue();
		int coups = ((Integer) p.getObjet(5)).intValue();
		Pions comb = (Pions) p.getObjet(6);
		Pions[] essais = (Pions[]) p.getObjet(7);
		
		Solo s = new Solo( nom_partie, n, j );
		s.setCoups(coups_totau);
		s.setNbTour(nbTour);
		Tour t = new Tour(n.getPions());
		t.setCoups(coups);
		t.setComb(comb);
		
		for( int i=0;i<essais.length;i++ ){
			t.addEssai(essais[i]);
		}
		s.setTour(t);
		return s;
	}
	
	public static Paquet creeDEMANDE_CHARGER_SOLO(){
		Paquet p = new Paquet( 0, DEMANDE_CHARGER_SOLO, creerId() );
		return p;
	}
	
	public static Paquet creeREPONSE_CHARGER_SOLO( Solo solo, int id ){
		if( solo == null ){
			return new Paquet( 0, REPONSE_CHARGER_SOLO, id );
		}
		Paquet p = new Paquet( 8, REPONSE_CHARGER_SOLO, id );
		p.addObjet( solo.getJoueur() );
		p.addObjet( solo.getNom() );
		p.addObjet( solo.getNiveau() );
		p.addObjet( new Integer(solo.getCoups()) );
		p.addObjet( new Integer(solo.getNbTour()) );
		p.addObjet( new Integer(solo.getTour().getCoups()) );
		p.addObjet( solo.getTour().getComb() );
		Pions[] essais = new Pions[ solo.getTour().getEssais().size() ];
		for( int i=0;i<solo.getTour().getEssais().size();i++ ){
			essais[i] = solo.getTour().getEssai(i);
		}
		p.addObjet( essais );
		return p;
	}
	
	public static Paquet creeDEMANDE_NOUV_SCORE( Score score ){
		Paquet p = new Paquet( 1,DEMANDE_NOUV_SCORE, -1);
		p.addObjet(score);
		return p;
	}
	
	public static Paquet creeDEMANDE_STATS( String identifiant ){
		Paquet p = new Paquet( 1,DEMANDE_STATS, creerId());
		p.addObjet(identifiant);
		return p;
	}
	
	public static Paquet creeREPONSE_STATS( float[] stats, int id ){
		if( stats == null ){
			return new Paquet( 0, REPONSE_STATS, id );
		}
		Paquet p = new Paquet( 8, REPONSE_STATS, id );
		for (int i = 0; i<8; i++)
		{
			p.addObjet( stats[i] );
		}
		return p;
	}
	
	public static Paquet creeDEMANDE_CREE_MULTI( Niveau niveau ){
		Paquet p = new Paquet( 1, DEMANDE_CREE_MULTI, -1 );
		p.addObjet(niveau);
		return p;
	}
	
	//Pour demander la liste des parties qui ne sont ni pleine et en attente de joueur
	public static Paquet creeDEMANDE_LISTE_PARTIES(){
		return new Paquet( 0, DEMANDE_LISTE_PARTIES, creerId());
	}
	
	
	public static Paquet creeREPONSE_LISTE_PARTIES( ArrayList<Multijoueur> parties, int id ){
		Paquet p = new Paquet( parties.size(), REPONSE_LISTE_PARTIES, id);
		for(int i =0; i<parties.size();i++){
			p.addObjet( parties.get(i) );
		}
		return p;
	}
	
	//C'est le joueur2 qui demande d'entrer dans un partie deja créais(Dans la page liste des parties)
	public static Paquet creeDEMANDE_NOUV_JOUEUR2( String nom_partie ){
		Paquet p = new Paquet( 1, DEMANDE_NOUV_JOUEUR2, creerId());
		p.addObjet(nom_partie);
		return p;
	}
	
	//retourne null s'il la partie et introuvé ou la partie est pleine (il faut rafraichir la liste des partie)
	public static Paquet creeREPONSE_NOUV_JOUEUR2( Joueur j, Niveau n, int id ){
		if( j == null ){
			return new Paquet( 0, REPONSE_NOUV_JOUEUR2, id );
		}else{
			Paquet p = new Paquet( 2, REPONSE_NOUV_JOUEUR2, id );
			p.addObjet(j);
			p.addObjet(n);
			return p;
		}
	}
	
	//C'est le serveur qui envoi au joueur1 le joueur qui vveut entrer dans ca partie
	public static Paquet creeNOUV_JOUEUR2( Joueur j ){
		Paquet p = new Paquet( 1, NOUV_JOUEUR2, -1);
		p.addObjet(j);
		return p;
	}
	
	//C'est le joueur1 qui demande de kick le joueur2. Ca n'a pas de réponse.
	public static Paquet creeDEMANDE_KICKER_JOUEUR2(){
		return new Paquet( 0, DEMANDE_KICKER_JOUEUR2, -1);
	}
	
	//C'est le serveur qui dit au joueur2 que tu es kick
	public static Paquet creeTU_ES_KICK(){
		return new Paquet( 0, TU_ES_KICK, -1);
	}
	
	//C'est le joueur2 qui quitte la partie(croix rouge, ou le bouton quitter)
	public static Paquet creeDEMANDE_JOUEUR2_PARTI(){
		return new Paquet(0, DEMANDE_JOUEUR2_PARTI, -1);
	}
	
	//C'est le serveur qui envoi au joueur1 que le joueur2 est partie
	public static Paquet creeJOUEUR2_PARTI(){
		return new Paquet(0, JOUEUR2_PARTI, -1);
	}
	
	//C'est le joueur1 qui quitte la partie(croix rouge, ou le bouton quitter)
	public static Paquet creeDEMANDE_JOUEUR1_PARTI(){
		return new Paquet( 0, DEMANDE_JOUEUR1_PARTI, -1 );
	}
	
	//C'est le serveur qui envoi au joueur2 que le joueur1 est partie(Donc la partie n'existe plus)
	public static Paquet creeJOUEUR1_PARTI(){
		return new Paquet( 0, JOUEUR1_PARTI, creerId() );
	}
	
	//C'est le joueur1 qui demande de commencer la partie(il clic sur le bouton jouer). Donc il faut que le joueur2 soit la aussi
	public static Paquet creeDEMANDE_JOUER_MULTI(){
		return new Paquet( 0, DEMANDE_JOUER_MULTI, creerId() );
	}
}
