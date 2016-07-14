package sk.tsystems.gamestudio.games;

import java.util.ArrayList;
import java.util.List;

import sk.tsystems.gamestudio.entity.Game;

public class Games {

	private List<Game> gamesList;

	public Games() {
		gamesList = new ArrayList<Game>();

		
	}

	public void addGame(Game game) {
		gamesList.add(game);
	}

	public List<Game> getGamesList() {
		return gamesList;
	}

	public Game getGame(int index) {

		if (index < getSize() && index >= 0) {
			return gamesList.get(index);

		}
		return null;
	}

	public int getSize() {
		return gamesList.size();
	
	}

}
