package dev.felipeazsantos.hexagonal.application.ports.out;

import dev.felipeazsantos.hexagonal.application.core.domain.Customer;

import java.util.Optional;

public interface FindCustomerByIdOutputPort {

    Optional<Customer> find(String id);
}
