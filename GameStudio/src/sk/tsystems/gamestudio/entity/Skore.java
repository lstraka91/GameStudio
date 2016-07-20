package sk.tsystems.gamestudio.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity

public class Skore {
	@Id
	@GeneratedValue
	private int id;

	private int skore;
	@ManyToOne(cascade = CascadeType.ALL)
	private Game game;
	@ManyToOne(cascade = CascadeType.ALL)
	private Hrac hrac;

	public int getSkore() {
		return skore;
	}

	public void setSkore(int skore) {
		this.skore = skore;
	}

	public Game getHra() {
		return game;
	}

	public void setHra(Game hra) {
		this.game = hra;
	}

	public Hrac getHrac() {
		return hrac;
	}

	public void setHrac(Hrac hrac) {
		this.hrac = hrac;
	}

}
