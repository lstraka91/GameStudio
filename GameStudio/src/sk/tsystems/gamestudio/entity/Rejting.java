package sk.tsystems.gamestudio.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rejting  {
	
		
	private int rejting;
	@EmbeddedId
	private RejtingId rajtId;

	
	
	public RejtingId getRajtId() {
		return rajtId;
	}

	public void setRajtId(RejtingId rajtId) {
		this.rajtId = rajtId;
	}

	public int getRejting() {
		return rejting;
	}

	public void setRejting(int rejting) {
		this.rejting = rejting;
	}
//
//	public Hra getHra() {
//		return hra;
//	}
//
//	public void setHra(Hra hra) {
//		this.hra = hra;
//	}
//
//	public Hrac getHrac() {
//		return hrac;
//	}
//
//	public void setHrac(Hrac hrac) {
//		this.hrac = hrac;
//	}

}
