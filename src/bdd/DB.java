package bdd;

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
	private String host = "";
	private String id = "";
	private String mdp = "";
	private String db_name="";
	private Connection connexion = null;
		
	public DB( String host, String id, String mdp, String db_name ){
		this.host = host;
		this.id = id;
		this.mdp = mdp;
		this.db_name = db_name;
		this.connexion = null;
	}
		
	//Connexion à la base de données
	public void connexion(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection( "jdbc:mysql://"+ this.host  + "/" + this.db_name + "?"
					+ "user=" + this.id + "&password=" + this.mdp );
		}
		catch ( SQLException e ) {
			throw new RuntimeException("Cannot connect the database!", e);
		}catch ( ClassNotFoundException e ) {
			throw new RuntimeException("Cannot connect the database!, classe non trouve", e);
		}
	}
		
	//Déconnexion de la base de données
	public void deconnexion() throws SQLException{
		this.connexion.close();
		this.connexion = null;
	}

	public Joueur getJoueur( String login, String mdp ) throws SQLException{
		String query = " select avatar, malus  from Joueur" +
				" where identifiant = ? AND mdp = ?";
		PreparedStatement preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setString(1, login );
		preparedStmt.setString(2, mdp );
		ResultSet resultSet = preparedStmt.executeQuery();
		if( resultSet.next() ){
			int malus = resultSet.getInt( "malus" );
			String avatar = resultSet.getString( "avatar" );
			resultSet.close();
			preparedStmt.close();
			return new Joueur( login, mdp, avatar, malus );
		}
		resultSet.close();
		preparedStmt.close();
		return null;
	}
	
	public void modifieAvatar( Joueur j ) throws SQLException{
		String query = "update Joueur set avatar = ? where identifiant = ?";
		PreparedStatement preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setString(1, j.getIdentifiant() + ".png" );
		preparedStmt.setString(2, j.getIdentifiant() );
		preparedStmt.executeUpdate();
		preparedStmt.close();
	}
	
	public Joueur inscrireJoueur( String login, String mdp ) throws SQLException{
		String query = " select identifiant from Joueur" +
				" where identifiant = ?";
		PreparedStatement preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setString(1, login );
		ResultSet resultSet = preparedStmt.executeQuery();
		if( resultSet.next() ){//Joueur existe deja
			resultSet.close();
			preparedStmt.close();
			return null;
		}
		resultSet.close();
		preparedStmt.close();
		query = " insert into Joueur(identifiant, mdp, avatar, malus)" +
				" values(?,?,null,0)";
		preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setString(1, login );
		preparedStmt.setString(2, mdp );
		preparedStmt.executeUpdate();
		preparedStmt.close();
		return new Joueur( login, mdp, null, 0 );
	}
	
	//Retourne l'id d'un joueur
	public Integer getIdJoueur(Joueur j) throws SQLException, Exception{
		String query = "select id from Joueur where identifiant = ?";
		PreparedStatement preparedStmt = connexion.prepareStatement(query);
		preparedStmt.setString (1, j.getIdentifiant());
		ResultSet result = preparedStmt.executeQuery();
		if( result.next() ){
			int id = result.getInt( "id" );
			preparedStmt.close();
			result.close();
			return id;
		}else{
			preparedStmt.close();
			result.close();
			throw new Exception( "Impossible d'avoir l'id, du joueur: " + j.getIdentifiant() );
		}
		
	}
			
	//Renvoie la clé primaire généré lors d'un insert
	private int getCleGenerer( PreparedStatement p ) throws SQLException{
		ResultSet res = p.getGeneratedKeys();
		if ( res.next() ) {
			int id = res.getInt(1);
			res.close();
			return id;
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
			preparedStmt = this.connexion.prepareStatement( query );
			preparedStmt.setInt ( 1, id_essais );
			preparedStmt.setInt ( 2, this.saveCombinaison( essais.get( essai ) ) );
			preparedStmt.setInt ( 3, essai );
			preparedStmt.executeUpdate();
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
			preparedStmt = this.connexion.prepareStatement( query );
			preparedStmt.setInt ( 1, id_comb );
			preparedStmt.setString ( 2, pions.getPion( pion ).toString() );
			preparedStmt.setInt ( 3, pion );
			preparedStmt.executeUpdate();
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
		preparedStmt.executeUpdate();
		int id_tour = getCleGenerer( preparedStmt );
		preparedStmt.close();
		return id_tour;
	}
			
	//Permet de sauvegarder une partie 
	public void sauvegarderSolo( Solo solo ) throws SQLException, Exception{
		this.supprimerSolo(solo.getNom(), solo.getJoueur());
		String query = " insert into Partie(id_Joueur, nom, niveau)"
					+ " values (?, ?, ?)";
		PreparedStatement  preparedStmt = this.connexion.prepareStatement( query, Statement.RETURN_GENERATED_KEYS );
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
			
	public Solo chargerSolo( Joueur joueur ) throws SQLException, Exception{
		String query = "select Partie.id_Partie as id_Partie, Partie.id_Joueur as id_Joueur, Partie.nom as nom, Partie.niveau as niveau from Solo, Partie" +
				" where Solo.id_Partie = Partie.id_Partie AND Partie.nom = ? AND Partie.id_Joueur = ? AND Partie.nom IS NOT NULL";
		PreparedStatement preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setString( 1, joueur.getIdentifiant() );
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
			return null;
		}
		Solo solo = new Solo( "", Niveau.niveauString( niveau ), joueur );
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
	
	private void supprimerSolo( String nom_partie, Joueur j ) throws SQLException, Exception{
		String query = "select Solo.id_Partie as id, Solo.tour as tour from Solo, Partie" +
				" where Solo.id_Partie = Partie.id_Partie AND Partie.nom = ? AND Partie.id_Joueur = ?";
		PreparedStatement preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setString( 1, nom_partie );
		preparedStmt.setInt( 2, this.getIdJoueur( j ) );
		ResultSet resultSet = preparedStmt.executeQuery();
		int id_partie = -1;
		int idTour;
		if( resultSet.next() ){
			id_partie = resultSet.getInt( "id" );
			idTour = resultSet.getInt( "tour" );
			resultSet.close();
			preparedStmt.close();
		}else{
			resultSet.close();
			preparedStmt.close();
			return;
		}
		query = "delete from Solo where id_Partie = ?";
		preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setInt( 1, id_partie );
		preparedStmt.executeUpdate();
		preparedStmt.close();
		query = "delete from Partie where id_Partie = ?";
		preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setInt( 1, id_partie );
		preparedStmt.executeUpdate();
		preparedStmt.close();
		
		this.supprimeTour( idTour );
	}
	
	private void supprimeTour( int idTour ) throws SQLException {
		String query = "select combinaison, essais from Tour where id = ?";
		PreparedStatement preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setInt( 1, idTour );
		ResultSet resultSet = preparedStmt.executeQuery();
		int id_comb;
		int id_essais;
		if( resultSet.next() ){
			id_comb = resultSet.getInt( "combinaison" );
			id_essais = resultSet.getInt( "essais" );
			resultSet.close();
			preparedStmt.close();
		}else{
			resultSet.close();
			preparedStmt.close();
			return;
		}
		query = "delete from Tour where id = ?";
		preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setInt( 1, idTour );
		preparedStmt.executeUpdate();
		preparedStmt.close();
		
		this.supprimerCombinaison( id_comb );
		this.supprimerEssais(id_essais);
	}
	
	private void supprimerCombinaison( int idComb ) throws SQLException{
		String query = "delete from Pions where id_combinaison = ?";
		PreparedStatement preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setInt( 1, idComb );
		preparedStmt.executeUpdate();
		preparedStmt.close();
		
		query = "delete from Combinaison where id = ?";
		preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setInt( 1, idComb );
		preparedStmt.executeUpdate();
		preparedStmt.close();
	}
	
	private void supprimerEssais( int idEssais ) throws SQLException{
		this.supprimerEssai( idEssais );
		
		String query = "delete from Essais where id = ?";
		PreparedStatement preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setInt( 1, idEssais );
		preparedStmt.executeUpdate();
		preparedStmt.close();
	}
	
	private void supprimerEssai( int idEssais ) throws SQLException{
		String query = "select id_combinaison from Essai where id_essais = ?";
		PreparedStatement preparedStmt = this.connexion.prepareStatement( query );
		preparedStmt.setInt( 1, idEssais );
		ResultSet resultSet = preparedStmt.executeQuery();
		int idCombin;
		while( resultSet.next() ){
			idCombin = resultSet.getInt("id_combinaison");
			
			String query2 = "delete from Essai where id_essais = ? and id_combinaison = ?";
			PreparedStatement preparedStmt2 = this.connexion.prepareStatement( query2 );
			preparedStmt2.setInt( 1, idEssais );
			preparedStmt2.setInt( 2, idCombin );
			preparedStmt2.executeUpdate();
			preparedStmt2.close();
			
			this.supprimerCombinaison(idCombin);
		}
		resultSet.close();
		preparedStmt.close();
	}

}
