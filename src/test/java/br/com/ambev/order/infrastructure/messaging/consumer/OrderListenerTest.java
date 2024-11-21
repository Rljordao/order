package br.com.ambev.order.infrastructure.messaging.consumer;

import br.com.ambev.order.application.dto.OrderDTO;
import br.com.ambev.order.infrastructure.messaging.producer.OrderProducer;
import br.com.ambev.order.usecase.ProcessOrderUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderListenerTest {

    @InjectMocks
    private OrderListener orderListener;

    @Mock
    private ProcessOrderUseCase processOrderUseCase;

    @Mock
    private OrderProducer orderProducer;

    @Mock
    private Acknowledgment acknowledgment;

    @Test
    void shouldProcessOrderSuccessfully() {
        OrderDTO orderDTO = new OrderDTO();
        String key = "order-key";

        Message<OrderDTO> message = new GenericMessage<>(orderDTO, Map.of(KafkaHeaders.RECEIVED_KEY, key));

        orderListener.orderInput().accept(message);

        verify(processOrderUseCase, times(1)).execute(orderDTO);

        verify(orderProducer, times(1)).sendOrder(orderDTO);
    }

    @Test
    void shouldHandleExceptionAndNotAcknowledge() {
        OrderDTO orderDTO = new OrderDTO();
        String key = "order-key";
        Message<OrderDTO> message = new GenericMessage<>(orderDTO, Map.of(KafkaHeaders.RECEIVED_KEY, key));

        doThrow(new RuntimeException("Erro de processamento")).when(processOrderUseCase).execute(orderDTO);

        try {
            orderListener.orderInput().accept(message);
        } catch (Exception e) {
            assert(e instanceof RuntimeException);
        }

        verify(processOrderUseCase, times(1)).execute(orderDTO);

        verify(acknowledgment, never()).acknowledge();

        verify(orderProducer, never()).sendOrder(orderDTO);
    }
}
