package sk.tsystems.gamestudio.entity;

import java.util.Date;

public class Score {
	
	private int score;
	private int identPlayer;
	private int identGame;
	private Date datePlayed;
	private String gameName;
	private String playerName;
	
	
	public Score(int score, int identPlayer, int identGame, Date datePlayed,
			String gameName, String playerName) {
		
		this.score = score;
		this.identPlayer = identPlayer;
		this.identGame = identGame;
		this.datePlayed = datePlayed;
		this.gameName = gameName;
		this.playerName = playerName;
	}


	public Score(int score, int identPlayer, int identGame, Date datePlayed) {
		
		this.score = score;
		this.identPlayer = identPlayer;
		this.identGame = identGame;
		this.datePlayed = datePlayed;
		
	}
	
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getIdentPlayer() {
		return identPlayer;
	}
	public void setIdentPlayer(int identPlayer) {
		this.identPlayer = identPlayer;
	}
	public int getIdentGame() {
		return identGame;
	}
	public void setIdentGame(int identGame) {
		this.identGame = identGame;
	}
	public Date getDatePlayed() {
		return datePlayed;
	}
	public void setDatePlayed(Date datePlayed) {
		this.datePlayed = datePlayed;
	}
	
	public String getGameName() {
		return gameName;
	}


	public void setGameName(String gameName) {
		this.gameName = gameName;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public String toString(){
		StringBuilder sb= new StringBuilder();
		sb.append(getGameName()+" ");
		sb.append(getPlayerName()+ " ");
		sb.append(getScore()+" \n");
		return sb.toString();
	}
}
