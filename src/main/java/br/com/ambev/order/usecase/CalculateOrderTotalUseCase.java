package br.com.ambev.order.usecase;

import br.com.ambev.order.domain.model.Order;


public interface CalculateOrderTotalUseCase {

     void execute(Order order);
}
