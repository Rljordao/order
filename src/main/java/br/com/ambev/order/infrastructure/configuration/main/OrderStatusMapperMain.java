package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.mapper.OrderStatusMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderStatusMapperMain {

    @Bean
    public OrderStatusMapper createOrderStatusMapper(){
        return new OrderStatusMapper();
    }
}
