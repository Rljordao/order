package br.com.ambev.order.infrastructure.messaging.consumer;

import java.util.Optional;
import java.util.function.Consumer;

import br.com.ambev.order.application.dto.OrderDTO;
import br.com.ambev.order.infrastructure.messaging.producer.OrderProducer;
import br.com.ambev.order.usecase.ProcessOrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderListener {

    private final ProcessOrderUseCase processOrderUseCase;
    private final OrderProducer orderProducer;

    @Bean
    public Consumer<Message<OrderDTO>> orderInput() {
        return message -> {
            String key = (String) message.getHeaders().get(KafkaHeaders.RECEIVED_KEY);
            try {
                log.info("Recebendo mensagem com chave: {}", key);
                Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
                var order = message.getPayload();
                order.setIndependentKey(key);
                processOrderUseCase.execute(order);
                acknowledgeMessage(acknowledgment);
                log.info("Mensagem com chave {} processada com sucesso.", key);
                orderProducer.sendOrder(order);
            } catch (Exception e) {
                log.error("Erro ao processar mensagem com chave {}: {}", key, e.getMessage(), e);
                throw e;
            }
        };
    }

    private void acknowledgeMessage(Acknowledgment acknowledgment) {
        Optional.ofNullable(acknowledgment).ifPresent(Acknowledgment::acknowledge);
    }

}


