package br.com.ambev.order.infrastructure.service;


import br.com.ambev.order.domain.exception.OrderStatusNotFoundException;
import br.com.ambev.order.domain.model.OrderStatus;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;
import br.com.ambev.order.infrastructure.persistence.OrderStatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderStatusServiceTest {

    @InjectMocks
    private OrderStatusService orderStatusService;

    @Mock
    private OrderStatusRepository orderStatusRepository;

    @Test
    void getStatus_shouldReturnOrderStatus_whenStatusIsFound() {
        OrderStatusEnum statusEnum = OrderStatusEnum.CALCULATED;
        OrderStatus expectedOrderStatus = new OrderStatus();
        expectedOrderStatus.setStatus(statusEnum);

        when(orderStatusRepository.findByStatus(statusEnum)).thenReturn(Optional.of(expectedOrderStatus));

        OrderStatus result = orderStatusService.getStatus(statusEnum);

        assertNotNull(result);
        assertEquals(statusEnum, result.getStatus());
        verify(orderStatusRepository, times(1)).findByStatus(statusEnum);
    }

    @Test
    void getStatus_shouldThrowOrderStatusNotFoundException_whenStatusIsNotFound() {
        OrderStatusEnum statusEnum = OrderStatusEnum.CALCULATED;

        when(orderStatusRepository.findByStatus(statusEnum)).thenReturn(Optional.empty());

        OrderStatusNotFoundException exception = assertThrows(OrderStatusNotFoundException.class, () -> {
            orderStatusService.getStatus(statusEnum);
        });

        assertEquals("Status do pedido não encontrado: CALCULATED", exception.getMessage());
        verify(orderStatusRepository, times(1)).findByStatus(statusEnum);
    }
}
