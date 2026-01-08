package dev.felipeazsantos.hexagonal.adapters.in.controller;

import dev.felipeazsantos.hexagonal.adapters.in.controller.mapper.CustomerMapper;
import dev.felipeazsantos.hexagonal.adapters.in.controller.request.CustomerRequest;
import dev.felipeazsantos.hexagonal.adapters.in.controller.response.CustomerResponse;
import dev.felipeazsantos.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import dev.felipeazsantos.hexagonal.application.ports.in.InsertCustomerInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private InsertCustomerInputPort insertCustomerInputPort;

    @Autowired
    private FindCustomerByIdInputPort findCustomerByIdInputPort;

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest request) {
        var customer = customerMapper.toCustomer(request);
        insertCustomerInputPort.insert(customer, request.getZipCode());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("id") final String id) {
        var customer = findCustomerByIdInputPort.find(id);
        return ResponseEntity.ok(customerMapper.toResponse(customer));
    }
}
