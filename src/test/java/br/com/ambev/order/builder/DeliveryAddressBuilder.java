package br.com.ambev.order.builder;

import br.com.ambev.order.domain.model.DeliveryAddress;

public class DeliveryAddressBuilder {
    public static DeliveryAddress deliveryAddressBuild() {
        return DeliveryAddress.builder()
                .addressDescription("123 Main St, Apt 101")
                .cep("12345-678")
                .city("São Paulo")
                .state("SP")
                .number("101")
                .contactPhone("1122334455")
                .recipientName("Jane Doe")
                .build();
    }

}
