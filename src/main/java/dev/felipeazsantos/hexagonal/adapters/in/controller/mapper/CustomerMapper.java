package dev.felipeazsantos.hexagonal.adapters.in.controller.mapper;

import dev.felipeazsantos.hexagonal.adapters.in.controller.request.CustomerRequest;
import dev.felipeazsantos.hexagonal.adapters.in.controller.response.CustomerResponse;
import dev.felipeazsantos.hexagonal.application.core.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "isValidCpf", ignore = true)
    Customer toCustomer(CustomerRequest request);

    CustomerResponse toResponse(Customer customer);
}
