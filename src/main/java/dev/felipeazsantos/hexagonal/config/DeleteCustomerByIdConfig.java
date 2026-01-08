package dev.felipeazsantos.hexagonal.config;

import dev.felipeazsantos.hexagonal.adapters.out.DeleteCustomerByIdAdapter;
import dev.felipeazsantos.hexagonal.application.core.usecase.DeleteCustomerByIdUseCase;
import dev.felipeazsantos.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteCustomerByIdConfig {

    @Bean
    public DeleteCustomerByIdUseCase deleteCustomerByIdUseCase(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            DeleteCustomerByIdAdapter deleteCustomerByIdAdapter
    ) {
        return new DeleteCustomerByIdUseCase(
                findCustomerByIdUseCase,
                deleteCustomerByIdAdapter
        );
    }
}
