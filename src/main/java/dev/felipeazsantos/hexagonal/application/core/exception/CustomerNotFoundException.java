package dev.felipeazsantos.hexagonal.application.core.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String id) {
        super(String.format("Customer with id %s not found", id));
    }
}
