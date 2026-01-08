package dev.felipeazsantos.hexagonal.config;

import dev.felipeazsantos.hexagonal.adapters.out.FindAddressByZipCodeAdapter;
import dev.felipeazsantos.hexagonal.adapters.out.InsertCustomerAdapter;
import dev.felipeazsantos.hexagonal.application.core.usecase.InsertCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertCustomerConfig {

    @Bean
    public InsertCustomerUseCase insertCustomerUseCase(
            FindAddressByZipCodeAdapter findAddressByZipCodeAdapter,
            InsertCustomerAdapter insertCustomerAdapter
    ) {
        return new InsertCustomerUseCase(
                findAddressByZipCodeAdapter,
                insertCustomerAdapter
        );
    }
}
