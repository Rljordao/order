package br.com.ambev.order.usecase;

import br.com.ambev.order.domain.model.Order;

import java.math.BigInteger;

public interface FindOrderByOrderNumberUseCase {
    Order execute(BigInteger orderNumber);
}
