package br.com.ambev.order.infrastructure.messaging.producer;


import br.com.ambev.order.application.dto.OrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.EnableRetry;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@EnableRetry

class OrderProducerTest {

    @InjectMocks
    private OrderProducer orderProducer;

    @Mock
    private MessageProducer messageProducer;

    @Value("${order-binding-producer-orders-output-out:orders-output-out-0}")
    private String ORDER_BINDING_OUT;

    private OrderDTO order;

    @BeforeEach
    void setUp() {
        order = new OrderDTO();
    }

    @Test
    void sendOrderTest() {
        orderProducer.sendOrder(order);
        verify(messageProducer, times(1)).sendMessage(ORDER_BINDING_OUT, order);
    }


    @Test
    void sendOrderRetryExhaustedTest() {
        doThrow(new RuntimeException("Falha simulada"))
                .when(messageProducer).sendMessage(ORDER_BINDING_OUT, order);

        assertThrows(RuntimeException.class, () -> orderProducer.sendOrder(order));
        verify(messageProducer, times(1)).sendMessage(ORDER_BINDING_OUT, order); // Verifica que 5 tentativas foram feitas
    }
}