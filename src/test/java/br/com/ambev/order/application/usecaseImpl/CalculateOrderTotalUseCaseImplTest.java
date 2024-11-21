package br.com.ambev.order.application.usecaseImpl;


import br.com.ambev.order.builder.OrderBuilder;
import br.com.ambev.order.builder.OrderItemBuilder;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CalculateOrderTotalUseCaseImplTest {

    @InjectMocks
    private CalculateOrderTotalUseCaseImpl calculateOrderTotalUseCase;
    private Order order;
    private List<OrderItem> items;

    @BeforeEach
    void setUp() {
        order = OrderBuilder.orderBuild();
        items = Arrays.asList(OrderItemBuilder.orderItemBuild(), OrderItemBuilder.orderItemBuild());
    }

    @Test
    void executeTest() {
        calculateOrderTotalUseCase.execute(order);
        assertTrue(order.getItemsTotalValue().compareTo(BigDecimal.valueOf(200.00)) == 0);
        assertTrue(order.getItemsDiscountTotalValue().compareTo(BigDecimal.valueOf(20)) == 0);
        assertTrue(order.getTotalValue().compareTo(BigDecimal.valueOf(180)) == 0);
    }
}
