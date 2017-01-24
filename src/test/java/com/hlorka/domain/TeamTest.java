package com.hlorka.domain;

import com.hlorka.utils.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lbilenki on 1/23/2017.
 */
public class TeamTest {
    private User one;
    private User two;
    private User three;
    private Team team;

    @Before
    public void setUp() {
        one = new User(1, "one");
        two = new User(2, "two");
        three = new User(3, "three");

        team = new Team(1, "team", 2);
        team.addMember(one);
        team.addMember(two);
    }

    @Test
    public void testGetName() throws Exception {
        assertThat(team.getName(), is("team"));
    }

    @Test
    public void testGetMaxPlayerCount() throws Exception {
        assertThat(team.getTeamSize(), is(2));
    }

    @Test
    public void testGetMembers() throws Exception {
        assertThat(team.getMembers(), is(CollectionUtils.asSet(one, two)));
    }

    @Test
    public void testIsMember() throws Exception {
        assertThat(team.isMember(one), is(true));
        assertThat(team.isMember(three), is(false));
    }

    @Test
    public void testTeamIsFull() throws Exception {
        assertThat(team.isFull(), is(true));
    }

    @Test
    public void testAddMember() throws Exception {
        Team team2 = new Team(2, "team2", 2);
        assertThat(team2.isFull(), is(false));
        team2.addMember(one);
        assertThat(team2.isFull(), is(false));
        team2.addMember(two);
        assertThat(team2.isFull(), is(true));
    }

    @Test
    public void testRemoveMember() throws Exception {
        assertThat(team.getMembers(), is(CollectionUtils.asSet(one, two)));

        assertThat(team.removeMember(one), is(true));
        assertThat(team.getMembers(), is(CollectionUtils.asSet(two)));

        assertThat(team.removeMember(one), is(false));

        assertThat(team.removeMember(two), is(true));
        assertThat(team.getMembers(), is(CollectionUtils.asSet()));
    }

}