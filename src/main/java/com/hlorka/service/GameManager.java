package com.hlorka.service;

import com.hlorka.domain.Game;
import com.hlorka.domain.User;

/**
 * Created by lbilenki on 1/24/2017.
 */
public interface GameManager {
    Game joinGame(User user);
    Game getGame();
}
