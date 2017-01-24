package com.hlorka.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Created by lbilenki on 1/23/2017.
 */
public class PlayerTest {
    @Test
    public void test() throws Exception {
        User user = new User(1, "one");
        Player player = new Player(user);

        assertThat(player.getUser(), sameInstance(user));
        assertThat(player.isReadyToStart(), is(false));
        assertThat(player.toString(), is("Player{user=User{id=1, login='one'}, readyToStart=false}"));

        player.setReadyToStart(true);
        assertThat(player.isReadyToStart(), is(true));
    }

}