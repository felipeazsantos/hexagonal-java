package dev.felipeazsantos.hexagonal.application.ports.out;

public interface DeleteCustomerByIdOutputPort {

    void delete(String id);
}
