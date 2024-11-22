package br.com.ambev.order.application.usecaseImpl;

import br.com.ambev.order.application.gateway.OrderGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExistsByOrderNumberUseCaseImplTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private ExistsByOrderNumberUseCaseImpl existsByOrderNumberUseCase;

    @Test
    void executeTest() {
        BigInteger orderNumber = new BigInteger("123456789");

        when(orderGateway.existsByOrderNumber(orderNumber)).thenReturn(true);

        Boolean exists = existsByOrderNumberUseCase.execute(orderNumber);

        assertTrue(exists);
        verify(orderGateway, times(1)).existsByOrderNumber(orderNumber);
    }
}
