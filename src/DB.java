import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mastermind.Couleur;
import mastermind.Joueur;
import mastermind.Niveau;
import mastermind.Partie;
import mastermind.Pions;
import mastermind.Solo;
import mastermind.Tour;


/*
 * Contrainte: -Dans la table(de la bdd) Couleur, il faut que chaque couleur a le meme nom
 * que dans la classe enum de couleur.
 * 				-Le couple nom(de la partie) et id_joueur doivent etre unqiue, dans la table Partie
 * 				-Le nom de la classe des niveau(Facile, Normal etc) doit être le même que les nom des niveau dans la BDD
 */

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
				Class.forName("com.mysql.jdbc.Driver");
				connexion = DriverManager.getConnection("jdbc:mysql://"
                    +host+
                    ":3306/"+db_name+""
                    + "",""+id+"",""+mdp+"");
			}
			catch ( SQLException e ) {
				throw new RuntimeException("Cannot connect the database!", e);
			}catch ( ClassNotFoundException e ) {
				throw new RuntimeException("Cannot connect the database!, classe non trouve", e);
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
		
		//Renvoie la clé primaire généré lors d'un insert
		private int getCleGenerer( PreparedStatement p ) throws SQLException{
			ResultSet res = p.getGeneratedKeys();
			if ( res.next() ) {
				res.close();
				return res.getInt(1);
			 }else{
				 res.close();
				 throw new SQLException("Imposible d'obtenir la cle primaire");
			 }
		}
		
		private int saveEssais( ArrayList<Pions> essais ) throws SQLException{
			String query = " insert into Essais values (null)";
			PreparedStatement preparedStmt = this.connexion.prepareStatement( query, Statement.RETURN_GENERATED_KEYS );
			preparedStmt.executeUpdate();
			int id_essais = getCleGenerer( preparedStmt );
			preparedStmt.close();
			
			for( int essai = 0; essai < essais.size(); essai++ ){
				query = " insert into Essai(id_essais, id_combinaison, place)"
				        + " values (?, ?, ?)";
				this.connexion.prepareStatement( query );
				preparedStmt.setInt ( 1, id_essais );
				preparedStmt.setInt ( 2, this.saveCombinaison( essais.get( essai ) ) );
				preparedStmt.setInt ( 3, essai );
				preparedStmt.close();
			}
			
			return id_essais;
		}
		
		//Sauvegarde la combinaison et renvoi l'id de la BDD de cette combinaison
		private int saveCombinaison( Pions pions ) throws SQLException{
			String query = " insert into Combinaison values (null)";
			PreparedStatement preparedStmt = this.connexion.prepareStatement( query, Statement.RETURN_GENERATED_KEYS );
			preparedStmt.executeUpdate();
			int id_comb = getCleGenerer( preparedStmt );
			preparedStmt.close();
			
			for( int pion = 0; pion < pions.getNbPion(); pion++ ){
				query = " insert into Pions(id_combinaison, id_couleur, place)"
				        + " values (?, ?, ?)";
				this.connexion.prepareStatement( query );
				preparedStmt.setInt ( 1, id_comb );
				preparedStmt.setString ( 2, pions.getPion( pion ).toString() );
				preparedStmt.setInt ( 3, pion );
				preparedStmt.close();
			}
			
			return id_comb;
		}
		
		//Sauvegarde le tour et renvoi l'id de la BDD du tour
		private int saveTour( Tour t ) throws SQLException{
			String query = " insert into Tour(coups, combinaison, essais)"
			        + " values (?, ?, ?)";
			PreparedStatement preparedStmt = this.connexion.prepareStatement( query, Statement.RETURN_GENERATED_KEYS );
			preparedStmt.setInt ( 1, t.getCoups() );
			preparedStmt.setInt ( 2, this.saveCombinaison( t.getComb() ) );
			preparedStmt.setInt ( 3, this.saveEssais( t.getEssais() ) );
			int id_tour = getCleGenerer( preparedStmt );
			preparedStmt.close();
			return id_tour;
		}
		
		//Permet de sauvegarder une partie 
		public void sauvegarderSolo( Solo solo ) throws SQLException{
			String query = " insert into Partie(id_Joueur, nom, niveau)"
					+ " values (?, ?, ?)";
			PreparedStatement preparedStmt = this.connexion.prepareStatement( query, Statement.RETURN_GENERATED_KEYS );
			preparedStmt.setInt ( 1, this.getIdJoueur( solo.getJoueur() ) );
			preparedStmt.setString ( 2, solo.getNom() );
			preparedStmt.setString ( 3, solo.getNiveau().toString() );
			preparedStmt.executeUpdate();
			int id_partie = getCleGenerer( preparedStmt );
			preparedStmt.close();
			  
			query = " insert into Solo(id_Partie, coups, nbTours, tour)"
					+ " values (?, ?, ?, ?)";
			preparedStmt = this.connexion.prepareStatement( query );
			preparedStmt.setInt ( 1, id_partie );
			preparedStmt.setInt ( 2, solo.getCoups() );
			preparedStmt.setInt ( 3, solo.getNbTour() );
			preparedStmt.setInt ( 4, this.saveTour( solo.getTour() ) );
			preparedStmt.executeUpdate();
			preparedStmt.close();
		}
		
		private Pions chargeCombinaison( int id_combinaison, int nb_pions ) throws SQLException{
			Pions pions = new Pions( nb_pions );
			String query = " select id_couleur, place from Pions" +
					" where id_combinaison = ?";
			PreparedStatement preparedStmt = this.connexion.prepareStatement( query );
			preparedStmt.setInt(1, id_combinaison );
			ResultSet resultSet = preparedStmt.executeQuery();
			String couleur;
			int place;
			while (resultSet.next()) {
				couleur = resultSet.getString( "id_couleur" );
				place = resultSet.getInt( "place" );
				pions.addPion( Couleur.valueOf( couleur ) , place);
			}
			resultSet.close();
			preparedStmt.close();
			return pions;
		}
		
		private ArrayList<Pions> chargeEssais( int id_essai, int nb_pions ) throws SQLException{
			ArrayList<Pions> essais = new ArrayList<Pions>();
			String query = " select id_combinaison, place from Essai" +
					" where id_essais = ?";
			PreparedStatement preparedStmt = this.connexion.prepareStatement( query );
			preparedStmt.setInt(1, id_essai );
			ResultSet resultSet = preparedStmt.executeQuery();
			int id_combinaison;
			int place;
			while (resultSet.next()) {
				id_combinaison = resultSet.getInt( "id_combinaison" );
				place = resultSet.getInt( "place" );
				essais.add( place, this.chargeCombinaison(id_combinaison, nb_pions));
			}
			resultSet.close();
			preparedStmt.close();
			return essais;
		}
		
		private Tour chargerTour( int id_tour, int nb_pions ) throws SQLException, Exception{
			Tour t = new Tour( nb_pions );
			String query = " select coups, combinaison, essais from Tour" +
					" where id = ?";
			PreparedStatement preparedStmt = this.connexion.prepareStatement( query );
			preparedStmt.setInt(1, id_tour );
			ResultSet resultSet = preparedStmt.executeQuery();
			int coups;
			int id_combinaison;
			int id_essai;
			if( resultSet.next() ){
				coups = resultSet.getInt( "coups" );
				id_combinaison = resultSet.getInt( "combinaison" );
				id_essai = resultSet.getInt( "essais" );
				resultSet.close();
				preparedStmt.close();
			}else{
				resultSet.close();
				preparedStmt.close();
				throw new Exception( "Tour non trouve" );
			}
			t.setCoups( coups );
			t.setComb( this.chargeCombinaison( id_combinaison, nb_pions ) );
			t.setEssais( this.chargeEssais( id_essai, nb_pions ) );
			t.ajouteAides();
			return t;
		}
		
		public Solo chargerSolo( String nom_partie, Joueur joueur ) throws SQLException, Exception{
			String query = " select id_Partie, id_Joueur, nom, niveau from Partie" +
					" where nom = ? AND id_jouoeur = ?";
			PreparedStatement preparedStmt = this.connexion.prepareStatement( query );
			preparedStmt.setString( 1, nom_partie );
			preparedStmt.setInt(2, this.getIdJoueur( joueur ) );
			ResultSet resultSet = preparedStmt.executeQuery();
			int id_partie;
			String niveau;
			if( resultSet.next() ){
				id_partie = resultSet.getInt( "id_Partie" );
				niveau = resultSet.getString( "niveau" );
				resultSet.close();
				preparedStmt.close();
			}else{
				resultSet.close();
				preparedStmt.close();
				throw new Exception( "Partie non trouve" );
			}
			Solo solo = new Solo( nom_partie );
			solo.setNiveau( Niveau.niveauString( niveau ) );
			solo.setJoueur( joueur );
			
			query = " select coups, nbTours, tour from Solo" +
					" where id_Partie = ?";
			preparedStmt = this.connexion.prepareStatement( query );
			preparedStmt.setInt(1, id_partie );
			resultSet = preparedStmt.executeQuery();
			int coups;
			int nbTours;
			int id_tour;
			if( resultSet.next() ){
				coups = resultSet.getInt( "coups" );
				nbTours = resultSet.getInt( "nbTours" );
				id_tour = resultSet.getInt( "tour" );
				resultSet.close();
				preparedStmt.close();
			}else{
				resultSet.close();
				preparedStmt.close();
				throw new Exception( "Solo non trouve" );
			}
			solo.setCoups(coups);
			solo.setNbTour(nbTours);
			solo.setTour( this.chargerTour(id_tour, solo.getNiveau().getPions()) );
			
			
			return solo;
		}
		
	}
