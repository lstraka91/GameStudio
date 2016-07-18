package sk.tsystems.gamestudio.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RejtingId implements Serializable{
	
	private int hra;

	private int hrac;
	
	public int getHra() {
		return hra;
	}
	public void setHra(int hra) {
		this.hra = hra;
	}
	public int getHrac() {
		return hrac;
	}
	public void setHrac(int hrac) {
		this.hrac = hrac;
	}
	
}

