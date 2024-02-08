package com.boardcamp.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.services.RentalService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
@RequestMapping(path = "/rentals")
public class RentalController {
  final RentalService rentalService;

  RentalController(RentalService rentalService) {
    this.rentalService = rentalService;
  }

  @GetMapping
  public ResponseEntity<List<RentalModel>> getAllRentals() {
    List<RentalModel> rentals = rentalService.getAllRentals();

    return ResponseEntity.status(HttpStatus.OK).body(rentals);
  }

  @PostMapping
  public ResponseEntity<Object> addNewRental(@RequestBody @Valid RentalDTO body) {
    Optional<Object> rental = rentalService.save(body);

    if (!rental.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário ou jogo não encontrado com os ids enviados.");
    }

    if (rental.get().equals("Jogo indisponível.")) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Jogo indisponível.");
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(rental.get());
  }

  @PutMapping("/{id}/return")
  public ResponseEntity<Object> returnGame(@PathVariable Long id) {
    Optional<Object> rental = rentalService.returnGame(id);

    if (!rental.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum aluguel encontrado com o id informado.");
    }

    if (rental.get().equals("Aluguel já finalizado.")) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Essa aluguel já foi finalizado antes e não pode ser finalizado novamente.");
    }

    return ResponseEntity.status(HttpStatus.OK).body(rental.get());
  }
}
