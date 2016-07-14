package sk.tsystems.gamestudio.games.nPuzzle;

public class NPuzzle implements Runnable {

	private UserInterface userInterface;
	private Field field;

	public NPuzzle() {
		userInterface = new ConsoleUI();
		field = new Field(3, 3);
		// userInterface.newGame(field);
	}

	

	@Override
	public void run() {
		userInterface.newGame(field);
	}

}
