package br.com.ambev.order.application.gateway;

import java.math.BigInteger;

public interface OrderIdGeneratorGateway {

    BigInteger generateOrderId(long customerId, long timestamp, int size);
}
