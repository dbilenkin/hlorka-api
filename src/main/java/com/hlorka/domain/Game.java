package com.hlorka.domain;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A Game.
 */
public class Game extends ObjectWithId {
    private final User host;
    private final Set<Player> players;

    public Game(int id, User host) {
        super(id);
        this.host = host;
        this.players = new HashSet<>();
    }

    public String getName() {
        return String.format("%s's game", host.getLogin());
    }

    public User getHost() {
        return host;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void addPlayer(User user) {
        this.players.add(new Player(user));
    }

    public Optional<Player> getPlayer(User user) {
        return players.stream().filter(p -> p.getUser().equals(user)).findFirst();
    }

    @Override
    public String toString() {
        return String.format("id=%d, host=%s}", id, host);
    }

}
