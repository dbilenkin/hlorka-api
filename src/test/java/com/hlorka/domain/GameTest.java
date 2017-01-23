package com.hlorka.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by lbilenki on 1/23/2017.
 */
public class GameTest {
    @Test
    public void test() throws Exception {
        User one = new User(1, "one");
        User two = new User(2, "two");
        User three = new User(3, "three");

        Game game = new Game(1, one);
        game.addPlayer(two);
        game.addPlayer(three);

        ObjectMapper mapper = new ObjectMapper();
        StringWriter out = new StringWriter();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, game);

        out.close();

        System.out.println(out.getBuffer().toString());
    }

}