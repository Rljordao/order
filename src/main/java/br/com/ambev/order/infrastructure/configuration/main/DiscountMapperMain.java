package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.mapper.DiscountMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountMapperMain {

    @Bean
    public DiscountMapper createDiscountMapper(){
        return new DiscountMapper();
    }
}
