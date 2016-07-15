package sk.tsystems.gamestudio.entity;



import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private int ident;
	
	private String comment;
	private String gameName;
	private String playerName;
	
	private int identPlayer;
	private int identGame;
	private Date dateCommented;

	public Comment(){
		
	}

	public Comment(int ident, String comment, String gameName,String playerName, int identPlayer, int identGame,Date dateCommented) {
		
		this.ident = ident;
		this.comment = comment;
		this.gameName = gameName;
		this.playerName = playerName;
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
	
	public int getIdent() {
		return ident;
	}
	public void setIdent(int ident) {
		this.ident = ident;
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

}
