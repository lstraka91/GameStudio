package sk.tsystems.gamestudio.services.hibernate;

import javax.persistence.EntityManager;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Hrac;

public class HracServiceHibernateImpl {

	public void insertNewHrac(Hrac hrac){
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(hrac);
		JpaHelper.commitTransaction();
	}
	
	public Hrac getHracFromDB(String name){
		 JpaHelper.beginTransaction();
		 EntityManager em = JpaHelper.getEntityManager();
		 JpaHelper.commitTransaction();
		 Hrac hrac = (Hrac) em.createQuery("Select h from Hrac h where h.name=:name").setParameter("name", name).getSingleResult();
	
		 return hrac;
	}
}
