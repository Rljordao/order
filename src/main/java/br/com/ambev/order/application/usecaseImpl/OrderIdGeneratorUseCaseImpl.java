package br.com.ambev.order.application.usecaseImpl;

import br.com.ambev.order.application.gateway.OrderIdGeneratorGateway;
import br.com.ambev.order.usecase.OrderIdGeneratorUseCase;

import java.math.BigInteger;

public class OrderIdGeneratorUseCaseImpl implements OrderIdGeneratorUseCase {

    private final OrderIdGeneratorGateway orderIdGeneratorGateway;

    public OrderIdGeneratorUseCaseImpl(OrderIdGeneratorGateway orderIdGeneratorGateway) {
        this.orderIdGeneratorGateway = orderIdGeneratorGateway;
    }

    @Override
    public BigInteger execute(long customerId, long timestamp, int size) {
        return orderIdGeneratorGateway.generateOrderId(customerId, timestamp, size);
    }

}
