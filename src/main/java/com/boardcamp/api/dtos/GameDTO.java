package com.boardcamp.api.dtos;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GameDTO {
  @NotBlank(message = "O nome do jogo deve estar presente e não pode ser nulo nem uma string vazia")
  private String name;

  @NotBlank(message = "A imagem do jogo é obrigatória.")
  @URL(message = "URL inválida.")
  @Pattern(regexp = ".*\\.(jpg|jpeg|png|webm|gif)$", message = "A URL deve ser de uma imagem.")
  private String image;

  @NotNull(message = "A quantidade de jogos não pode estar em branco.")
  @Positive(message = "A quantidade de jogos deve ser um número positivo maior que zero.")
  @Digits(fraction = 0, integer = Integer.MAX_VALUE, message = "A quantidade de jogos deve ser um número inteiro menor que 2.147.483.647.")
  private Integer stockTotal;

  @NotNull(message = "O valor do aluguel por dia não pode estar em branco.")
  @Positive(message = "O valor do aluguel por dia deve ser um número positivo maior que zero.")
  @Digits(fraction = 0, integer = Integer.MAX_VALUE, message = "O valor do aluguel por dia deve ser um número inteiro (está em centavos: R$ 10,00 = 1000) menor que 2.147.483.647.")
  private Integer pricePerDay;
}
