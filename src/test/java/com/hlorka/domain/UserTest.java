package com.hlorka.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lbilenki on 1/23/2017.
 */
public class UserTest {
    @Test
    public void testGetLogin() throws Exception {
        assertThat(new User(1, "one").getLogin(), is("one"));
    }

    @Test
    public void testToString() throws Exception {
        assertThat(new User(1, "one").toString(), is("id=1, login='one'"));
    }

}