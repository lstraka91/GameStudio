package sk.tsystems.gamestudio.services.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.exceptions.RatingException;
import sk.tsystems.gamestudio.services.RatingService;

public class RatingServiceJDBCImpl implements RatingService {
	private final String INSERT_INTO_RATING = "insert into RATING (ID_USER,ID_GAME, RATING, DATE_RATE) values(? ,?, ?, ?)";
	private final String SELECT_RATING_ON_GAME = "select r.ID_GAME,r.ID_USER, r.rating, r.DATE_RATE, p.name,g.name from rating r join game g on r.ID_GAME = g.ID_GAME join player p on r.ID_USER = p.ID_USER where g.name like ? ";
	private final String SELECT_AVG_RATE = "select g.name, COUNT(*) as pocet_hodnoteni ,round(avg(r.RATING),2)as priemerne_hodnotenie from RATING r join game g on r.ID_GAME = g.ID_GAME where g.name= ? group by g.name";
	private String game;
	private int countOfRatings;
	private float avgRating;

	public int getCountOfRatings() {
		return countOfRatings;
	}

	public void setCountOfRatings(int countOfRatings) {
		this.countOfRatings = countOfRatings;
	}

	public float getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	@Override
	public void add(Rating rating) throws RatingException {
		try (Connection connection = DriverManager.getConnection(DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD); PreparedStatement ps = connection.prepareStatement(INSERT_INTO_RATING)) {
			ps.setInt(1, rating.getIdentPlayer());
			ps.setInt(2, rating.getIdentGame());
			ps.setInt(3, rating.getRating());
			ps.setDate(4, new java.sql.Date(rating.getDateRated().getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RatingException("Error saving rating", e);
		}

	}

	@Override
	public List<Rating> findRatingForGame(String game) throws RatingException {
		List<Rating> ratings = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD); PreparedStatement ps = connection.prepareStatement(SELECT_RATING_ON_GAME)) {
			ps.setString(1, game);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Rating rating = new Rating(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5),
							rs.getString(6));
					ratings.add(rating);
				}
			}
		} catch (SQLException e) {
			throw new RatingException("Error loading ratings", e);
		}

		return ratings;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		try {
			System.out.printf("   %-10s %3s  %s ", "PLAYER", "RATE", "DATE \n");
			System.out.println("---------------------------------------");
			for (Rating rating : findRatingForGame(getGame())) {
				index++;
				sb.append(index + ". " + rating.toString());

			}
			if (index == 0) {
				sb.append("Game " + getGame() + "have no rating yet!");
			}
		} catch (RatingException e) {

			e.printStackTrace();
		}
		sb.append("\n===========================================\n");
		return sb.toString();
	}

	public void averageRate(String game) throws RatingException {
		Rating rating = null;
		try (Connection connection = DriverManager.getConnection(DatabaseSetting.URL, DatabaseSetting.USER,
				DatabaseSetting.PASSWORD); PreparedStatement ps = connection.prepareStatement(SELECT_AVG_RATE)) {
			ps.setString(1, game);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					// rating = new Rating(rs.getInt(1), rs.getInt(2),
					// rs.getInt(3),
					// rs.getDate(4),rs.getString(5),rs.getString(6));
					setAvgRating(rs.getFloat(3));
					setCountOfRatings(rs.getInt(2));
				}
			}
		} catch (SQLException e) {
			throw new RatingException("Error loading ratings", e);
		}
		// return rating;
	}

}
