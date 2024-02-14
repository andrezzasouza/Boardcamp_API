package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.dtos.GameDTO;
import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.exceptions.CustomerNotFoundException;
import com.boardcamp.api.exceptions.GameNotFoundException;
import com.boardcamp.api.exceptions.GameUnavailableException;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.repositories.RentalRepository;
import com.boardcamp.api.services.RentalService;

@SpringBootTest
class RentalUnitTests {

  @InjectMocks
  private RentalService rentalService;

  @Mock
  private GameRepository gameRepository;

  @Mock
  private CustomerRepository customerRepository;

  @Mock
  private RentalRepository rentalRepository;

  @Test
  void givenGameNotFound_whenCreatingRental_thenThrowsException() {
    Long gameId = 1L;
    Long customerId = 1L;
    Integer daysRented = 2;

    RentalDTO rentalDto = new RentalDTO(customerId, gameId, daysRented);

    doReturn(Optional.empty()).when(gameRepository).findById(any());

    GameNotFoundException exception = assertThrows(GameNotFoundException.class, () -> rentalService.save(rentalDto));

    assertNotNull(exception);
    assertEquals("Nenhum jogo encontrado com o id informado.", exception.getMessage());
    verify(rentalRepository, times(0)).save(any());
    verify(gameRepository, times(1)).findById(any());
  }

  @Test
  void givenGameOutOfStock_whenCreatingRental_thenThrowsException() {
    Long gameId = 1L;
    Long customerId = 1L;
    Integer daysRented = 2;

    RentalDTO rentalDto = new RentalDTO(customerId, gameId, daysRented);
    GameDTO gameDto = new GameDTO("Name", "http://image.com/example.jpg", 0, 1000);
    GameModel gameModel = new GameModel(gameDto);

    doReturn(Optional.of(gameModel)).when(gameRepository).findById(any());

    GameUnavailableException exception = assertThrows(GameUnavailableException.class,
        () -> rentalService.save(rentalDto));

    assertNotNull(exception);
    assertEquals(
        "Todas as unidades deste jogo estão alugadas no momento. Ele está indisponível.", exception.getMessage());
    verify(rentalRepository, times(0)).save(any());
    verify(gameRepository, times(1)).findById(any());
  }

  @Test
  void givenCustomerNotFound_whenCreatingRental_thenThrowsException() {
    Long gameId = 1L;
    Long customerId = 1L;
    Integer daysRented = 2;

    RentalDTO rentalDto = new RentalDTO(customerId, gameId, daysRented);
    GameDTO gameDto = new GameDTO("Name", "http://image.com/example.jpg", 2, 1000);
    GameModel gameModel = new GameModel(gameDto);

    doReturn(Optional.of(gameModel)).when(gameRepository).findById(any());
    doReturn(Optional.empty()).when(customerRepository).findById(any());

    CustomerNotFoundException exception = assertThrows(
        CustomerNotFoundException.class, () -> rentalService.save(rentalDto));

    assertNotNull(exception);
    assertEquals("Nenhum cliente encontrado com o id informado.", exception.getMessage());
    verify(rentalRepository, times(0)).save(any());
    verify(customerRepository, times(1)).findById(any());
  }

}
