package com.hlorka.service.impl;

import com.hlorka.domain.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Created by lbilenki on 1/22/2017.
 */
public class UserServiceImplTest {
    @Test
    public void testGetUser() throws Exception {
        UserServiceImpl userService = new UserServiceImpl();

        User login1 = userService.getUser("login1");
        User anotherLogin1 = userService.getUser("login1");

        assertThat(login1.getLogin(), is("login1"));
        assertThat(login1, sameInstance(anotherLogin1));

        User login2 = userService.getUser("login2");
        assertThat(login2.getLogin(), is("login2"));
        assertThat(login2, is(not(sameInstance(login1))));
    }

}