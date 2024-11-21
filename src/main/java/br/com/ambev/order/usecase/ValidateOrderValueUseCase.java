package br.com.ambev.order.usecase;

import br.com.ambev.order.domain.model.Order;

public interface ValidateOrderValueUseCase {
    void execute(Order order);
}
