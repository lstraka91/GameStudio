package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Comment;

public interface CommentService {

	void add(Comment comment);
	List<Comment> findCommentForGame(String game);
}
