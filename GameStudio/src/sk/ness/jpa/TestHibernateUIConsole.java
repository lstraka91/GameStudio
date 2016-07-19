package sk.ness.jpa;

import sk.tsystems.gamestudio.UI.ConsoleUIHibernate;
import sk.tsystems.gamestudio.entity.Hra;
import sk.tsystems.gamestudio.games.guessNumber.GuessNumber;
import sk.tsystems.gamestudio.games.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.services.hibernate.HraServiceHibernateImpl;

public class TestHibernateUIConsole {

	public static void main(String[] args) {
	ConsoleUIHibernate ui = new ConsoleUIHibernate();
	HraServiceHibernateImpl gameImpl= new HraServiceHibernateImpl();
	
	ui.showSkore("guessNumber");

//	Hra game = new Hra();
//	game.setGameName("Minesweeper");
//	gameImpl.addGame(game);
	ui.showAvgRateAndCount("guessNumber");
	
	ui.startGame(new GuessNumber(20));
	
	}

}
