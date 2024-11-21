package br.com.ambev.order.application.usecaseImpl;


import br.com.ambev.order.builder.DiscountBuilder;
import br.com.ambev.order.builder.OrderBuilder;
import br.com.ambev.order.domain.exception.DiscountExceedsTotalAmountException;
import br.com.ambev.order.domain.exception.OrderValidationException;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.OrderItem;
import br.com.ambev.order.usecase.ValidateOrderValueUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidateOrderValueUseCaseImplTest {

    private ValidateOrderValueUseCase validateOrderValueUseCase;
    private Order order;

    @BeforeEach
    void setUp() {
        validateOrderValueUseCase = new ValidateOrderValueUseCaseImpl();
        order = OrderBuilder.orderBuild();
    }

    @Test
    void execute_shouldThrowOrderValidationException_whenTotalItemsIsZeroOrNegative() {
        order.getItems()
                .forEach(orderItem -> orderItem.setUnitPrice(BigDecimal.ZERO));

        OrderValidationException exception = assertThrows(OrderValidationException.class, () -> {
            validateOrderValueUseCase.execute(order);
        });

        assertEquals("O valor total dos itens deve ser maior que zero.", exception.getMessage());
    }

    @Test
    void execute_shouldThrowDiscountExceedsTotalAmountException_whenDiscountExceedsTotalItemsValue() {
        order.setItemsDiscountTotalValue(BigDecimal.valueOf(999999));

        DiscountExceedsTotalAmountException exception = assertThrows(DiscountExceedsTotalAmountException.class, () -> {
            validateOrderValueUseCase.execute(order);
        });

        assertEquals("O desconto não pode ser maior ou igual ao valor total dos itens.", exception.getMessage());
    }

    @Test
    void execute_shouldThrowOrderValidationException_whenItemQuantityIsZeroOrNegative() {
        var item = OrderItem.builder()
                .id(1L)
                .productId(101L)
                .unitPrice(new BigDecimal("50.00"))
                .quantity(0)
                .totalItem(new BigDecimal("100.00"))
                .productName("Product A")
                .discount(DiscountBuilder.discountBuild())
                .build();
        order.getItems().add(item);
        OrderValidationException exception = assertThrows(OrderValidationException.class, () -> {
            validateOrderValueUseCase.execute(order);
        });

        assertEquals("A quantidade de cada item deve ser maior que zero.", exception.getMessage());
    }

    @Test
    void execute_shouldNotThrowException_whenOrderIsValid() {
        assertDoesNotThrow(() -> validateOrderValueUseCase.execute(order));
    }
}
