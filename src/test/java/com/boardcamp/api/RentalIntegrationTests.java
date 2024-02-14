package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.repositories.RentalRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class RentalIntegrationTests {
  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private GameRepository gameRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private RentalRepository rentalRepository;

  @AfterEach
  public void cleanUpDatabase() {
    rentalRepository.deleteAll();
    gameRepository.deleteAll();
    customerRepository.deleteAll();
  }

  @Test
  void givenValidGameStockAndCustomer_whenCreatingRental_thenCreatesRental() {
    GameDTO gameDto = new GameDTO("Game", "Description", 10, 1000);
    GameModel game = new GameModel(gameDto);
    GameModel createdGame = gameRepository.save(game);

    CustomerDTO customerDto = new CustomerDTO("Customer", "01236547890");
    CustomerModel customer = new CustomerModel(customerDto);
    CustomerModel createdCustomer = customerRepository.save(customer);

    RentalDTO rental = new RentalDTO(createdCustomer.getId(), createdGame.getId(), 2);
    HttpEntity<RentalDTO> body = new HttpEntity<>(rental);

    ResponseEntity<RentalModel> response = restTemplate.exchange("/rentals", HttpMethod.POST, body, RentalModel.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(1, rentalRepository.count());
  }
}
