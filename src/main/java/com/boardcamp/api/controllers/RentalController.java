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
    Optional<RentalModel> rental = rentalService.save(body);

    if (!rental.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário ou jogo não encontrado com os ids enviados.");
    }

    return ResponseEntity.status(HttpStatus.OK).body(rental.get());
  }
}
