package com.boardcamp.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.RentalDTO;
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

  public Optional<Object> save(RentalDTO dto) {
    Optional<GameModel> existingGame = gameRepository.findById((long) dto.getGameId());
    Optional<CustomerModel> existingCustomer = customerRepository.findById((long) dto.getCustomerId());

    if (!existingGame.isPresent() || !existingCustomer.isPresent()) {
      return Optional.empty();
    }

    if (existingGame.get().getStockTotal() <= 0) {
      return Optional.of("Jogo indisponível.");
    }

    RentalModel newRental = new RentalModel(dto, existingCustomer.get(), existingGame.get());
    RentalModel createdRental = rentalRepository.save(newRental);
    GameModel updatedGameInfo = new GameModel(existingGame.get());

    updatedGameInfo.setId(existingGame.get().getId());
    updatedGameInfo.setStockTotal(existingGame.get().getStockTotal() - 1);
    gameRepository.save(updatedGameInfo);

    return Optional.of(createdRental);
  }
}
