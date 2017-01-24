package com.hlorka.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlorka.utils.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Created by lbilenki on 1/23/2017.
 */
public class GameTest {

    private User one;
    private User two;
    private User three;
    private Game game;
    private User four;

    @Before
    public void setUp() {
        one = new User(1, "one");
        two = new User(2, "two");
        three = new User(3, "three");

        game = new Game(1, GameType.Classic, GameState.Setup, "one's game", one);
        game.addPlayer(one);
        game.addPlayer(two);
        game.addPlayer(three);
        four = new User(4, "four");
    }

    @Test
    public void testGetType() throws Exception {
        assertThat(game.getGameType(), is(GameType.Classic));
    }

    @Test
    public void testGetState() throws Exception {
        assertThat(game.getGameState(), is(GameState.Setup));
    }

    @Test
    public void testGetName() throws Exception {
        assertThat(game.getName(), is("one's game"));
    }

    @Test
    public void testGetHost() throws Exception {
        assertThat(game.getHost(), is(sameInstance(one)));
    }

    @Test
    public void testGetPlayers() throws Exception {
        assertThat(game.getPlayers().stream().map(Player::getUser).collect(Collectors.toSet()),
                is(CollectionUtils.asSet(one, two, three)));
    }

    @Test
    public void testAddPlayer() throws Exception {
        assertThat(game.addPlayer(three), is(false));
        assertThat(game.addPlayer(four), is(true));
        assertThat(game.getPlayers().size(), is(4));
    }

    @Test
    public void testGetPlayer() throws Exception {
        assertThat(game.getPlayer(one).get().getUser(), is(sameInstance(one)));
        assertThat(game.getPlayer(four).isPresent(), is(false));
    }

//    @Ignore
    @Test
    public void testJSon() throws Exception {
        User one = new User(1, "one");
        User two = new User(2, "two");
        User three = new User(3, "three");

        Game game = new Game(1, GameType.Classic, GameState.Setup, "one's game", one);
        game.addPlayer(one);
        game.addPlayer(two);
        game.addPlayer(three);

        game.getPlayer(two).ifPresent(p -> p.setReadyToStart(true));

        ObjectMapper mapper = new ObjectMapper();
        StringWriter out = new StringWriter();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, game);

        out.close();

        String jsonString = out.getBuffer().toString();
        System.out.println(jsonString);
    }

}