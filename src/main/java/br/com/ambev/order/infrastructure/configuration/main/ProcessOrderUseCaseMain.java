package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.mapper.OrderMapper;
import br.com.ambev.order.application.usecaseImpl.ProcessOrderUseCaseImpl;
import br.com.ambev.order.usecase.CreateOrderUseCase;
import br.com.ambev.order.usecase.OrderIdGeneratorUseCase;
import br.com.ambev.order.usecase.ProcessOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessOrderUseCaseMain {

    @Bean
    public ProcessOrderUseCase processOrderUseCase(OrderIdGeneratorUseCase orderIdGeneratorUseCase,
                                                   OrderMapper orderMapper, CreateOrderUseCase createOrderUseCase) {
        return new ProcessOrderUseCaseImpl(orderIdGeneratorUseCase, orderMapper, createOrderUseCase);
    }
}

