package dev.felipeazsantos.hexagonal.adapters.out;

import dev.felipeazsantos.hexagonal.adapters.out.repository.CustomerRepository;
import dev.felipeazsantos.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import dev.felipeazsantos.hexagonal.application.core.domain.Customer;
import dev.felipeazsantos.hexagonal.application.ports.out.InsertCustomerOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertCustomerAdapter implements InsertCustomerOutputPort {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public void insert(Customer customer) {
        var entity = customerEntityMapper.toEntity(customer);
        customerRepository.save(entity);
    }
}
