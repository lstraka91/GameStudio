package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Skore;
import sk.tsystems.gamestudio.exceptions.ScoreException;

public interface SkoreService {
	void add(Skore skore) throws ScoreException;
	List<Skore> findSkoreForGame(String game) throws ScoreException;
}
