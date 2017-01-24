package com.hlorka.service.impl;

import com.hlorka.domain.Game;
import com.hlorka.domain.GameConfig;
import com.hlorka.domain.GameType;
import com.hlorka.domain.User;
import com.hlorka.service.EventHandler;
import com.hlorka.service.GameContext;
import com.hlorka.service.GameManager;

/**
 * Created by lbilenki on 1/24/2017.
 */
public class ClassicGameManagerImpl implements GameManager {
    private final GameContext context;
    private final Game game;

    public ClassicGameManagerImpl(GameContext context, User user, GameConfig gameConfig) {
        this.context = context;
        game = new Game(context.getGameIdGenerator().next(), GameType.Classic, generateGameName(user, gameConfig), user);
        context.getEventHandler().onGameCreated(game);
        joinGame(user); // the host automatically joins the game
    }

    @Override
    public Game joinGame(User user) {
        if (!game.addPlayer(user)) {
            throw new IllegalArgumentException(String.format("%s already joined %s", user.getLogin(), game.getName()));
        }

        eventHandler().onGameJoined(game);
        return game;
    }

    @Override
    public Game getGame() {
        return game;
    }

    private String generateGameName(User user, GameConfig gameConfig) {
        return String.format("%s's game", user.getLogin());
    }

    private EventHandler eventHandler() {
        return context.getEventHandler();
    }
}
