package sk.tsystems.gamestudio.games.nPuzzle;

public class NPuzzle implements Runnable {

	private UserInterface userInterface;
	private Field field;
	private int score;

	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}



	public NPuzzle() {
		userInterface = new ConsoleUI();
		field = new Field(3, 3);
		// userInterface.newGame(field);
		score=0;
	}

	

	@Override
	public void run() {
		userInterface.newGame(field);
	}

}
