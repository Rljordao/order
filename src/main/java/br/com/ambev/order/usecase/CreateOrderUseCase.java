package br.com.ambev.order.usecase;

import br.com.ambev.order.domain.model.Order;

public interface CreateOrderUseCase {
    Order execute(Order order);
}
