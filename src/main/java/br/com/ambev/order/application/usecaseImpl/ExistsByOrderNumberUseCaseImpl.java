package br.com.ambev.order.application.usecaseImpl;

import br.com.ambev.order.application.gateway.OrderGateway;
import br.com.ambev.order.usecase.ExistsByOrderNumberUseCase;

import java.math.BigInteger;

public class ExistsByOrderNumberUseCaseImpl implements ExistsByOrderNumberUseCase {

    private final OrderGateway orderGateway;

    public ExistsByOrderNumberUseCaseImpl(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    @Override
    public Boolean execute(BigInteger orderNumber) {
        return orderGateway.existsByOrderNumber(orderNumber);
    }
}
