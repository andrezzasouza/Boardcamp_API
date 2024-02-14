package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
  @NotBlank(message = "O nome do cliente deve estar presente e não pode ser nulo nem uma string vazia.")
  private String name;

  @NotBlank(message = "O CPF deve estar presente e não pode ser nulo nem uma string vazia.")
  @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos, todos números.")
  private String cpf;
}
