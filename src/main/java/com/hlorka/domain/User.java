package com.hlorka.domain;

/**
 * Created by dbilenkin on 1/21/17.
 */
public class User extends ObjectWithId {
    private final String login;

    public User(int id, String login) {
        super(id);
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return String.format("id=%d, login='%s'", id, login);
    }




}
