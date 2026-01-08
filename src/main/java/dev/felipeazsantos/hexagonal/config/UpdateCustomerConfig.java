package dev.felipeazsantos.hexagonal.config;

import dev.felipeazsantos.hexagonal.adapters.out.FindAddressByZipCodeAdapter;
import dev.felipeazsantos.hexagonal.adapters.out.UpdateCustomerAdapter;
import dev.felipeazsantos.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import dev.felipeazsantos.hexagonal.application.core.usecase.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateCustomerConfig {

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            FindAddressByZipCodeAdapter findAddressByZipCodeAdapter,
            UpdateCustomerAdapter updateCustomerAdapter
    ) {
        return new UpdateCustomerUseCase(
                findCustomerByIdUseCase,
                findAddressByZipCodeAdapter,
                updateCustomerAdapter
        );
    }
}
