package dev.felipeazsantos.hexagonal.adapters.in.controller;

import dev.felipeazsantos.hexagonal.adapters.in.controller.mapper.CustomerMapper;
import dev.felipeazsantos.hexagonal.adapters.in.controller.request.CustomerRequest;
import dev.felipeazsantos.hexagonal.application.ports.in.InsertCustomerInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private InsertCustomerInputPort insertCustomerInputPort;

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest request) {
        var customer = customerMapper.toCustomer(request);
        insertCustomerInputPort.insert(customer, request.getZipCode());
        return ResponseEntity.ok().build();
    }
}
