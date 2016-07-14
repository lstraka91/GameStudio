package sk.tsystems.gamestudio.entity;

import java.util.Date;

public class Comment {

	private String comment;
	private int identPlayer;
	private int identGame;
	private Date dateCommented;

	public Comment(String comment, int identPlayer, int identGame,
			Date dateCommented) {
		this.comment = comment;
		this.identPlayer = identPlayer;
		this.identGame = identGame;
		this.dateCommented = dateCommented;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public Date getDateCommented() {
		return dateCommented;
	}

	public void setDateCommented(Date dateCommented) {
		this.dateCommented = dateCommented;
	}

}
