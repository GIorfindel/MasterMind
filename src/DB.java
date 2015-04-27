import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mastermind.Couleur;
import mastermind.Joueur;
import mastermind.Pions;
import mastermind.Solo;
import mastermind.Tour;

public class DB {

		/* Connexion à la base de données */
		String host = "";
		String id = "";
		String mdp = "";
		String db_name="";
		Connection connexion = null;
		
		//Connexion à la base de données
		public void connexion(){
			try {
				connexion = DriverManager.getConnection("jdbc:mysql://"
                    +host+
                    ":3306/"+db_name+""
                    + "",""+id+"",""+mdp+"");
			}
			catch ( SQLException e ) {
				throw new RuntimeException("Cannot connect the database!", e);
			}
		}
		
		//Déconnexion de la base de données
		public void deconnexion() throws SQLException
		{
			this.connexion.close();
		}
		
		//Retourne l'id d'un joueur
		public Integer getIdJoueur(Joueur j) throws SQLException
		{
		      String query = "select id from Joueur where identifiant = ?";
		      PreparedStatement preparedStmt = connexion.prepareStatement(query);
		      preparedStmt.setString (1, j.getIdentifiant());
		      ResultSet result = preparedStmt.executeQuery();
		      return result.getInt(1);
		}
		
		//Permet de sauvegarder une partie 
		public void sauvegarderPartie(Solo solo, Joueur j) throws SQLException{
		      String query = " insert into Partie(id_Joueur, nom, niveau, coups)"
				        + " values (?, ?, ?, ?)";
		      PreparedStatement preparedStmt = connexion.prepareStatement(query);
		      preparedStmt.setInt (1, this.getIdJoueur(j));
		      preparedStmt.setString (2, solo.getNom());
		      preparedStmt.setString (3, solo.getNiveau().toString());
		      preparedStmt.setInt (4, solo.getTour().getCoups());
			  preparedStmt.executeQuery();
			  for (int i=0; i<solo.getTour().getComb().getNbPion();i++)
			  {
				  String query2 = " insert into Couleur"
				        + " values (?, ?, ?)";
				  preparedStmt.setInt (1, this.getIdPartie(j, solo));
				  preparedStmt.setInt (2, i);
				  preparedStmt.setInt (3, solo.getTour().getComb().getPion(i).ordinal());
				  preparedStmt.executeQuery();
			  }
		}
		
		//Permet de recuperer l'id d'une partie
		public Integer getIdPartie(Joueur j, Solo solo) throws SQLException
		{
		      String query = "select id_Partie from Partie where id_Joueur = ? AND nom = ?";
		      PreparedStatement preparedStmt = connexion.prepareStatement(query);
		      preparedStmt.setInt (1, getIdJoueur(j));
		      preparedStmt.setString (2, solo.getNom());
		      ResultSet result = preparedStmt.executeQuery();
		      return result.getInt(1);
		}
		
		//Permet de recuperer le niveau d'une partie
		public String getNiveau(Integer partie) throws SQLException
		{
		      String query = "select niveau from Partie where id_Partie = ?";
		      PreparedStatement preparedStmt = connexion.prepareStatement(query);
		      preparedStmt.setInt (1, partie);
		      ResultSet result = preparedStmt.executeQuery();
		      return result.getNString(1);
		}
		
		//Permet de recuperer le nombre de coups joués dans dans une partie
		public int getCoups(Integer partie) throws SQLException
		{
		      String query = "select coups from Partie where id_Partie = ?";
		      PreparedStatement preparedStmt = connexion.prepareStatement(query);
		      preparedStmt.setInt (1, partie);
		      ResultSet result = preparedStmt.executeQuery();
		      return result.getInt(1);
		}
		
		//Permet de recuperer le nombre de couleur composants la combinaison
		public int getNbCouleur(Joueur j, Solo solo) throws SQLException
		{
		    String query = "select count(id_Partie) from Couleur where id_Partie = ?";
		    PreparedStatement preparedStmt = connexion.prepareStatement(query);
		    preparedStmt.setInt (1, getIdPartie(j, solo));
		    ResultSet result = preparedStmt.executeQuery();
		    return result.getInt(1);
		}
		
		//Permet de charger de charger une partie sauvegardée
		public Solo chargerPartie(Joueur j, Solo solo) throws SQLException
		{
			Solo s = new Solo();
			Tour t = new Tour();
			t.setCoups(this.getCoups(getIdPartie(j, solo)));
		    String query = "select place, numero from Couleur where id_Partie = ? order by place";
		    PreparedStatement preparedStmt = connexion.prepareStatement(query);
		    preparedStmt.setInt (1, getIdPartie(j, solo));
		    ResultSet result = preparedStmt.executeQuery();
		    Couleur[] comb = new Couleur[this.getNbCouleur(j, solo)];
		    while (result.next()) {
		        int place = result.getInt("place");
		        int num = result.getInt("numero");
		        comb[place]=Couleur.values()[num];
		    }
		    t.setComb(new Pions(comb));
		    s.setTour(t);
		    return s;
		}
		
	}
