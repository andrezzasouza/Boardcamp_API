package com.boardcamp.api.exceptions;

public class GameUnavailableException extends RuntimeException {
  public GameUnavailableException(String message) {
    super(message);
  }
}
