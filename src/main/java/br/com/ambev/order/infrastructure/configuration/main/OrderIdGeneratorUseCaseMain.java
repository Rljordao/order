package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.gateway.OrderIdGeneratorGateway;
import br.com.ambev.order.application.usecaseImpl.OrderIdGeneratorUseCaseImpl;
import br.com.ambev.order.usecase.OrderIdGeneratorUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderIdGeneratorUseCaseMain {

     @Bean
     public OrderIdGeneratorUseCase orderIdGeneratorUseCase(OrderIdGeneratorGateway orderIdGeneratorGateway){
          return new OrderIdGeneratorUseCaseImpl(orderIdGeneratorGateway);
     }
}
