package com.hlorka.web.websocket;

import com.hlorka.domain.Game;
import com.hlorka.domain.User;
import com.hlorka.service.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.inject.Inject;
import java.time.format.DateTimeFormatter;

/**
 * Created by dbilenkin on 1/16/17.
 */
@Service
public class GameEventHandler implements EventHandler, ApplicationListener<SessionDisconnectEvent> {

    private static final Logger log = LoggerFactory.getLogger(GameEventHandler.class);

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Inject
    SimpMessageSendingOperations messagingTemplate;

    @Override
    public void onGameCreated(Game game) {
        //messagingTemplate.convertAndSend("/topic/game", game);
    }

    @Override
    public void onGameJoined(Game game, User user) {
        messagingTemplate.convertAndSend("/topic/addGame", game);
    }

    @Override
    public void onGameLeft(Game game, User user) {
        // TODO
    }

    @Override
    public void onGameDeleted(Game game) {
        messagingTemplate.convertAndSend("/topic/deleteGame", game);
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {

    }
}
