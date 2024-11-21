package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.gateway.OrderGateway;
import br.com.ambev.order.application.usecaseImpl.CreateOrderUseCaseImpl;
import br.com.ambev.order.usecase.CalculateOrderTotalUseCase;
import br.com.ambev.order.usecase.CreateOrderUseCase;
import br.com.ambev.order.usecase.FindAndSetOrderStatusUseCase;
import br.com.ambev.order.usecase.ValidateOrderValueUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateOrderUseCaseMain {

    @Bean
    public CreateOrderUseCase createCreateOrderUseCase(ValidateOrderValueUseCase validateOrderValueUseCase, CalculateOrderTotalUseCase calculateOrderTotalUseCase,
                                                       OrderGateway orderGateway,
                                                       FindAndSetOrderStatusUseCase findAndSetOrderStatusUseCase){

        return new CreateOrderUseCaseImpl(validateOrderValueUseCase, calculateOrderTotalUseCase,
                orderGateway, findAndSetOrderStatusUseCase);
    }
}
