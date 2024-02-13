package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.exceptions.GameNameConflictException;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;

@Service
public class GameService {
  final GameRepository gameRepository;

  GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public GameModel save(GameDTO dto) {
    boolean gameExists = gameRepository.existsByName(dto.getName());

    if (gameExists) {
      throw new GameNameConflictException("Este jogo já foi cadastrado antes.");
    }

    GameModel game = new GameModel(dto);

    return gameRepository.save(game);
  }

  public List<GameModel> findAll() {
    return gameRepository.findAll();
  }
}
