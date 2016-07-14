package sk.tsystems.gamestudio;

import sk.tsystems.gamestudio.UI.ConsoleUIGameStudio;

public class Main {

	public static void main(String[] args) {

		// new GuessNumber(50);
//		Games g = new Games();
//		g.addGame(new Game(1, "Minesweeper"));
//		g.addGame(new Game(2, "nPuzzle"));
//		g.addGame(new Game(3, "Guess The Number"));
//		UserIntefaceGameStudio ui = new ConsoleUIGameStudio(g);
		UserIntefaceGameStudio ui = new ConsoleUIGameStudio();
		ui.run();

	}

}
