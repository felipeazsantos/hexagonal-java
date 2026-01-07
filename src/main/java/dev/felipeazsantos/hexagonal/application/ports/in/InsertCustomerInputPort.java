package dev.felipeazsantos.hexagonal.application.ports.in;

import dev.felipeazsantos.hexagonal.application.core.domain.Customer;

public interface InsertCustomerInputPort {

    void insert(Customer customer, String zipCode);
}
