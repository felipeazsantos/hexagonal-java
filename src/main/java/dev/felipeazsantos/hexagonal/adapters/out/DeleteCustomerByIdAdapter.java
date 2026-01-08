package dev.felipeazsantos.hexagonal.adapters.out;

import dev.felipeazsantos.hexagonal.adapters.out.repository.CustomerRepository;
import dev.felipeazsantos.hexagonal.application.ports.out.DeleteCustomerByIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteCustomerByIdAdapter implements DeleteCustomerByIdOutputPort {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }
}
