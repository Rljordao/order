package br.com.ambev.order.builder;

import br.com.ambev.order.domain.model.OrderItem;

import java.math.BigDecimal;

public class OrderItemBuilder {
    public static OrderItem orderItemBuild() {
        return OrderItem.builder()
                .id(1L)
                .productId(101L)
                .unitPrice(new BigDecimal("50.00"))
                .quantity(2)
                .totalItem(new BigDecimal("100.00"))
                .productName("Product A")
                .discount(DiscountBuilder.discountBuild())
                .build();
    }

}
