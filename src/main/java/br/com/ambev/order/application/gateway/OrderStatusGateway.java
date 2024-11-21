package br.com.ambev.order.application.gateway;

import br.com.ambev.order.domain.model.OrderStatus;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;

public interface OrderStatusGateway {

    OrderStatus getStatus(OrderStatusEnum status);
}
