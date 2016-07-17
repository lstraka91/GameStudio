package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Comments;
import sk.tsystems.gamestudio.exceptions.CommentException;

public interface CommentService {

	void add(Comments comment) throws CommentException;
	List<Comments> findCommentForGame(String game) throws CommentException;
}
