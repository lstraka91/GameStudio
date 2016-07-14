package sk.tsystems.gamestudio;

import java.util.List;

import sk.tsystems.gamestudio.UI.ConsoleUIGameStudio;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.services.ScoreServiceImpl;

public class Main {

	public static void main(String[] args) {

		// new GuessNumber(50);
//		Games g = new Games();
//		g.addGame(new Game(1, "Minesweeper"));
//		g.addGame(new Game(2, "nPuzzle"));
//		g.addGame(new Game(3, "Guess The Number"));
//		UserIntefaceGameStudio ui = new ConsoleUIGameStudio(g);
//		UserIntefaceGameStudio ui = new ConsoleUIGameStudio();
//		ui.run();

		ScoreServiceImpl serv = new ScoreServiceImpl();
		try {
			List<Score> score = serv.getScoreforGame("to je jedno");
			for(Score scoree : score){
				System.out.println(scoree);
			}
		} catch (ScoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
