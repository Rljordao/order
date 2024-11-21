package br.com.ambev.order.usecase;

import java.math.BigInteger;

public interface OrderIdGeneratorUseCase {

     BigInteger execute(long customerId, long timestamp, int size);
}
