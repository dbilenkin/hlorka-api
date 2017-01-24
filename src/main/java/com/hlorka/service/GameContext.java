package com.hlorka.service;

/**
 * Created by lbilenki on 1/24/2017.
 */
public interface GameContext {
    EventHandler getEventHandler();
    IntSequence getGameIdGenerator();
}
