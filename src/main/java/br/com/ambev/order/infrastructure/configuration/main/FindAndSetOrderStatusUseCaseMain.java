package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.gateway.OrderStatusGateway;
import br.com.ambev.order.application.usecaseImpl.FindAndSetOrderStatusUseCaseImpl;
import br.com.ambev.order.usecase.FindAndSetOrderStatusUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindAndSetOrderStatusUseCaseMain {

    @Bean
    public FindAndSetOrderStatusUseCase createFindAndSetOrderStatusUseCase(OrderStatusGateway orderStatusGateway){
        return new FindAndSetOrderStatusUseCaseImpl(orderStatusGateway);
    }
}
