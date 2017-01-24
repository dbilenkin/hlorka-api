package com.hlorka.service.impl;

import com.hlorka.domain.*;
import com.hlorka.service.EventHandler;
import com.hlorka.service.GameContext;
import com.hlorka.service.GameManager;
import com.hlorka.service.IntSequence;
import com.hlorka.utils.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by lbilenki on 1/24/2017.
 */
public class ClassicGameManagerImplTest {

    private IntSequence gameIdGenerator;
    private GameContext gameContext;
    private User one;
    private User two;
    private User three;

    @Mock
    private EventHandler eventHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        gameIdGenerator = new IntSequence();

        gameContext = new GameContext() {
            @Override
            public EventHandler getEventHandler() {
                return eventHandler;
            }

            @Override
            public IntSequence getGameIdGenerator() {
                return gameIdGenerator;
            }
        };

        one = new User(1, "one");
        two = new User(2, "two");
        three = new User(3, "three");
    }

    @Test
    public void test() throws Exception {
        // create game - one is the host and the automatic participant
        GameManager gameManager = new ClassicGameManagerImpl(gameContext, one, new GameConfig(GameType.Classic));
        Game game = gameManager.getGame();

        verify(eventHandler).onGameCreated(eq(game));
        verify(eventHandler).onGameJoined(eq(game), eq(one));

        assertThat(game.getHost(), sameInstance(one));
        assertThat(game.getName(), is("one's game"));
        assertThat(game.getGameType(), is(GameType.Classic));
        assertThat(game.getPlayers().size(), is(1));
        assertThat(game.getPlayers().iterator().next().getUser(), sameInstance(one));

        // two joins the game
        gameManager.joinGame(two);
        verify(eventHandler).onGameJoined(eq(game), eq(two));
        assertPlayers(game, one, two);

        // two joins the game again
        try {
            gameManager.joinGame(two);
            Assert.fail("Should've thrown IllegalArgumentException");
        } catch (IllegalArgumentException success) {
            assertThat(success.getMessage(), is("two already joined one's game"));
        }

        // two leaves the game
        gameManager.leaveGame(two);
        verify(eventHandler).onGameLeft(eq(game), eq(two));
        assertPlayers(game, one);

        // three joins the game
        gameManager.joinGame(three);
        verify(eventHandler).onGameJoined(eq(game), eq(three));
        assertPlayers(game, one, three);
    }

    private void assertPlayers(Game game, User ... expectedPlayers) {
        assertThat(game.getPlayers().stream().map(Player::getUser).collect(Collectors.toSet()),
                is(CollectionUtils.asSet(expectedPlayers)));
    }

}