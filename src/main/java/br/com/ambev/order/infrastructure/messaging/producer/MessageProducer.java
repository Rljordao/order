package br.com.ambev.order.infrastructure.messaging.producer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.cloud.stream.function.StreamBridge;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {

    private final StreamBridge streamBridge;

    public <T> void sendMessage(String bindingName, T payload) {
        try {
            log.info("Enviando mensagem para o binding {}: {}", bindingName, payload);
            streamBridge.send(bindingName, MessageBuilder.withPayload(payload).build());
            log.info("Mensagem enviada com sucesso para o binding {}", bindingName);
        } catch (Exception e) {
            log.error("Erro ao enviar mensagem para o binding {}: {}", bindingName, e.getMessage(), e);
        }
    }
}
