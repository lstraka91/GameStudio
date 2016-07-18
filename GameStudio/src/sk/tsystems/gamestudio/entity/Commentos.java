package sk.tsystems.gamestudio.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Commentos {

	@Id
	@GeneratedValue
	private int ident;

	private String userComment;
	@Transient
	private String gameName;
	@Transient
	private String playerName;
	@ManyToOne(cascade = CascadeType.ALL)
	private Hra hra;
	@ManyToOne(cascade = CascadeType.ALL)
	private Hrac hrac;
	
	
	
	public Commentos( String userComment, Hra hra, Hrac hrac) {
		
		this.userComment = userComment;
		this.hra = hra;
		this.hrac = hrac;
	}
	
	public Commentos(){
		
	}
	public int getIdent() {
		return ident;
	}
	public void setIdent(int ident) {
		this.ident = ident;
	}
	
	public String getUserComment() {
		return userComment;
	}
	public void setUserComment(String userComment) {
		this.userComment = userComment;
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
//	public int getIdentPlayer() {
//		return id_user;
//	}
//	public void setIdentPlayer(int identPlayer) {
//		this.id_user = identPlayer;
//	}
//	public int getIdentGame() {
//		return id_game;
//	}
//	public void setIdentGame(int identGame) {
//		this.id_game = identGame;
//	}
	public Hra getHra() {
		return hra;
	}
	public void setHra(Hra hra) {
		this.hra = hra;
	}
	public Hrac getHrac() {
		return hrac;
	}
	public void setHrac(Hrac hrac) {
		this.hrac = hrac;
	}
	
	
}
