package com.boardcamp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {

  @ExceptionHandler({ CustomerCpfConflictException.class })
  public ResponseEntity<String> handleCustomerConflict(CustomerCpfConflictException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
  }

  @ExceptionHandler({ CustomerNotFoundException.class })
  public ResponseEntity<String> handleCustomerNotFound(CustomerNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler({ GameNameConflictException.class })
  public ResponseEntity<String> handleGameConflict(GameNameConflictException exception) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
  }

  @ExceptionHandler({ GameNotFoundException.class })
  public ResponseEntity<String> handleGameNotFound(GameNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler({ GameUnavailableException.class })
  public ResponseEntity<String> handleGameUnavailable(GameUnavailableException exception) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }

  @ExceptionHandler({ RentalAlreadyFinishedException.class })
  public ResponseEntity<String> handleRentalAlreadyFinished(RentalAlreadyFinishedException exception) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
  }

  @ExceptionHandler({ RentalNotFoundException.class })
  public ResponseEntity<String> handleRentalNotFound(RentalNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

}
