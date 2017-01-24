package com.hlorka.service.impl;

import com.hlorka.domain.Game;
import com.hlorka.domain.GameConfig;
import com.hlorka.domain.GameType;
import com.hlorka.domain.User;
import com.hlorka.service.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lbilenki on 1/12/2017.
 */
@Service
public class GameServiceImpl implements GameService, GameContext {

    private final IntSequence gameIdSeq = new IntSequence();
    private final Map<Integer, GameManager> gameManagers = new HashMap<>();
    private final GameManagerFactory gameManagerFactory = new GameManagerFactory();

    @Inject
    private EventHandler eventHandler;

    @Override
    public GameManager createGame(User user, GameConfig gameConfig) {
        GameManager gameManager = gameManagerFactory.create(user, gameConfig);
        gameManagers.put(gameManager.getGame().getId(), gameManager);
        return gameManager;
    }

    @Override
    public List<Game> getGames() {
        return gameManagers.values().stream().map(GameManager::getGame).collect(Collectors.toList());
    }

    @Override
    public void deleteGame(int gameId) {
        GameManager gameManager = gameManagers.remove(gameId);
        eventHandler.onGameDeleted(gameManager.getGame());
    }

    @Override
    public GameManager getGameManager(int gameId) {
        return gameManagers.get(gameId);
    }

    @Override
    public IntSequence getGameIdGenerator() {
        return gameIdSeq;
    }

    @Override
    public EventHandler getEventHandler() {
        return eventHandler;
    }

    private class GameManagerFactory {
        public GameManager create(User user, GameConfig gameConfig) {
            if (gameConfig.getGameType() == GameType.Classic) {
                return new ClassicGameManagerImpl(GameServiceImpl.this, user, gameConfig);
            }

            throw new IllegalArgumentException(String.format("Unsupported game type: %s", gameConfig.getGameType()));
        }
    }
}
