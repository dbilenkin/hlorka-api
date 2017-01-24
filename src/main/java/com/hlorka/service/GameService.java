package com.hlorka.service;


import com.hlorka.domain.GameConfig;
import com.hlorka.domain.User;
import com.hlorka.domain.Game;

import java.util.List;

/**
 * Created by lbilenki on 1/12/2017.
 */
public interface GameService {

    GameManager createGame(User user, GameConfig gameConfig);
    GameManager getGameManager(int gameId);

    void deleteGame(int gameId);

    List<Game> getGames();
}
