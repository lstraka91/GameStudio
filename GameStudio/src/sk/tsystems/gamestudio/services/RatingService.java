package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Rejting;
import sk.tsystems.gamestudio.exceptions.RatingException;



public interface RatingService {

	void add(Rejting rating) throws RatingException;
	List<Rejting> findRatingForGame(String game) throws RatingException;
}
