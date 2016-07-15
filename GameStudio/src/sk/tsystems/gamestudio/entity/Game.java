package sk.tsystems.gamestudio.entity;

public class Game {

	private int identGame;
	private String gameName;
	private String gameURL;
	private String author;

	public Game(int identGame, String gameName, String author, String gameURL) {

		this.identGame = identGame;
		this.gameName = gameName;
		this.gameURL = gameURL;
		this.author = author;
	}

	public int getIdentGame() {
		return identGame;
	}

	public void setIdentGame(int identGame) {
		this.identGame = identGame;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameURL() {
		return gameURL;
	}

	public void setGameURL(String gameURL) {
		this.gameURL = gameURL;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getGameName());
		return sb.toString();
	}
}
