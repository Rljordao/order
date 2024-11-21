package br.com.ambev.order.infrastructure.service;

import br.com.ambev.order.domain.exception.OrderIdGenerationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class OrderIdGeneratorServiceTest {

    @InjectMocks
    private OrderIdGeneratorService orderIdGeneratorService;

    @Test
    void generateOrderId_shouldGenerateValidOrderId() {
        long customerId = 12345L;
        long timestamp = System.currentTimeMillis();
        int size = 5;

        BigInteger orderId = orderIdGeneratorService.generateOrderId(customerId, timestamp, size);

        assertNotNull(orderId);
        assertTrue(orderId.compareTo(BigInteger.ZERO) > 0);
        assertTrue(orderId.compareTo(new BigInteger("99999999999999999999999999999999999999")) <= 0);
    }

    @Test
    void generateOrderId_shouldThrowOrderIdGenerationException_whenNoSuchAlgorithmExceptionOccurs() {
        try (MockedStatic<MessageDigest> mockedDigest = mockStatic(MessageDigest.class)) {
            mockedDigest.when(() -> MessageDigest.getInstance("SHA-256"))
                    .thenThrow(new NoSuchAlgorithmException("Simulação de falha no algoritmo"));

            long customerId = 12345L;
            long timestamp = System.currentTimeMillis();
            int size = 5;

            OrderIdGenerationException exception = assertThrows(OrderIdGenerationException.class, () -> {
                orderIdGeneratorService.generateOrderId(customerId, timestamp, size);
            });

            assertEquals("Erro ao gerar o hash do ID do pedido", exception.getMessage());
        }
    }
}
