package sk.tsystems.gamestudio.games.guessNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class GuessNumber implements GuessNumberInterface {
	private int toGuess;
	private int turns;
	private boolean guessed;

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
		guess(range);
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
				System.out.println("Congrats");
			} else if (guess > toGuess) {
				System.out.println("Try again with lower number");
			} else if (guess < toGuess) {
				System.out.println("Try again with bigger number");
			}
		}
	}

}