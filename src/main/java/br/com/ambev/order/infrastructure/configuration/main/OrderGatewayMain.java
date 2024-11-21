package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.gateway.OrderGateway;
import br.com.ambev.order.application.gateway.OrderStatusGateway;
import br.com.ambev.order.infrastructure.persistence.OrderRepository;
import br.com.ambev.order.infrastructure.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderGatewayMain {

    @Bean
    public OrderGateway orderGateway(OrderRepository orderRepository, OrderStatusGateway orderStatusService) {
        return new OrderService(orderRepository, orderStatusService);
    }

}