package sk.tsystems.gamestudio.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

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
