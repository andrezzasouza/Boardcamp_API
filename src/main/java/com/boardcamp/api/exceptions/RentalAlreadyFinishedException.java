package com.boardcamp.api.exceptions;

public class RentalAlreadyFinishedException extends RuntimeException {
  public RentalAlreadyFinishedException(String message) {
    super(message);
  }
}
