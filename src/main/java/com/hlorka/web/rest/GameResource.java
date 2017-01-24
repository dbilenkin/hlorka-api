package com.hlorka.web.rest;

import com.hlorka.domain.GameConfig;
import com.hlorka.domain.GameType;
import com.hlorka.domain.User;
import com.hlorka.domain.Game;
import com.hlorka.service.GameManager;
import com.hlorka.service.UserService;
import com.hlorka.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Game.
 */
@RestController
@RequestMapping("/api")
public class GameResource {

    private final Logger log = LoggerFactory.getLogger(GameResource.class);

    @Inject
    private GameService gameService;

    @Inject
    private UserService userService;

    @Inject
    private SimpMessageSendingOperations messagingTemplate;

    /**
     * POST  /games : Create a new game.
     *
     * @return the ResponseEntity with status 201 (Created) and with body the new game, or with status 400 (Bad Request) if the game has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/games")
    public ResponseEntity<Game> createGame(@RequestBody String login) throws URISyntaxException {
        log.debug("REST request to create Game.");

        User user = userService.getUser(login);
        GameManager gameManager = gameService.createGame(user, new GameConfig(GameType.Classic));
        Game game = gameManager.getGame();

        return ResponseEntity.created(new URI("/api/games/" + game.getId()))
            .body(game);
    }


    /**
     * PUT  /games/:id/join : Join an existing game.
     *
     * @param id the id of the game to join
     * @return the ResponseEntity with status 200 (OK) and with body the joined game,
     * or with status 400 (Bad Request) if the game is not valid,
     * or with status 500 (Internal Server Error) if the game couldnt be joined
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/games/{id}/join")
    public ResponseEntity<Game> joinGame(@PathVariable int id, @RequestBody String login) throws URISyntaxException {
        log.debug("REST request to join Game : {}", id);
        User user = userService.getUser(login);
        Game game = gameService.getGameManager(id).joinGame(user);
        return ResponseEntity.ok()
            .body(game);
    }

    /**
     * GET  /games : get all the games.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of games in body
     */
    @GetMapping("/games")
    public List<Game> getAllGames() {
        log.debug("REST request to get all Games");
        return gameService.getGames();
    }

    /**
     * GET  /games/:id : get the "id" game.
     *
     * @param id the id of the game to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the game, or with status 404 (Not Found)
     */
    @GetMapping("/games/{id}")
    public ResponseEntity<Game> getGame(@PathVariable int id) {
        log.debug("REST request to get Game : {}", id);
        GameManager gameManager = gameService.getGameManager(id);
        return Optional.ofNullable(gameManager)
            .map(result -> new ResponseEntity<>(
                result.getGame(),
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /games/:id : delete the "id" game.
     *
     * @param id the id of the game to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/games/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable int id) {
        log.debug("REST request to delete Game : {}", id);
        gameService.deleteGame(id);
        return ResponseEntity.ok().build();
    }

}
