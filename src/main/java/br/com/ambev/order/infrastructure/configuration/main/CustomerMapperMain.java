package br.com.ambev.order.infrastructure.configuration.main;


import br.com.ambev.order.application.mapper.CustomerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerMapperMain {

    @Bean
    public CustomerMapper createCustomerMapper(){
        return new CustomerMapper();
    }
}
