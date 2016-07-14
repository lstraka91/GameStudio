package sk.tsystems.gamestudio.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import sk.tsystems.gamestudio.UserIntefaceGameStudio;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.games.guessNumber.GuessNumber;
import sk.tsystems.gamestudio.games.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.games.nPuzzle.NPuzzle;
import sk.tsystems.gamestudio.services.ScoreServiceImpl;

public class ConsoleUIGameStudio implements UserIntefaceGameStudio {

	private List<Game> games;

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

	}

	@Override
	public void run() {
		while (true) {
			switch (showMenu()) {
			case PLAY:
				playGame();
				break;
			case SHOW_HIGH_SCORE:
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
			showScore(mines.getClass().getSimpleName());
			break;
		case 2:
			NPuzzle nPuzzle = new NPuzzle();
			nPuzzle.run();
			showScore(nPuzzle.getClass().getSimpleName());
			break;

		case 3:
			GuessNumber guessNum = new GuessNumber(20);
			guessNum.run();
			showScore(guessNum.getClass().getSimpleName());
			break;
		case 4: 
			return;
		default:
			break;
		}

	}

	private void showScore(String game) {
		ScoreServiceImpl score=new ScoreServiceImpl();
		score.setGame(game);
		System.out.println(score.toString());
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
}
