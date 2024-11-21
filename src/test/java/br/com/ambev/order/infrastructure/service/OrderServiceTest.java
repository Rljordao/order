package br.com.ambev.order.infrastructure.service;

import br.com.ambev.order.application.gateway.OrderStatusGateway;
import br.com.ambev.order.builder.OrderBuilder;
import br.com.ambev.order.domain.exception.OrderNotFoundException;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;
import br.com.ambev.order.infrastructure.persistence.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderStatusGateway orderStatusGateway;

    @InjectMocks
    private OrderService orderService;

    private Order order;

    @BeforeEach
    void setUp() {
        order = OrderBuilder.orderBuild();
    }

    @Test
    void fetchOrderById_shouldReturnOrder_whenOrderExists() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order result = orderService.fetchOrderById(1L);

        assertEquals(order, result);
    }

    @Test
    void fetchOrderById_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.fetchOrderById(1L);
        });

        assertEquals("Pedido com ID 1 não encontrado", exception.getMessage());
    }

    @Test
    void saveOrder_shouldSaveOrderSuccessfully() {
        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.saveOrder(order);

        assertEquals(order, result);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void deleteOrder_shouldDeleteOrderSuccessfully() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).save(order);
    }


    @Test
    void deleteOrder_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.deleteOrder(1L);
        });

        assertEquals("Pedido com ID 1 não encontrado", exception.getMessage());
    }

    @Test
    void fetchOrderByOrderNumber_shouldReturnOrder_whenOrderExists() {
        when(orderRepository.findByOrderNumber(BigInteger.valueOf(123456))).thenReturn(Optional.of(order));

        Order result = orderService.fetchOrderByOrderNumber(BigInteger.valueOf(123456));

        assertEquals(order, result, "O pedido deve ser retornado corretamente pelo número do pedido.");
    }

    @Test
    void fetchOrderByOrderNumber_shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        when(orderRepository.findByOrderNumber(BigInteger.valueOf(123456))).thenReturn(Optional.empty());

        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.fetchOrderByOrderNumber(BigInteger.valueOf(123456));
        });

        assertEquals("Pedido com número 123456 não encontrado", exception.getMessage());
    }

    @Test
    void setOrderStatus_shouldSetStatusSuccessfully() {
        OrderStatusEnum newStatus = OrderStatusEnum.CALCULATED;
        when(orderStatusGateway.getStatus(newStatus)).thenReturn(order.getStatus());

        orderService.setOrderStatus(order, newStatus);
        assertEquals(order.getStatus().getStatus(), newStatus);
    }
}