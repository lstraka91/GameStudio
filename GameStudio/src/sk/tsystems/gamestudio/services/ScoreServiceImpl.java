package sk.tsystems.gamestudio.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.services.jdbc.DatabaseSetting;

public class ScoreServiceImpl implements ScoreService {

	private final String SELECT_SCORE = "select s.SCORE,s.ID_USER, s.ID_GAME,s.DATE_PLAYED,g.name, p.NAME from score s join game g on s.ID_GAME = g.ID_GAME join player p on s.ID_USER = p.ID_USER";

	@Override
	public void add(Score score) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Score> getScoreforGame(String game) throws ScoreException {
		List<Score> scores = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(
				DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD);
				PreparedStatement ps = connection
						.prepareStatement(SELECT_SCORE)) {
			// ps.setString(1, game);
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
		try {
			for (Score score : getScoreforGame("game")) {
				sb.append(score.toString());
			}

		} catch (ScoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

}
