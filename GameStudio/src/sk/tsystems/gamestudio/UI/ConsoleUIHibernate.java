package sk.tsystems.gamestudio.UI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import sk.tsystems.gamestudio.ServiceInterface;
import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Game;
import sk.tsystems.gamestudio.entity.Hrac;
import sk.tsystems.gamestudio.entity.Rejting;
import sk.tsystems.gamestudio.entity.RatingId;
import sk.tsystems.gamestudio.entity.Skore;
import sk.tsystems.gamestudio.exceptions.GameException;
import sk.tsystems.gamestudio.exceptions.RatingException;
import sk.tsystems.gamestudio.exceptions.ScoreException;
import sk.tsystems.gamestudio.games.GameInterface;
import sk.tsystems.gamestudio.services.hibernate.CommentServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.HraServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.HracServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.RejtingServiceHibernateImpl;
import sk.tsystems.gamestudio.services.hibernate.SkoreServiceHibernateImpl;

public class ConsoleUIHibernate implements ServiceInterface {
	SkoreServiceHibernateImpl skoreImpl;
	RejtingServiceHibernateImpl rejtingImpl;
	HracServiceHibernateImpl playerImpl;
	HraServiceHibernateImpl gameImpl;
	CommentServiceHibernateImpl commentImpl;
	Game game;
	Hrac player;

	public ConsoleUIHibernate() {
		skoreImpl = new SkoreServiceHibernateImpl();
		rejtingImpl = new RejtingServiceHibernateImpl();
		playerImpl = new HracServiceHibernateImpl();
		gameImpl = new HraServiceHibernateImpl();
		commentImpl = new CommentServiceHibernateImpl();
		game = new Game();
		player = new Hrac();
	}

	public void startGame(GameInterface gameIntrfc) {
		GameInterface game = gameIntrfc;
		String gameName = game.getClass().getSimpleName();
		String playerName = "Valibuk";
		showSkore(gameName);
		game.run();

		// DOCASNE NESPRAVENE PRE UZIVATELA
		try {
			addHighScore(game.getScore(), gameName, playerName);
			addRating(gameName, playerName);
			addComment(gameName, playerName);
			showComments(gameName);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showSkore(String gameName) {
		try {
			List<Skore> scoreList = skoreImpl.findSkoreForGame(gameName);
			int index = 0;
			System.out.println("========TABLE HIGH SCORE========");
			System.out.printf("   %-10s %3s ", "PLAYER", "SCORE \n");
			System.out.println("---------------------------------------");
			for (Skore skore : scoreList) {
				index++;
				System.out.print(index + ". ");
				System.out.printf("%-10s %3d \n", skore.getHrac().getName(),
						skore.getSkore());
			}
			if (index == 0) {
				System.out.println("There is no High Score yet!");
			}

		} catch (ScoreException e) {
			System.err.println("Error during loading Score");
		}
		System.out.println("===========================================\n");
	}

	private void addHighScore(int highScore, String gameName, String playerName) throws GameException {
		Skore skore = new Skore();
		if (highScore > 0) {
			game = gameImpl.getGameByName(gameName);
			player = playerImpl.getHracFromDB(playerName);
			skore.setHra(game);
			skore.setHrac(player);
			skore.setSkore(1000 / highScore);
			try {
				skoreImpl.add(skore);
			} catch (ScoreException e) {
				System.err.println("Error during save the score ");
			}
		}
	}

	public void showAvgRateAndCount(String gameName) {
		try {
			game = gameImpl.getGameByName(gameName);

			Rejting rejting = new Rejting();
			RatingId rId = new RatingId();
			rId.setGameId(game.getIdentGame());
			rejting.setRatingId(rId);
			rejtingImpl.getAverageRatingAndCount(rejting);
			if (rejtingImpl.getCount() == 0 || rejtingImpl.getAvgRate() == 0) {
				System.out.println("==============================");
				System.out.println("There is no rating yet !");
				System.out.println("==============================");
			} else {
				System.out.println("========================================");
				System.out.print("Count of Ratings: " + rejtingImpl.getCount());
				System.out.println(" Average rating :"
						+ rejtingImpl.getAvgRate());
				System.out.println("========================================");
			}
		} catch (GameException e) {

			e.printStackTrace();
		}
	}

	private void addRating(String gameName, String playerName) throws GameException {

		System.out.println("Do you want to add the rating? ");
		if (doYouWant()) {
			Game game = getGame(gameName);
			Hrac player = getPlayer(playerName);

			System.out.println("Enter rating 1-5");
			Scanner ratingScanner = new Scanner(System.in);
			if (ratingScanner.hasNextInt()) {
				int rating = ratingScanner.nextInt();
				if (rating > 0 && rating <= 5) {
					Rejting ratingObj = new Rejting();
					RatingId rid = new RatingId();
					rid.setGameId(game.getIdentGame());
					rid.setPlayer(player.getId());
					ratingObj.setRating(rating);
					ratingObj.setRatingId(rid);
					try {
						rejtingImpl.add(ratingObj);
					} catch (RatingException e) {
						System.err.println("Error during save Rating");
					}
				}
			} else {
				System.out.println("You enter wrong rating!! Input 1-5!!!");
			}
		}
	}

	private Hrac getPlayer(String playerName) {
		return new HracServiceHibernateImpl().getHracFromDB(playerName);
	}

	private Game getGame(String gameName) throws GameException {
		Game game = new HraServiceHibernateImpl().getGameByName(gameName);
		return game;
	}

	private void showComments(String gameName) {
		ArrayList<Comment> commentList = (ArrayList<Comment>) commentImpl
				.findCommentForGame(gameName);
		int index = 0;
		System.out.println("===================================");
		System.out.println("------------COMMENTS---------------");
		System.out.println("===================================");
		for (Comment comment : commentList) {
			index++;
			System.out.printf("%1d, %5s, %s, %s \n", index,
					comment.getDateCommented(), comment.getHrac().getName(),
					comment.getUserComment());
			// System.out.println(index + ". " + comment.getHrac().getName() +
			// " "+ comment.getUserComment()+" "+comment.getDateCommented());
		}

	}

	private void addComment(String gameName, String playerName) throws GameException {

		System.out.println("Do you want to comment the game?");
		if (doYouWant() == true) {

			Comment comment = new Comment();
			comment.setHra(getGame(gameName));
			comment.setHrac(getPlayer(playerName));
			comment.setDateCommented(new Date());
			System.out.println("Enter the comment: ");
			Scanner read = new Scanner(System.in);
			String userComment = read.nextLine();
			comment.setUserComment(userComment);
			commentImpl.add(comment);
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

}
