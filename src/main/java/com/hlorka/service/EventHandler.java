package com.hlorka.service;

import com.hlorka.domain.Game;
import com.hlorka.domain.User;

/**
 * Created by lbilenki on 1/13/2017.
 */
public interface EventHandler {

    void onGameCreated(Game game);
    void onGameJoined(Game game, User user);
    void onGameLeft(Game game, User user);
    void onGameDeleted(Game game);
}
