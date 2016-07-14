package sk.tsystems.gamestudio.entity;

public class Player {

	private int identPlayer;
	private String playerName;
	
	public Player(int identPlayer, String playerName) {

		this.identPlayer = identPlayer;
		this.playerName = playerName;
		
	}

	public int getIdentPlayer() {
		return identPlayer;
	}

	public void setIdentPlayer(int identPlayer) {
		this.identPlayer = identPlayer;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
}
