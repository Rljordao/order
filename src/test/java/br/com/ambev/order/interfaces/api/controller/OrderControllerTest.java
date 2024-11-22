package br.com.ambev.order.interfaces.api.controller;

import br.com.ambev.openapi.model.OrderModel;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.interfaces.api.controller.mapper.OrderMapper;
import br.com.ambev.order.usecase.FindOrderByOrderNumberUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private FindOrderByOrderNumberUseCase findOrderByOrderNumberUseCase;

    @Mock
    private OrderMapper orderMapper;

    private Order mockOrder;
    private OrderModel mockOrderModel;

    @BeforeEach
    void setUp() {
        mockOrder = Order.builder()
                .orderNumber(BigInteger.valueOf(12345))
                .build();

        mockOrderModel = new OrderModel()
                .orderNumber("12345");
    }

    @Test
    void getOrderSuccessTest() {
        when(findOrderByOrderNumberUseCase.execute(BigInteger.valueOf(12345))).thenReturn(mockOrder);
        when(orderMapper.toModel(mockOrder)).thenReturn(mockOrderModel);

        ResponseEntity<OrderModel> response = orderController.getOrder("12345");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockOrderModel, response.getBody());
        verify(findOrderByOrderNumberUseCase, times(1)).execute(BigInteger.valueOf(12345));
        verify(orderMapper, times(1)).toModel(mockOrder);
    }
}
