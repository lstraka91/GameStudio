package sk.tsystems.gamestudio.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="Comments")
public class Comment {

	@Id
	@GeneratedValue
	private int ident;

	@Column(name="comments")
	private String userComment;
	@Transient
	private String gameName;
	@Transient
	private String playerName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Game hra;
	@ManyToOne(cascade = CascadeType.ALL)
	private Hrac hrac;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_comment")
	private Date dateCommented;
	
	
	
	public Comment( String userComment, Game hra, Hrac hrac) {
		
		this.userComment = userComment;
		this.hra = hra;
		this.hrac = hrac;
	}
	
	public Comment(){
		
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
	
	
public Date getDateCommented() {
		return dateCommented;
	}

	public void setDateCommented(Date dateCommented) {
		this.dateCommented = dateCommented;
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
	public Game getHra() {
		return hra;
	}
	public void setHra(Game game) {
		this.hra = game;
	}
	public Hrac getHrac() {
		return hrac;
	}
	public void setHrac(Hrac hrac) {
		this.hrac = hrac;
	}
	
	
}
