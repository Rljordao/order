package br.com.ambev.order.usecase;


import br.com.ambev.order.application.dto.OrderDTO;

public interface ProcessOrderUseCase {

    void execute(OrderDTO order);
}
