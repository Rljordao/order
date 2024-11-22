package br.com.ambev.order.application.usecaseImpl;

import br.com.ambev.order.application.gateway.OrderGateway;
import br.com.ambev.order.builder.OrderBuilder;
import br.com.ambev.order.domain.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FindOrderByOrderNumberUseCaseImplTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private FindOrderByOrderNumberUseCaseImpl findOrderByOrderNumberUseCase;

    @Test
    void execute_shouldReturnOrderWhenOrderNumberExists() {
        BigInteger orderNumber = new BigInteger("123456789");
        Order mockOrder = OrderBuilder.orderBuild();

        when(orderGateway.findOrderByOrderNumber(orderNumber)).thenReturn(mockOrder);

        Order result = findOrderByOrderNumberUseCase.execute(orderNumber);

        assertNotNull(result);
        assertEquals(mockOrder.getId(), result.getId());
        assertEquals(mockOrder.getOrderNumber(), result.getOrderNumber());
        verify(orderGateway, times(1)).findOrderByOrderNumber(orderNumber);
    }

}
