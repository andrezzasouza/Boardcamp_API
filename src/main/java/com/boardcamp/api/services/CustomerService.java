package com.boardcamp.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;

@Service
public class CustomerService {
  CustomerRepository customerRepository;
  
  CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Optional<CustomerModel> save(CustomerDTO dto) {
    CustomerModel customer = new CustomerModel(dto);
    boolean customerExists = customerRepository.existsByCpf(customer.getCpf());

    if (customerExists) {
      return Optional.empty();
    }

    return Optional.of(customerRepository.save(customer));
  }

  public Optional<CustomerModel> findCustomerById(Long id) {
    Optional<CustomerModel> customer = customerRepository.findById(id);

    if (!customer.isPresent()) {
      return Optional.empty();
    }

    return customer;
  }
}
