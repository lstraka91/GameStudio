package sk.tsystems.gamestudio.services.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sk.ness.jpa.JpaHelper;
import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Commentos;
import sk.tsystems.gamestudio.exceptions.CommentException;
import sk.tsystems.gamestudio.services.CommentService;
import sk.tsystems.gamestudio.services.CommentosService;

public class CommentServiceHibernateImpl implements CommentosService {

	@Override
	public void add(Commentos comment){

		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(comment);
		JpaHelper.commitTransaction();

	}

	@Override
	public List<Commentos> findCommentForGame(String game) {

//		JpaHelper.beginTransaction();
//		EntityManager em = JpaHelper.getEntityManager();
//		JpaHelper.commitTransaction();
//		return em
//				.createQuery("Select c from Comment c where c.id_game=:gameId")
//				.setParameter("id_game", 2).getResultList();

		return null;
	}

}
