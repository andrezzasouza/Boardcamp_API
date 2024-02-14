package com.boardcamp.api.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalDTO {
  @NotNull(message = "O id do cliente deve estar presente e não pode ser nulo.")
  @Positive(message = "O id do cliente deve ser um número positivo maior que zero.")
  @Digits(fraction = 0, integer = Integer.MAX_VALUE, message = "O id do cliente deve ser um número inteiro menor que 2.147.483.647.")
  private Long customerId;

  @NotNull(message = "O id do jogo deve estar presente e não pode ser nulo.")
  @Positive(message = "O id do jogo deve ser um número positivo maior que zero.")
  @Digits(fraction = 0, integer = Integer.MAX_VALUE, message = "O id do jogo deve ser um número inteiro menor que 2.147.483.647.")
  private Long gameId;

  @NotNull(message = "A quantidade de dias de aluguel deve estar presente e não pode ser nula.")
  @Positive(message = "A quantidade de dias de aluguel deve ser um número positivo maior que zero.")
  @Digits(fraction = 0, integer = Integer.MAX_VALUE, message = "A quantidade de dias de aluguel deve ser um número inteiro menor que 2.147.483.647.")
  private Integer daysRented;
}
