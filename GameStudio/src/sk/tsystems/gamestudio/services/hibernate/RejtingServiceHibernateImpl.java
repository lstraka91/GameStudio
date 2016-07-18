package sk.tsystems.gamestudio.services.hibernate;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.EntityManager;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Rejting;
import sk.tsystems.gamestudio.exceptions.RatingException;
import sk.tsystems.gamestudio.services.RejtingService;

public class RejtingServiceHibernateImpl implements RejtingService{

	@Override
	public void add(Rejting rejting) throws RatingException {
		
		try {
			JpaHelper.beginTransaction();
			EntityManager em = JpaHelper.getEntityManager();
			em.persist(rejting);
			JpaHelper.commitTransaction();
		} catch (Exception e) {
			System.out.println(e.getClass());
			if(e.getClass().equals(SQLIntegrityConstraintViolationException.class)){
				
				System.out.println("chytil constraint");
			}
			
		}
		
	}

	@Override
	public List<Rejting> findRejtingForGame(String game) throws RatingException {
		 JpaHelper.beginTransaction();
		 EntityManager em = JpaHelper.getEntityManager();
		 JpaHelper.commitTransaction();
		 return em.createQuery("Select r from Rejting r JOIN r.hra h where h.gameName=:gameName").setParameter("gameName", game).getResultList();
	}

}
