package dev.felipeazsantos.hexagonal.adapters.out.repository.mapper;

import dev.felipeazsantos.hexagonal.adapters.out.repository.entity.CustomerEntity;
import dev.felipeazsantos.hexagonal.application.core.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustomerEntity toEntity(Customer customer);
}
