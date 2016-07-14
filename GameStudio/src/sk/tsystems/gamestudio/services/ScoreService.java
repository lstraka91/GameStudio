package sk.tsystems.gamestudio.services;

import java.util.List;

import sk.tsystems.gamestudio.entity.Score;

public interface ScoreService {
void add(Score score);
List <Score> getScoreforGame(String game);
}
