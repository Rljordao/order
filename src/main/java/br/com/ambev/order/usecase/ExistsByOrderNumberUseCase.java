package br.com.ambev.order.usecase;

import java.math.BigInteger;

public interface ExistsByOrderNumberUseCase {
    Boolean execute(BigInteger orderNumber);
}
