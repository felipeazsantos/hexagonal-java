package dev.felipeazsantos.hexagonal.application.ports.out;

import dev.felipeazsantos.hexagonal.application.core.domain.Address;

public interface FindAddressByZipCodeOutputPort {

    Address find(String zipCode);
}
