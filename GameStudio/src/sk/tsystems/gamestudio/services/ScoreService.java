package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Skore;
import sk.tsystems.gamestudio.exceptions.ScoreException;

public interface ScoreService {
void add(Skore score) throws ScoreException;
List <Skore> getScoreforGame(String game) throws ScoreException;
}
