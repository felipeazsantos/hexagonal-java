package dev.felipeazsantos.hexagonal.adapters.out;

import dev.felipeazsantos.hexagonal.adapters.out.repository.CustomerRepository;
import dev.felipeazsantos.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import dev.felipeazsantos.hexagonal.application.core.domain.Customer;
import dev.felipeazsantos.hexagonal.application.ports.out.UpdateCustomerOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateCustomerAdapter implements UpdateCustomerOutputPort {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public void update(Customer customer) {
        var customerEntity = customerEntityMapper.toEntity(customer);
        customerRepository.save(customerEntity);
    }
}
