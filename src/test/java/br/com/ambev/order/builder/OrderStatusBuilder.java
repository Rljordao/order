package br.com.ambev.order.builder;

import br.com.ambev.order.domain.model.OrderStatus;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;

public class OrderStatusBuilder {
    public static OrderStatus orderStatusBuild() {
        return OrderStatus.builder()
                .id(1L)
                .status(OrderStatusEnum.CALCULATED)
                .build();
    }

}
