package sk.tsystems.gamestudio.services.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Rejting;
import sk.tsystems.gamestudio.exceptions.RatingException;
import sk.tsystems.gamestudio.services.RejtingService;

public class RejtingServiceHibernateImpl implements RejtingService {
	private float avgRate;
	private int count;

	public float getAvgRate() {
		return avgRate;
	}

	public void setAvgRate(float avgRate) {
		this.avgRate = avgRate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void add(Rejting rejting) throws RatingException {
		if (selectRejting(rejting) == 0) {

			JpaHelper.beginTransaction();
			EntityManager em = JpaHelper.getEntityManager();
			em.persist(rejting);
			JpaHelper.commitTransaction();
		} else {
			updateRejting(rejting);
		}
	}

	@Override
	public List<Rejting> findRejtingForGame(String game) throws RatingException {

		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		JpaHelper.commitTransaction();
		return em
				.createQuery(
						"Select r from Rejting r JOIN r.hra h where h.gameName=:gameName")
				.setParameter("gameName", game).getResultList();
	}

	public int selectRejting(Rejting rejting) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		JpaHelper.commitTransaction();
		ArrayList<Rejting> rejtList = (ArrayList<Rejting>) em
				.createQuery("Select r from Rejting r where r.rajtId=:rajt ")
				.setParameter("rajt", rejting.getRatingId()).getResultList();
		System.out.println(rejtList);
		System.out.println(rejtList.size());
		return rejtList.size();
	}

	private void updateRejting(Rejting rejting) {
		EntityManager em = JpaHelper.getEntityManager();
		EntityTransaction updt = em.getTransaction();
		updt.begin();
		em.createQuery("update Rejting set rejting=:rejt where rajtId=:rajt")
				.setParameter("rejt", rejting.getRating())
				.setParameter("rajt", rejting.getRatingId()).executeUpdate();
		updt.commit();
	}

	public void getAverageRatingAndCount(Rejting rejting) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		List<Long> countList = em
				.createQuery(
						"select COUNT(*) from Rejting r join r.ratingId h  where h.game=:gameName group by h.hra")
				.setParameter("gameName", rejting.getRatingId().getGameId())
				.getResultList();
		JpaHelper.commitTransaction();

		JpaHelper.beginTransaction();
		List<Double> avgList = em
				.createQuery(
						"select round(avg(r.rejting),2) from Rejting r join r.ratingId h  where h.game=:gameName group by h.hra")
				.setParameter("gameName", rejting.getRatingId().getGameId())
				.getResultList();
		JpaHelper.commitTransaction();
		double avg = 0;
		long count = 0;
		if (!avgList.isEmpty() || !countList.isEmpty()) {
			avg = avgList.get(0);
			count = countList.get(0);
		}

		setAvgRate((float) avg);
		setCount((int) count);

	}

}