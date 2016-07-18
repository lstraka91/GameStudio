package sk.tsystems.gamestudio.services.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.services.ScoreService;

public class ScoreServiceJDBCImpl implements ScoreService {
	private String game;
	private final String SELECT_SCORE = "select s.SCORE,s.ID_USER, s.ID_GAME,s.DATE_PLAYED,g.name, p.NAME from score s join game g on s.ID_GAME = g.ID_GAME join player p on s.ID_USER = p.ID_USER WHERE g.name like ? ORDER BY s.score DESC ";
	private final String INSERT_INTO_SCORE = "insert into SCORE (ID_USER,ID_GAME, score, date_played)	values(?, ?, ?, ? )";

	public ScoreServiceJDBCImpl() {
		game = null;
	}

	@Override
	public void add(Score score) throws ScoreException {
		try (Connection connection = DriverManager.getConnection(
				DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD);
				PreparedStatement ps = connection
						.prepareStatement(INSERT_INTO_SCORE)) {
			ps.setInt(1, score.getIdentPlayer());
			ps.setInt(2, score.getIdentGame());
			ps.setInt(3, score.getScore());
			ps.setDate(4, new java.sql.Date(score.getDatePlayed().getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ScoreException("Error saving score", e);
		}

	}

	@Override
	public List<Score> getScoreforGame(String game) throws ScoreException {
		List<Score> scores = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(
				DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD);
				PreparedStatement ps = connection
						.prepareStatement(SELECT_SCORE)) {
			ps.setString(1, game);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Score score = new Score(rs.getInt(1), rs.getInt(2),
							rs.getInt(3), rs.getDate(4), rs.getString(5),
							rs.getString(6));
					scores.add(score);
				}
			}
		} catch (SQLException e) {
			throw new ScoreException("Error loading score", e);
		}

		return scores;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		System.out.println("========TABLE HIGH SCORE========");
		try {
			System.out.printf("   %-10s %3s  %s ", "PLAYER", "SCORE", "DATE \n");
			System.out.println("---------------------------------------");
			for (Score score : getScoreforGame(getGame())) {
				index++;
				sb.append(index + ". " + score.toString());

			}
			if (index == 0) {
				sb.append("There is no High Score yet!");
			}
		} catch (ScoreException e) {

			e.printStackTrace();
		}				
		sb.append("\n===========================================\n");
		return sb.toString();
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

}
