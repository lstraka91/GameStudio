//package sk.tsystems.gamestudio.entity;
//
//import java.util.Date;
//import java.util.Formatter;
//
//public class Rating {
//	private int identGame;
//	private int identPlayer;
//	private int rating;
//	private Date dateRated;
//	private String playerName;
//	private String gameName;
//
//	public Rating(int identGame, int identPlayer, int rating, Date dateRated,
//			String playerName, String gameName) {
//		super();
//		this.identGame = identGame;
//		this.identPlayer = identPlayer;
//		this.rating = rating;
//		this.dateRated = dateRated;
//		this.playerName = playerName;
//		this.gameName = gameName;
//	}
//	
//	public Rating(int identGame, int identPlayer, int rating, Date dateRated){
//		this.identGame = identGame;
//		this.identPlayer = identPlayer;
//		this.rating = rating;
//		this.dateRated = dateRated;
//	}
//
//	public String getPlayerName() {
//		return playerName;
//	}
//
//	public void setPlayerName(String playerName) {
//		this.playerName = playerName;
//	}
//
//	public String getGameName() {
//		return gameName;
//	}
//
//	public void setGameName(String gameName) {
//		this.gameName = gameName;
//	}
//
//	public int getIdentGame() {
//		return identGame;
//	}
//
//	public void setIdentGame(int identGame) {
//		this.identGame = identGame;
//	}
//
//	public int getIdentPlayer() {
//		return identPlayer;
//	}
//
//	public void setIdentPlayer(int identPlayer) {
//		this.identPlayer = identPlayer;
//	}
//
//	public int getRating() {
//		return rating;
//	}
//
//	public void setRating(int rating) {
//		this.rating = rating;
//	}
//
//	public Date getDateRated() {
//		return dateRated;
//	}
//
//	public void setDateRated(Date dateRated) {
//		this.dateRated = dateRated;
//	}
//
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		Formatter formatter = new Formatter();
//
//		sb.append(formatter.format("%-10s %3d  %td.%tm.%ty ", getPlayerName(),
//				getRating(), getDateRated(), getDateRated(), getDateRated()));
//		sb.append("\n");
//		formatter.close();
//		return sb.toString();
//
//	}
//}
