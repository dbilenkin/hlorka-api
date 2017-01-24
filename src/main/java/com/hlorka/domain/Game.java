package com.hlorka.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A Game.
 */
public class Game extends ObjectWithId {
    private final GameType gameType;
    private final String name;
    private final User host;
    private final Set<Player> players;

    public Game(int id, GameType gameType, String name, User host) {
        super(id);
        this.gameType = gameType;
        this.name = name;
        this.host = host;
        this.players = new LinkedHashSet<>();
    }

    public GameType getGameType() {
        return gameType;
    }

    public String getName() {
        return name;
    }

    public User getHost() {
        return host;
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public boolean addPlayer(User user) {
        return this.players.add(new Player(user));
    }

    public Optional<Player> getPlayer(User user) {
        return players.stream().filter(p -> p.getUser().equals(user)).findFirst();
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameType=" + gameType +
                ", name='" + name + '\'' +
                ", host=" + host +
                '}';
    }
}
