package br.com.ambev.order.application.usecaseImpl;

import br.com.ambev.order.application.gateway.OrderGateway;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.usecase.FindOrderByOrderNumberUseCase;

import java.math.BigInteger;

public class FindOrderByOrderNumberUseCaseImpl implements FindOrderByOrderNumberUseCase {

    private final OrderGateway orderGateway;

    public FindOrderByOrderNumberUseCaseImpl(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    @Override
    public Order execute(BigInteger orderNumber) {
        return orderGateway.findOrderByOrderNumber(orderNumber);
    }
}
