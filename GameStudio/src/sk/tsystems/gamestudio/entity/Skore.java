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
	private Hra hra;
	@ManyToOne(cascade = CascadeType.ALL)
	private Hrac hrac;

	public int getSkore() {
		return skore;
	}

	public void setSkore(int skore) {
		this.skore = skore;
	}

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
