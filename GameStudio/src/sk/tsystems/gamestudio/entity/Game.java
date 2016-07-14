package sk.tsystems.gamestudio.entity;

public class Game {

	private int identGame;
	private String gameName;
	
	public Game(int identGame, String gameName) {
	
		this.identGame = identGame;
		this.gameName = gameName;
		
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
	
	public String toString(){
		StringBuilder sb =new StringBuilder();
		sb.append(getGameName());
		return sb.toString();
	}
}
