package br.com.ambev.order.application.usecaseImpl;

import br.com.ambev.order.domain.exception.DiscountExceedsTotalAmountException;
import br.com.ambev.order.domain.exception.OrderValidationException;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.usecase.ValidateOrderValueUseCase;

import java.math.BigDecimal;

public class ValidateOrderValueUseCaseImpl implements ValidateOrderValueUseCase {

    @Override
    public void execute(Order order) {
        BigDecimal totalItems = order.getItems().stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalItems.compareTo(BigDecimal.ZERO) <= 0) {
            throw new OrderValidationException("O valor total dos itens deve ser maior que zero.");
        }

        if (order.getItemsDiscountTotalValue().compareTo(order.getItemsTotalValue()) >= 0) {
            throw new DiscountExceedsTotalAmountException("O desconto não pode ser maior ou igual ao valor total dos itens.");
        }

        order.getItems().stream()
                .filter(item -> item.getQuantity() <= 0)
                .findAny()
                .ifPresent(item-> {
                    throw new OrderValidationException(
                        String.format("A quantidade de cada item deve ser maior que zero.")
                    );
                });

    }
}
