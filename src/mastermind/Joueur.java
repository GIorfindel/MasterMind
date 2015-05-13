package mastermind;



import java.io.Serializable;
import javax.swing.ImageIcon;

public class Joueur implements Serializable{
	private static final long serialVersionUID = -3297928743639074673L;
	private String identifiant;
	private String mdp;
	//l'image du joueur
	public static int WIDTH_AVATAR = 100, HEIGHT_AVATAR = 200;
	private ImageIcon avatar;
	//Les malus du à ses partie
	private int malus;
	
	public Joueur( String identifiant, String mdp, String avatar, int malus ){
		this.identifiant = identifiant;
		this.mdp = mdp;
		this.loadAvatar( avatar );
		this.malus = malus;
	}
	
	//Methodes get et set
	
		public String getIdentifiant(){
			return this.identifiant;
		}
		
		public void setIdentifiant( String identifiant ){
			this.identifiant = identifiant;
		}
		
		public String getMDP(){
			return this.mdp;
		}
		
		public void setMDP( String mdp ){
			this.mdp = mdp;
		}
		
		public ImageIcon getAvatar(){
			return this.avatar;
		}
		
		public void setAvatar( ImageIcon img ){
			this.avatar = img;
		}
		
		public int getMalus(){
			return this.malus;
		}
		
		public void setMalus( int malus ){
			this.malus = malus;
		}
		
		//Fin des methodes get set
		
		//C'est que le serveur qui utilise cette méthode
		public void loadAvatar( String nom ){
			if( nom != null ){
				this.avatar = new ImageIcon( "/avatar/" + nom);
			}else{
				this.avatar = null;
			}
		}

}
