package dev.felipeazsantos.hexagonal.adapters.out.client.mapper;

import dev.felipeazsantos.hexagonal.adapters.out.client.response.AddressResponse;
import dev.felipeazsantos.hexagonal.application.core.domain.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {

    Address toAddress(AddressResponse addressResponse);
}
