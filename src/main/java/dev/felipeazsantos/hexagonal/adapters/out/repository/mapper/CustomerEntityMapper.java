package dev.felipeazsantos.hexagonal.adapters.out.repository.mapper;

import dev.felipeazsantos.hexagonal.adapters.out.repository.entity.AddressEntity;
import dev.felipeazsantos.hexagonal.adapters.out.repository.entity.CustomerEntity;
import dev.felipeazsantos.hexagonal.application.core.domain.Address;
import dev.felipeazsantos.hexagonal.application.core.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustomerEntity toEntity(Customer customer);

    Customer toCustomer(CustomerEntity customerEntity);

    AddressEntity toAddressEntity(Address address);

    Address toAddress(AddressEntity addressEntity);
}
