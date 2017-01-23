package com.hlorka.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by lbilenki on 1/23/2017.
 */
public class Team extends ObjectWithId {
    private final int maxPlayerCount;
    private final Set<Player> players = new LinkedHashSet<>();

    public Team(int id, int maxPlayerCount) {
        super(id);
        this.maxPlayerCount = maxPlayerCount;
    }

    public int getMaxPlayerCount() {
        return maxPlayerCount;
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public boolean teamIsFull() {
        return players.size() == maxPlayerCount;
    }

    public void addPlayer(Player player) {

    }
}
