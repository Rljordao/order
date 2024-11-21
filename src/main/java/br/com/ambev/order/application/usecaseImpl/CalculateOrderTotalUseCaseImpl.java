package br.com.ambev.order.application.usecaseImpl;

import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.usecase.CalculateOrderTotalUseCase;

import java.math.BigDecimal;
import java.util.Optional;

public class CalculateOrderTotalUseCaseImpl implements CalculateOrderTotalUseCase {

    @Override
    public void execute(Order order) {
        BigDecimal total = order.getItems().stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDiscount = order.getItems().stream()
                .map(item -> Optional.ofNullable(item.getDiscount())
                        .map(discount -> discount.getDiscountValue())
                        .orElse(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalValue = total.subtract(totalDiscount);
        order.setItemsTotalValue(total);
        order.setTotalValue(totalValue);
        order.setItemsDiscountTotalValue(totalDiscount);
    }
}
