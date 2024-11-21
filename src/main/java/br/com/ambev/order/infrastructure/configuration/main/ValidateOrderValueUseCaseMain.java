package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.usecaseImpl.ValidateOrderValueUseCaseImpl;
import br.com.ambev.order.usecase.ValidateOrderValueUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateOrderValueUseCaseMain {

    @Bean
    public ValidateOrderValueUseCase createValidateOrderValueUseCase(){
        return new ValidateOrderValueUseCaseImpl();
    }
}
