package br.com.ambev.order.application.usecaseImpl;


import br.com.ambev.order.application.gateway.OrderGateway;
import br.com.ambev.order.builder.OrderBuilder;
import br.com.ambev.order.domain.exception.OrderValidationException;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;
import br.com.ambev.order.usecase.CalculateOrderTotalUseCase;
import br.com.ambev.order.usecase.FindAndSetOrderStatusUseCase;
import br.com.ambev.order.usecase.ValidateOrderValueUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseImplTest {

    @InjectMocks
    private CreateOrderUseCaseImpl createOrderUseCase;

    @Mock
    private ValidateOrderValueUseCase validateOrderValueUseCase;

    @Mock
    private CalculateOrderTotalUseCase calculateOrderTotalUseCase;

    @Mock
    private OrderGateway orderGateway;

    @Mock
    private FindAndSetOrderStatusUseCase findAndSetOrderStatusUseCase;

    private Order order;

    @BeforeEach
    void setUp() {
        order = OrderBuilder.orderBuild();
    }

    @Test
    void execute_shouldThrowOrderValidationException_whenOrderHasNoItems() {
        order.setItems(null);
        OrderValidationException exception = assertThrows(OrderValidationException.class, () -> {
            createOrderUseCase.execute(order);
        });

        assertEquals("O pedido deve ter pelo menos um item", exception.getMessage());
    }

    @Test
    void execute_shouldCallDependencies_whenOrderIsValid() {

        when(orderGateway.saveOrder(any(Order.class))).thenReturn(order);
        createOrderUseCase.execute(order);

        verify(validateOrderValueUseCase, times(1)).execute(order);
        verify(calculateOrderTotalUseCase, times(1)).execute(order);
        verify(findAndSetOrderStatusUseCase, times(1)).execute(order, OrderStatusEnum.CALCULATED);
        verify(orderGateway, times(1)).saveOrder(order);
    }
}
