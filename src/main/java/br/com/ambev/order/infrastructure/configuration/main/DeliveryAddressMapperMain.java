package br.com.ambev.order.infrastructure.configuration.main;


import br.com.ambev.order.application.mapper.DeliveryAddressMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeliveryAddressMapperMain {

    @Bean
    public DeliveryAddressMapper createDeliveryAddressMapper(){
        return new DeliveryAddressMapper();
    }
}
