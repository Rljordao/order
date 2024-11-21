package br.com.ambev.order.infrastructure.messaging.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import br.com.ambev.order.application.dto.OrderDTO;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderProducer {

    private final MessageProducer messageProducer;

    @Value("${order-binding-producer-orders-output-out:orders-output-out-0}")
    private String ORDER_BINDING_OUT;

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 2000, multiplier = 2))
    public void sendOrder(OrderDTO order) {
        log.info("Preparando envio do pedido para o produto externo B: {}", order);
        messageProducer.sendMessage(ORDER_BINDING_OUT, order);
    }
}
