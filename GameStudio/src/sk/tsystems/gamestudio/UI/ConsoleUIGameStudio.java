package sk.tsystems.gamestudio.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import sk.tsystems.gamestudio.UserIntefaceGameStudio;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.games.guessNumber.GuessNumber;
import sk.tsystems.gamestudio.games.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.games.nPuzzle.NPuzzle;
import sk.tsystems.gamestudio.services.jdbc.GameServiceJDBCImpl;
import sk.tsystems.gamestudio.services.jdbc.RatingServiceJDBCImpl;
import sk.tsystems.gamestudio.services.jdbc.ScoreServiceJDBCImpl;

public class ConsoleUIGameStudio implements UserIntefaceGameStudio {

	private List<Game> games;
	private GameServiceJDBCImpl gameImpl;
	private ScoreServiceJDBCImpl score;
	private RatingServiceJDBCImpl rating;

	private enum MenuOption {
		PLAY, SHOW_HIGH_SCORE, RATING, EXIT
	};

	private enum Games {
		MINESWEEPER, N_PUZZLE, GUESS_NUMBER, GO_BACK
	};

	public ConsoleUIGameStudio(Games games) {
		// this.games=games.getGamesList();
	}

	public ConsoleUIGameStudio() {
		gameImpl = new GameServiceJDBCImpl();
		score = new ScoreServiceJDBCImpl();
		rating = new RatingServiceJDBCImpl();
	}

	@Override
	public void run() {
		while (true) {
			switch (showMenu()) {
			case PLAY:
				playGame();
				break;
			case SHOW_HIGH_SCORE:
				showHighScore();
				break;
			case RATING:
				break;
			case EXIT:
				return;
			}
		}
	}

	private MenuOption showMenu() {
		System.out.println("-----------------------------------------------");
		System.out.println("--------Welcome to my Game-Center-Studio-------");
		for (MenuOption option : MenuOption.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > MenuOption.values().length);

		return MenuOption.values()[selection - 1];
	}

	private void playGame() {
		// for(Game game : games){
		// System.out.println(game + " "+ game.toString());
		// }

		System.out.println("Choose game you want to Play");
		for (Games games : Games.values()) {
			System.out.printf("%d. %s%n", games.ordinal() + 1, games);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Game Index: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > Games.values().length);
		switch (selection) {
		case 1:
			Minesweeper mines = new Minesweeper();
			mines.run();
			String gameName = mines.getClass().getSimpleName();
			int ident = findGameIdentByName(gameName);
			addHighScore(mines.getScore(), 1, ident);
			showRating(gameName);
			showScore(gameName);

			break;
		case 2:
			NPuzzle nPuzzle = new NPuzzle();
			nPuzzle.run();
			gameName = nPuzzle.getClass().getSimpleName();
			ident = findGameIdentByName(gameName);
			addHighScore(nPuzzle.getScore(), 1, ident);
			showScore(gameName);
			break;

		case 3:
			GuessNumber guessNum = new GuessNumber(20);
			guessNum.run();
			gameName = guessNum.getClass().getSimpleName();
			ident = findGameIdentByName(gameName);
			addHighScore(guessNum.getScore(), 1, ident);
			showScore(gameName);
			break;
		case 4:
			return;
		default:
			break;
		}

	}

	private void showScore(String game) {

		score.setGame(game);
		System.out.println(score.toString());
	}

	private void showRating(String game) {
		rating.setGame(game);
		System.out.println(rating.toString());
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
}
