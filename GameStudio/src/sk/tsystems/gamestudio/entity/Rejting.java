package sk.tsystems.gamestudio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Rating")
public class Rejting {

	private int rating;
	@EmbeddedId
	private RatingId ratingId;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_rate")
	private Date rateDate;
	@Transient
	private String playerName;
	@Transient
	private String gameName;

	
	public RatingId getRatingId() {
		return ratingId;
	}

	public void setRatingId(RatingId ratingId) {
		this.ratingId = ratingId;
	}

	public Date getRateDate() {
		return rateDate;
	}

	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}


	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	//
	// public Hra getHra() {
	// return hra;
	// }
	//
	// public void setHra(Hra hra) {
	// this.hra = hra;
	// }
	//
	// public Hrac getHrac() {
	// return hrac;
	// }
	//
	// public void setHrac(Hrac hrac) {
	// this.hrac = hrac;
	// }

}
