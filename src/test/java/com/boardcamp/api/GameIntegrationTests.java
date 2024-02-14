package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.repositories.GameRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class GameIntegrationTests {
  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private GameRepository gameRepository;

  @AfterEach
  public void cleanUpDatabase() {
    gameRepository.deleteAll();
  }

  @Test
  void givenValidGame_whenCreatingRental_thenCreatesRental() {
    GameDTO game = new GameDTO("Game", "http://image.com/example.jpg", 10, 1000);

    HttpEntity<GameDTO> body = new HttpEntity<>(game);

    ResponseEntity<GameModel> response = restTemplate.exchange("/games", HttpMethod.POST, body, GameModel.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(1, gameRepository.count());

  }
}
