package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.gateway.OrderGateway;
import br.com.ambev.order.application.usecaseImpl.ExistsByOrderNumberUseCaseImpl;
import br.com.ambev.order.usecase.ExistsByOrderNumberUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExistsByOrderNumberUseCaseMain {

    @Bean
    public ExistsByOrderNumberUseCase createExistsByOrderNumberUseCase(OrderGateway orderGateway){
        return new ExistsByOrderNumberUseCaseImpl(orderGateway);
    }
}
