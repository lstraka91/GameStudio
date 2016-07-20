package sk.tsystems.gamestudio.services.hibernate;

import java.util.List;

import javax.persistence.EntityManager;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Skore;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.services.SkoreService;

public class SkoreServiceHibernateImpl implements SkoreService{

	@Override
	public void add(Skore skore) throws ScoreException {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(skore);
		JpaHelper.commitTransaction();
		
	}

	@Override
	public List<Skore> findSkoreForGame(String game) throws ScoreException {
		 JpaHelper.beginTransaction();
		 EntityManager em = JpaHelper.getEntityManager();
		 JpaHelper.commitTransaction();
		 return em.createQuery("Select s from Skore s JOIN s.game h where h.name=:gameName order by s.skore desc").setParameter("gameName", game).getResultList();
	}

}
