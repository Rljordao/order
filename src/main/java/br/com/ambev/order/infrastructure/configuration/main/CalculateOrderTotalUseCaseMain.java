package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.usecaseImpl.CalculateOrderTotalUseCaseImpl;
import br.com.ambev.order.usecase.CalculateOrderTotalUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculateOrderTotalUseCaseMain {

    @Bean
    public CalculateOrderTotalUseCase createCalculateOrderTotalUseCase(){
        return new CalculateOrderTotalUseCaseImpl();
    }
}
