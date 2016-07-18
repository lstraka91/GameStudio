package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Rejting;
import sk.tsystems.gamestudio.exceptions.RatingException;

public interface RejtingService {
	void add(Rejting rejting) throws RatingException;
	List<Rejting> findRejtingForGame(String game) throws RatingException;
}
