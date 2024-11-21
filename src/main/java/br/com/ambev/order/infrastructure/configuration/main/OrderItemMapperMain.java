package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.mapper.DiscountMapper;
import br.com.ambev.order.application.mapper.OrderItemMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderItemMapperMain {

    @Bean
    public OrderItemMapper createOrderItemMapper(DiscountMapper discountMapper){
        return new OrderItemMapper(discountMapper);
    }


}
