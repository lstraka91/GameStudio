package sk.tsystems.gamestudio.services.hibernate;

import java.util.List;

import javax.persistence.EntityManager;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.services.CommentosService;

public class CommentServiceHibernateImpl implements CommentosService {

	@Override
	public void add(Comment comment) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(comment);
		JpaHelper.commitTransaction();

	}

	@Override
	public List<Comment> findCommentForGame(String game) {

		 JpaHelper.beginTransaction();
		 EntityManager em = JpaHelper.getEntityManager();
		 JpaHelper.commitTransaction();
		 return em.createQuery("Select c from Comment c JOIN c.hra h where h.name=:gameName").setParameter("gameName", game).getResultList();

		
	}


}
