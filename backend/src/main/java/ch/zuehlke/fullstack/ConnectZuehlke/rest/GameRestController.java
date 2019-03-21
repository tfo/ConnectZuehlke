package ch.zuehlke.fullstack.ConnectZuehlke.rest;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Game;
import ch.zuehlke.fullstack.ConnectZuehlke.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

@RestController
public class GameRestController {

    private final GameService service;

    @Autowired
    public GameRestController(GameService service) {
        this.service = service;
    }

    @GetMapping("/api/game")
    public Game createGame() {
        return this.service.createGame(15);
    }
}
