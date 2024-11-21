package br.com.ambev.order.infrastructure.messaging.producer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageProducerTest {

    @InjectMocks
    private MessageProducer messageProducer;

    @Mock
    private StreamBridge streamBridge;

    private String bindingName;
    private String payload;

    @BeforeEach
    void setUp() {
        bindingName = "test-binding";
        payload = "Test Message";
    }

    @Test
    void sendMessageSuccessTest() {
        when(streamBridge.send(eq(bindingName), any(Message.class))).thenReturn(true);

        messageProducer.sendMessage(bindingName, payload);

        verify(streamBridge, times(1)).send(eq(bindingName), any(Message.class));
    }

    @Test
    void sendMessageFailureTest() {
        when(streamBridge.send(eq(bindingName), any(Message.class))).thenThrow(new RuntimeException("Falha simulada"));

        messageProducer.sendMessage(bindingName, payload);

        verify(streamBridge, times(1)).send(eq(bindingName), any(Message.class));
    }
}
