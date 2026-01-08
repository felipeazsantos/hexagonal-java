package dev.felipeazsantos.hexagonal.application.ports.in;

import dev.felipeazsantos.hexagonal.application.core.domain.Customer;

public interface FindCustomerByIdInputPort {

    Customer find(String id);
}
