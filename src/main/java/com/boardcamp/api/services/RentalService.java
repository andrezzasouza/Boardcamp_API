package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.RentalRepository;

@Service
public class RentalService {
  final RentalRepository rentalRepository;

  RentalService(RentalRepository rentalRepository) {
    this.rentalRepository = rentalRepository;
  }

  public List<RentalModel> getAllRentals() {
    return rentalRepository.findAll();
  }
}
