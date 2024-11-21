package br.com.ambev.order.application.usecaseImpl;

import br.com.ambev.order.application.gateway.OrderIdGeneratorGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderIdGeneratorUseCaseImplTest {

    @Mock
    private OrderIdGeneratorGateway orderIdGeneratorGateway;

    @InjectMocks
    private OrderIdGeneratorUseCaseImpl orderIdGeneratorUseCase;

    private long customerId;
    private long timestamp;
    private int size;
    private BigInteger expectedOrderId;

    @BeforeEach
    void setUp() {
        customerId = 12345L;
        timestamp = System.currentTimeMillis();
        size = 10;
        expectedOrderId = BigInteger.valueOf(9876543210L);
    }

    @Test
    void execute_shouldReturnGeneratedOrderId() {
        when(orderIdGeneratorGateway.generateOrderId(customerId, timestamp, size)).thenReturn(expectedOrderId);

        BigInteger result = orderIdGeneratorUseCase.execute(customerId, timestamp, size);

        assertEquals(expectedOrderId, result);

        Mockito.verify(orderIdGeneratorGateway).generateOrderId(customerId, timestamp, size);
    }
}
