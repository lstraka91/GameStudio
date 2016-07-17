package sk.tsystems.gamestudio.services.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sk.tsystems.gamestudio.entity.Comments;
import sk.tsystems.gamestudio.exceptions.CommentException;
import sk.tsystems.gamestudio.services.CommentService;

public class CommentServiceJDBCImpl implements CommentService {
	private String game;
	private final String SELECT_COMMENTS = "select c.comments,g.name, p.NAME,c.DATE_COMMENT,g.name, p.NAME from comments c join game g on c.ID_GAME = g.ID_GAME join player p on c.ID_USER = p.ID_USER WHERE g.name like ?";
	private final String INSERT_INTO_COMMENTS = "insert into COMMENTS (ID_USER,ID_GAME, comments, date_comment)	values(?, ?, ?, ? )";

	@Override
	public void add(Comments comment) throws CommentException {
		try (Connection connection = DriverManager.getConnection(DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD); PreparedStatement ps = connection.prepareStatement(INSERT_INTO_COMMENTS)) {
			ps.setInt(1, comment.getIdentPlayer());
			ps.setInt(2, comment.getIdentGame());
			ps.setString(3, comment.getComment());
			ps.setDate(4, new java.sql.Date(comment.getDateCommented().getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new CommentException("Error adding comment to Comments", e);
		}

	}

	@Override
	public List<Comments> findCommentForGame(String game) throws CommentException {
		List<Comments> comments = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD); PreparedStatement ps = connection.prepareStatement(SELECT_COMMENTS)) {
			ps.setString(1, game);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Comments comment = new Comments(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4));
					comments.add(comment);
				}
			}
		} catch (SQLException e) {
			throw new CommentException("Error loading comments", e);
		}

		return comments;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		try {
			System.out.println("======COMMENTS======");
			for (Comments comment : findCommentForGame(getGame())) {
				index++;
				sb.append(index + ". " + comment.toString());
			}
			if (index == 0) {
				sb.append("There is no Rating yet!");
			}
		} catch (CommentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

}
