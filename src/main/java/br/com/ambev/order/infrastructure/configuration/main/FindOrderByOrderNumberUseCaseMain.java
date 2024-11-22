package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.gateway.OrderGateway;
import br.com.ambev.order.application.usecaseImpl.FindOrderByOrderNumberUseCaseImpl;
import br.com.ambev.order.usecase.FindOrderByOrderNumberUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindOrderByOrderNumberUseCaseMain {

    @Bean
    public FindOrderByOrderNumberUseCase createFindOrderByOrderNumberUseCase(OrderGateway orderGateway){
        return new FindOrderByOrderNumberUseCaseImpl(orderGateway);
    }
}
