package sk.tsystems.gamestudio.services.hibernate;

import javax.persistence.EntityManager;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Hra;

public class HraServiceHibernateImpl {
	
	public Hra getGame(String gameName) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		JpaHelper.commitTransaction();
		Hra game = (Hra) em
				.createQuery("Select h from Hra h where h.gameName=:gameName")
				.setParameter("gameName", gameName).getSingleResult();

		return game;
	}
}
