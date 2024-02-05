package com.boardcamp.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.services.RentalService;

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
}
