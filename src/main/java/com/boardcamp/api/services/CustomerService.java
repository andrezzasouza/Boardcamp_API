package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.exceptions.CustomerCpfConflictException;
import com.boardcamp.api.exceptions.CustomerNotFoundException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

@Service
public class CustomerService {
  CustomerRepository customerRepository;

  CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public CustomerModel save(CustomerDTO dto) {
    CustomerModel customer = new CustomerModel(dto);
    boolean customerExists = customerRepository.existsByCpf(customer.getCpf());

    if (customerExists) {
      throw new CustomerCpfConflictException("Este cliente já foi cadastrado antes.");
    }

    return customerRepository.save(customer);
  }

  public CustomerModel findCustomerById(Long id) {
    CustomerModel customer = customerRepository.findById(id).orElseThrow(
        () -> new CustomerNotFoundException("Nenhum cliente encontrado com o id informado."));

    return customer;
  }
}
