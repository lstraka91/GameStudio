package sk.tsystems.gamestudio.games.guessNumber;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber implements GuessNumberInterface,Runnable {
	private int toGuess;
	private int turns;
	private boolean guessed;
	private int range;

	public int getTurns() {
		return turns;
	}

	public boolean isGuessed() {
		return guessed;
	}

	public GuessNumber(int range) {
		toGuess = new Random().nextInt(range);
		turns = 0;
		guessed = false;
		this.range=range; 
		
	}

	/* (non-Javadoc)
	 * @see sk.tsystems.gamestudio.games.guessNumber.GuessNumberInterface#guess(int)
	 */
	@Override
	public void guess(int number) {
		while (!isGuessed()) {
			turns++;
			System.out.println("Enter number from 0 to " + number);
			Scanner readLine = new Scanner(System.in);
			int guess = readLine.nextInt();
			if (guess == toGuess) {
				guessed = true;
				System.out.println("Congrats! You guess the number on "+getTurns()+" turns");
			} else if (guess > toGuess) {
				System.out.println("Try again with lower number");
			} else if (guess < toGuess) {
				System.out.println("Try again with bigger number");
			}
			readLine.close();
		}
	}
	@Override
	public void run(){
		guess(range);
	}

}