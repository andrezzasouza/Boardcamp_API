package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.exceptions.CustomerNotFoundException;
import com.boardcamp.api.exceptions.GameNotFoundException;
import com.boardcamp.api.exceptions.GameUnavailableException;
import com.boardcamp.api.exceptions.RentalAlreadyFinishedException;
import com.boardcamp.api.exceptions.RentalNotFoundException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.repositories.RentalRepository;

@Service
public class RentalService {
  final RentalRepository rentalRepository;
  final GameRepository gameRepository;
  final CustomerRepository customerRepository;

  RentalService(
      RentalRepository rentalRepository,
      GameRepository gameRepository,
      CustomerRepository customerRepository) {
    this.rentalRepository = rentalRepository;
    this.gameRepository = gameRepository;
    this.customerRepository = customerRepository;
  }

  public List<RentalModel> getAllRentals() {
    return rentalRepository.findAll();
  }

  public RentalModel save(RentalDTO dto) {
    GameModel existingGame = gameRepository.findById((long) dto.getGameId()).orElseThrow(
        () -> new GameNotFoundException("Nenhum jogo encontrado com o id informado."));

    if (existingGame.getStockTotal() <= 0) {
      throw new GameUnavailableException(
          "Todas as unidades deste jogo estão alugadas no momento. Ele está indisponível.");
    }

    CustomerModel existingCustomer = customerRepository.findById((long) dto.getCustomerId()).orElseThrow(
        () -> new CustomerNotFoundException("Nenhum cliente encontrado com o id informado."));

    RentalModel newRentalInfo = new RentalModel(dto, existingCustomer, existingGame);
    RentalModel createdRental = rentalRepository.save(newRentalInfo);
    GameModel updatedGameInfo = new GameModel(existingGame);

    updatedGameInfo.setId(existingGame.getId());
    updatedGameInfo.setStockTotal(existingGame.getStockTotal() - 1);
    gameRepository.save(updatedGameInfo);

    return createdRental;
  }

  public RentalModel finishRental(Long id) {
    RentalModel rental = rentalRepository.findById(id).orElseThrow(
        () -> new RentalNotFoundException("Nenhum aluguel encontrado com o id informado."));

    if (rental.getReturnDate() != null) {
      throw new RentalAlreadyFinishedException(
          "Esse aluguel já foi finalizado antes e não pode ser finalizado novamente.");
    }

    RentalModel updatedRentalInfo = new RentalModel(rental);
    updatedRentalInfo.setId(id);

    RentalModel updatedRental = rentalRepository.save(updatedRentalInfo);

    GameModel updatedGameInfo = new GameModel(rental.getGame());

    updatedGameInfo.setId(rental.getGame().getId());
    updatedGameInfo.setStockTotal(rental.getGame().getStockTotal() + 1);
    gameRepository.save(updatedGameInfo);

    return updatedRental;
  }
}
