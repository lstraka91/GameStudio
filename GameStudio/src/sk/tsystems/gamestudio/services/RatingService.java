package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Rating;



public interface RatingService {

	void add(Rating rating);
	List<Rating> findRatingForGame(String game);
}
