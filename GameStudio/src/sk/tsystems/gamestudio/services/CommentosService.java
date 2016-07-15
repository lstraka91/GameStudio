package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Commentos;
import sk.tsystems.gamestudio.exceptions.CommentException;

public interface CommentosService {
	void add(Commentos comment) throws CommentException;
	List<Commentos> findCommentForGame(String game) throws CommentException;
}
