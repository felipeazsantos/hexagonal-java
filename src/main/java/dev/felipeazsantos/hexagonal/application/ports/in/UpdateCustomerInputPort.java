package dev.felipeazsantos.hexagonal.application.ports.in;

import dev.felipeazsantos.hexagonal.application.core.domain.Customer;

public interface UpdateCustomerInputPort {

    void update(Customer customer, String zipCode);
}
