package com.hlorka.service;

import com.hlorka.domain.Game;

/**
 * Created by lbilenki on 1/13/2017.
 */
public interface EventHandler {

    void onGameCreated(Game game);
    void onGameJoined(Game game);
    void onGameDeleted(Game game);
}
