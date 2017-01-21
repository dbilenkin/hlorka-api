package com.hlorka.service;


import com.hlorka.domain.User;
import com.hlorka.domain.Game;

import java.util.List;

/**
 * Created by lbilenki on 1/12/2017.
 */
public interface GameService {

    Game createGame(User user);
    Game joinGame(int gameId, User user);
    Game getGame(int gameId);
    void deleteGame(int gameId);

    List<Game> getGames();
}
