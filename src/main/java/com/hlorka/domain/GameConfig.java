package com.hlorka.domain;

/**
 * Created by lbilenki on 1/24/2017.
 */
public class GameConfig {
    private final GameType gameType;

    public GameConfig(GameType gameType) {
        this.gameType = gameType;
    }

    public GameType getGameType() {
        return gameType;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "gameType=" + gameType +
                '}';
    }
}
