package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.mapper.CustomerMapper;
import br.com.ambev.order.application.mapper.DeliveryAddressMapper;
import br.com.ambev.order.application.mapper.OrderItemMapper;
import br.com.ambev.order.application.mapper.OrderMapper;
import br.com.ambev.order.application.mapper.OrderStatusMapper;
import br.com.ambev.order.application.mapper.PaymentMethodMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMapperMain {

    @Bean
    public OrderMapper createOrderMapper(OrderStatusMapper orderStatusMapper,
                                         CustomerMapper customerMapper,
                                         DeliveryAddressMapper deliveryAddressMapper,
                                         PaymentMethodMapper paymentMethodMapper,
                                         OrderItemMapper orderItemMapper) {
        return new OrderMapper(orderStatusMapper, customerMapper, deliveryAddressMapper, paymentMethodMapper, orderItemMapper);
    }
}
