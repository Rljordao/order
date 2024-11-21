package br.com.ambev.order.application.usecaseImpl;


import br.com.ambev.order.builder.OrderBuilder;
import br.com.ambev.order.builder.OrderStatusBuilder;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.OrderStatus;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;
import br.com.ambev.order.application.gateway.OrderStatusGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FindAndSetOrderStatusUseCaseImplTest {


    @InjectMocks
    private FindAndSetOrderStatusUseCaseImpl findAndSetOrderStatusUseCase;

    @Mock
    private OrderStatusGateway orderStatusGateway;

    private Order order;
    private OrderStatus orderStatus;

    @BeforeEach
    void setUp() {
        order = OrderBuilder.orderBuild();
        orderStatus = OrderStatusBuilder.orderStatusBuild();
    }

    @Test
    void executeTest() {
        OrderStatusEnum statusEnum = OrderStatusEnum.CALCULATED;
        when(orderStatusGateway.getStatus(statusEnum)).thenReturn(orderStatus);

        findAndSetOrderStatusUseCase.execute(order, statusEnum);

        verify(orderStatusGateway, times(1)).getStatus(statusEnum);
        assertEquals(orderStatus.getStatus(), order.getStatus().getStatus());
    }

}
