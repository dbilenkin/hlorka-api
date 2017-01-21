package com.hlorka.service.impl;

import com.hlorka.domain.User;
import com.hlorka.domain.Game;
import com.hlorka.service.IntSequence;
import com.hlorka.service.EventHandler;
import com.hlorka.service.GameService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lbilenki on 1/12/2017.
 */
@Service
public class GameServiceImpl implements GameService {

    private final IntSequence gameIdSeq = new IntSequence();

    private final Map<Integer, Game> games = new HashMap<>();

    @Inject
    private EventHandler eventHandler;

    @Override
    public Game createGame(User user) {
        Game game = new Game(gameIdSeq.next(), user);
        games.put(game.getId(), game);

        eventHandler.onGameCreated(game);
        joinGame(game.getId(), user); // the host automatically joins the game
        return game;
    }

    @Override
    public Game joinGame(int gameId, User user) {
        Game game = games.get(gameId);
        game.addPlayer(user);

        eventHandler.onGameJoined(game);
        return game;
    }

    @Override
    public List<Game> getGames() {
        return new ArrayList<>(games.values());
    }

    @Override
    public Game getGame(int gameId) {
        return games.get(gameId);
    }

    @Override
    public void deleteGame(int gameId) {
        Game game = games.get(gameId);
        games.remove(gameId);
        eventHandler.onGameDeleted(game);
    }

}
