package com.hlorka.domain;

/**
 * Created by dbilenkin on 1/21/17.
 */
public class User extends ObjectWithId{

    private String login;

    public User(int id, String login) {
        super(id);
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
