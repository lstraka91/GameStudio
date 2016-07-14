package sk.tsystems.gamestudio.services.jdbc;

public class GamesJDBC {

	  public static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";
	    public static final String USER = "strakal";
	    public static final String PASSWORD = "p4ssw0rd";

	   // public static final String QUERY_CREATE_BEST_TIMES = "CREATE TABLE player_time (name VARCHAR(128) NOT NULL, best_time INT NOT NULL)";
	   // public static final String QUERY_ADD_BEST_TIME = "INSERT INTO player_time (name, best_time) VALUES (?, ?)";
	    public static final String QUERY_SELECT_BEST_TIMES = "SELECT name, id_game FROM game";

	    
}
