package com.hlorka.domain;

/**
 * Created by lbilenki on 1/23/2017.
 */
public class Player {
    private final User user;
    private boolean readyToStart;

    public Player(User user) {
        this.user = user;
        this.readyToStart = false;
    }

    public User getUser() {
        return user;
    }

    public boolean isReadyToStart() {
        return readyToStart;
    }

    public void setReadyToStart(boolean readyToStart) {
        this.readyToStart = readyToStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return user != null ? user.equals(player.user) : player.user == null;
    }

    @Override
    public int hashCode() {
        return user != null ? user.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Player{" +
                "user=" + user +
                ", readyToStart=" + readyToStart +
                '}';
    }
}
