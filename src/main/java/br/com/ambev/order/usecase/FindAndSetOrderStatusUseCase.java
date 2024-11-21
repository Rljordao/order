package br.com.ambev.order.usecase;

import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;

public interface FindAndSetOrderStatusUseCase {
    void execute(Order order, OrderStatusEnum statusEnum);
}
