package br.com.ambev.order.infrastructure.configuration.main;

import br.com.ambev.order.application.mapper.PaymentMethodMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentMethodMapperMain {

    @Bean
    public PaymentMethodMapper createPaymentMethodMapper(){
        return new PaymentMethodMapper();
    }


}
