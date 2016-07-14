package sk.tsystems.gamestudio.entity;

import java.util.Date;

public class Score {
	
	private int score;
	private int identPlayer;
	private int identGame;
	private Date datePlayed;
	
	
	public Score(int score, int identPlayer, int identGame, Date datePlayed) {
		super();
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
	
	
}
