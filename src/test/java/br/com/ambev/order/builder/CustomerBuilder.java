package br.com.ambev.order.builder;

import br.com.ambev.order.domain.model.Customer;

public class CustomerBuilder {
    public static Customer  customerBuild() {
        return Customer.builder()
                .id(1L)
                .customerId(12345L)
                .documentNumber("12345678900")
                .email("customer@example.com")
                .name("John Doe")
                .build();
    }
}
