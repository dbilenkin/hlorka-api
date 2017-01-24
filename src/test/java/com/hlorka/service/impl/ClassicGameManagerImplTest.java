package com.hlorka.service.impl;

import com.hlorka.domain.*;
import com.hlorka.service.EventHandler;
import com.hlorka.service.GameContext;
import com.hlorka.service.GameManager;
import com.hlorka.service.IntSequence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Created by lbilenki on 1/24/2017.
 */
public class ClassicGameManagerImplTest {

    private TestEventHandler eventHandler;
    private IntSequence gameIdGenerator;
    private GameContext gameContext;
    private User one;
    private User two;
    private User three;

    @Before
    public void setUp() {
        eventHandler = new TestEventHandler();
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

        assertThat(game.getHost(), sameInstance(one));
        assertThat(game.getGameType(), is(GameType.Classic));
        assertThat(game.getPlayers().size(), is(1));
        assertThat(game.getPlayers().iterator().next().getUser(), sameInstance(one));

        // two joins the game
        gameManager.joinGame(two);

        Iterator<Player> iter = game.getPlayers().iterator();

        assertThat(iter.next().getUser(), sameInstance(one));
        assertThat(iter.next().getUser(), sameInstance(two));
        assertThat(iter.hasNext(), is(false));

        // two joins the game again
        try {
            gameManager.joinGame(two);
            Assert.fail("Should've thrown IllegalArgumentException");
        } catch (IllegalArgumentException success) {
            assertThat(success.getMessage(), is("two already joined one's game"));
        }
    }

    @Test
    public void testGetGame() throws Exception {

    }

    private static class TestEventHandler implements EventHandler {
        private final List<Event> events = new ArrayList<>();

        @Override
        public void onGameCreated(Game game) {
            events.add(new Event(EventType.Created, game));
        }

        @Override
        public void onGameJoined(Game game) {
            events.add(new Event(EventType.Joined, game));
        }

        @Override
        public void onGameDeleted(Game game) {
            events.add(new Event(EventType.Deleted, game));
        }

        public List<Event> getEvents() {
            return events;
        }

        public enum EventType {Created, Joined, Deleted}

        public static class Event {
            private final EventType eventType;
            private final Game game;

            public Event(EventType eventType, Game game) {
                this.eventType = eventType;
                this.game = game;
            }

            public EventType getEventType() {
                return eventType;
            }

            public Game getGame() {
                return game;
            }
        }
    }

}