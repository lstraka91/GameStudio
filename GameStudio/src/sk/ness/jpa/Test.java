package sk.ness.jpa;



import sk.tsystems.gamestudio.entity.Commentos;
import sk.tsystems.gamestudio.services.hibernate.CommentServiceHibernateImpl;

public class Test {

	public static void main(String[] args) throws Exception {
		// Student student
		//
		// JpaHelper.beginTransaction();
		// JpaHelper.getEntityManager().persist();
		// JpaHelper.commitTransaction();
		// JpaHelper.closeAll();
//		Student student = new Student();
//		student.setMeno("Ander");
//		student.setPriezvisko("Zkosic");
//		student.setVek(55);
//
//		JpaHelper.beginTransaction();
//		EntityManager em = JpaHelper.getEntityManager();
//
//		// em.persist(student);
//		JpaHelper.commitTransaction();
//		Query query = em
//				.createQuery("Select s from Student s where s.meno=:meno");
//		query.setParameter("meno", "janko");
//		System.out.println(query.getResultList());
//		JpaHelper.beginTransaction();
//		student = em.find(Student.class, 2);
//
//		System.out.println(student);
//		student.setVek(99);
//		JpaHelper.commitTransaction();
//		// JpaHelper.closeAll();
//		System.out.println(student);
//		JpaHelper.closeAll();
		
		
//		System.out.println(comment.findCommentForGame("ff"));
		
		CommentServiceHibernateImpl comment = new CommentServiceHibernateImpl();
		Commentos com = new Commentos();
		com.setComment("Nejaky Text");
		com.setGameName("Minesweeper");
		com.setPlayerName("Ignac");
		comment.add(com);
	}
}
