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
    public String toString() {
        return user.toString() +
                ", readyToStart=" + readyToStart;
    }
}
