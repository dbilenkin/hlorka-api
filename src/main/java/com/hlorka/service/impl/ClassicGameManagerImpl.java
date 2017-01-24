package com.hlorka.service.impl;

import com.hlorka.domain.*;
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
        game = new Game(context.getGameIdGenerator().next(), GameType.Classic, GameState.Setup, generateGameName(user, gameConfig), user);
        context.getEventHandler().onGameCreated(game);
        joinGame(user); // the host automatically joins the game
    }

    @Override
    public Game joinGame(User user) {
        if (!game.addPlayer(user)) {
            throw new IllegalArgumentException(String.format("%s already joined %s", user.getLogin(), game.getName()));
        }

        eventHandler().onGameJoined(game, user);
        return game;
    }

    @Override
    public Game leaveGame(User user) {
        if (!game.removePlayer(user)) {
            throw new IllegalArgumentException(String.format("%s is not in %s", user.getLogin(), game.getName()));
        }

        if (game.getGameState() != GameState.Setup) {
            throw new IllegalStateException(String.format("%s cannot leave %s. The game is in %s state",
                    user.getLogin(), game.getName(), game.getGameState()));
        }

        eventHandler().onGameLeft(game, user);
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
