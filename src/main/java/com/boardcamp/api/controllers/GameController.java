package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.services.GameService;

import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping(path = "/games")
public class GameController {
  final GameService gameService;

  GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @PostMapping
  public ResponseEntity<Object> addNewGame(@RequestBody @Valid GameDTO body) {
    Optional<GameModel> game = gameService.save(body);

    if (!game.isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Este jogo já foi cadastrado antes.");
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(game.get());
  }
}
