package sk.tsystems.gamestudio.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import sk.tsystems.gamestudio.ServiceInterface;
import sk.tsystems.gamestudio.UserIntefaceGameStudio;
import sk.tsystems.gamestudio.entity.Comments;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.exceptions.CommentException;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.exceptions.RatingException;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.games.GameInterface;
import sk.tsystems.gamestudio.games.guessNumber.GuessNumber;
import sk.tsystems.gamestudio.games.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.games.nPuzzle.NPuzzle;
import sk.tsystems.gamestudio.services.jdbc.CommentServiceJDBCImpl;
import sk.tsystems.gamestudio.services.jdbc.GameServiceJDBCImpl;
import sk.tsystems.gamestudio.services.jdbc.RatingServiceJDBCImpl;
import sk.tsystems.gamestudio.services.jdbc.ScoreServiceJDBCImpl;

public class ConsoleUI_JDBC implements ServiceInterface {

	private List<Game> games;
	private GameServiceJDBCImpl gameImpl;
	private ScoreServiceJDBCImpl score;
	private RatingServiceJDBCImpl rating;
	private CommentServiceJDBCImpl comment;
	
	public ConsoleUI_JDBC() {
		gameImpl = new GameServiceJDBCImpl();
		score = new ScoreServiceJDBCImpl();
		rating = new RatingServiceJDBCImpl();
		comment = new CommentServiceJDBCImpl();
	}

	@Override
	public void startGame(GameInterface gameIntrfc) {
		GameInterface game = gameIntrfc;
		String gameName = game.getClass().getSimpleName();
		showScore(gameName);
		game.run();
		int identGame = findGameIdentByName(gameName);
		addHighScore(game.getScore(), 1, identGame);
		getAverageRating(gameName);
		addRating(1, identGame);
		addComment(1, identGame);
		showComments(gameName);
	}

	private void showScore(String game) {
		score.setGame(game);
		System.out.println(score.toString());
	}

	private void showRating(String game) {
		rating.setGame(game);
		System.out.println(rating.toString());
	}

	private void showComments(String game) {
		comment.setGame(game);
		System.out.println(comment.toString());
	}

	/**
	 * In JDK 6 use Console class instead.
	 * 
	 * @see readLine()
	 */
	private BufferedReader input = new BufferedReader(new InputStreamReader(
			System.in));

	/**
	 * Reads line from console.
	 * 
	 * @return the string from console
	 */
	private String readLine() {
		// In JDK 6.0 and above Console class can be used
		// return System.console().readLine();

		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Iterate list of Games and returns ID of finding game or -1 if there is no
	 * match
	 * 
	 * @param gameName
	 * @return Ident of game from parameter
	 */
	private int findGameIdentByName(String gameName) {
		try {
			for (Game game : gameImpl.getGameList()) {
				if (game.getGameName().equals(gameName)) {
					return game.getIdentGame();
				}
			}
		} catch (GameException e) {
			e.printStackTrace();
		}
		return -1;
	}

	private void addHighScore(int highScore, int identPlayer, int identGame) {

		if (highScore > 0) {
			Date date = new Date();
			try {
				score.add(new Score(1000 / highScore, identPlayer, identGame,
						date));
			} catch (ScoreException e) {
				e.printStackTrace();
			}
		}
	}

	private void showHighScore() {
		try {
			for (Game game : gameImpl.getGameList()) {
				System.out
						.println("===========================================");
				System.out.println("High score table for game: "
						+ game.getGameName());
				System.out
						.println("===========================================");
				showScore(game.getGameName());
			}
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showRatings() {
		try {
			for (Game game : gameImpl.getGameList()) {
				System.out
						.println("===========================================");
				System.out.println("RATING table for game: "
						+ game.getGameName());
				System.out
						.println("===========================================");
				showRating(game.getGameName());
			}
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addRating(int identPlayer, int identGame) {
		try {
			rating.selectPlayerRating(identPlayer, identGame);
		} catch (RatingException e1) {
			e1.printStackTrace();
		}
		if (rating.getPlayerRate() > 0) {
			System.out.println("You allready rate the game :"+ rating.getPlayerRate());

		}
		System.out.println("Do you want to change the rating? ");
		if (doYouWant()) {
			if (rating.getPlayerRate() > 0) {
				try {
					rating.deleteRating(identPlayer, identGame);
				} catch (RatingException e) {
					e.printStackTrace();
				}
			}

			System.out.println("Enter rating 1-5");
			Scanner ratingScanner = new Scanner(System.in);
			if (ratingScanner.hasNextInt()) {
				int rating = ratingScanner.nextInt();
				if (rating > 0 && rating <= 5) {
					Date date = new Date();
					try {

						this.rating.add(new Rating(identGame, identPlayer,
								rating, date));
					} catch (RatingException e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("You enter wrong rating!! Input 1-5!!!");
			}
		}
	}

	private void getAverageRating(String gameName) {
		try {
			rating.averageRate(gameName);
			System.out
					.printf("%s, %s \n", "Count of Ratings", "Average Rating");
			System.out
					.printf("     %2d ,      %.2f \n",
							this.rating.getCountOfRatings(),
							this.rating.getAvgRating());
				} catch (RatingException e) {
					e.printStackTrace();
		}

	}

	private boolean doYouWant() {
		System.out.println("Type y/n");
		boolean condition = true;
		Scanner scan = new Scanner(System.in);
		do {

			if (scan.hasNext("y|Y")) {
				condition = false;

				return true;
			} else if (scan.hasNext("n|N")) {
				condition = false;

				return false;

			} else {
				System.out.println("Bad input..type Y/N");
				scan = new Scanner(System.in);

			}
		} while (condition);
		return false;
	}

	private void addComment(int identPlayer, int identGame) {
		Date date = new Date();
		System.out.println("Do you want to comment the game?");
		if (doYouWant() == true) {

			System.out.println("Enter the comment: ");
			Scanner read = new Scanner(System.in);
			String userComment = read.nextLine();

			try {
				this.comment.add(new Comments(userComment, identPlayer,
						identGame, new java.sql.Date(date.getTime())));
			} catch (CommentException e) {
				System.err.println("Error during saving Comment");
			}
		}

	}
}
