package dev.felipeazsantos.hexagonal.application.ports.out;

import dev.felipeazsantos.hexagonal.application.core.domain.Customer;

public interface UpdateCustomerOutputPort {

    void update(Customer customer);
}
