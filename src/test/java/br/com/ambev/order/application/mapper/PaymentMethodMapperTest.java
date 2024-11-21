package br.com.ambev.order.application.mapper;

import br.com.ambev.order.application.dto.PaymentMethodDTO;
import br.com.ambev.order.builder.OrderBuilder;
import br.com.ambev.order.domain.model.PaymentMethod;
import br.com.ambev.order.domain.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentMethodMapperTest {

    @InjectMocks
    private PaymentMethodMapper paymentMethodMapper;

    @Mock
    private OrderMapper orderMapper;


    private Order order;



    @Test
    void convert_shouldConvertDTOToPaymentMethod() {
        order = OrderBuilder.orderBuild();
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
        paymentMethodDTO.setPaymentMethodId(1);
        paymentMethodDTO.setPaymentDate(LocalDateTime.now().toString());
        paymentMethodDTO.setAcquirerName("AcquirerName");
        paymentMethodDTO.setCardId("Card1234");
        paymentMethodDTO.setId(1L);
        paymentMethodDTO.setNumberCard("1234567890");
        paymentMethodDTO.setTotalValue(BigDecimal.valueOf(100.50).doubleValue());

        PaymentMethod result = paymentMethodMapper.convert(paymentMethodDTO, order);

        assertEquals(paymentMethodDTO.getPaymentMethodId(), result.getPaymentMethodId());
        assertEquals(paymentMethodDTO.getPaymentDate(), result.getPaymentDate());
        assertEquals(paymentMethodDTO.getAcquirerName(), result.getAcquirerName());
        assertEquals(paymentMethodDTO.getCardId(), result.getCardId());
        assertEquals(paymentMethodDTO.getId(), result.getId());
        assertEquals(paymentMethodDTO.getNumberCard(), result.getNumberCard());
        assertEquals(paymentMethodDTO.getTotalValue(), result.getTotalValue());
        assertEquals(order, result.getOrder());
    }

    @Test
    void convert_shouldReturnNull_whenPaymentMethodDTOIsNull() {
        order = OrderBuilder.orderBuild();
        PaymentMethodDTO paymentMethodDTO = null;
        PaymentMethod result = paymentMethodMapper.convert(paymentMethodDTO, order);
        assertNull(result);
    }

    @Test
    void convertList_shouldConvertListOfDTOsToListOfPaymentMethods() {
        order = OrderBuilder.orderBuild();
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
        paymentMethodDTO.setPaymentMethodId(1);
        paymentMethodDTO.setPaymentDate(LocalDateTime.now().toString());
        paymentMethodDTO.setAcquirerName("AcquirerName");
        paymentMethodDTO.setCardId("Card1234");
        paymentMethodDTO.setId(1L);
        paymentMethodDTO.setNumberCard("1234567890");
        paymentMethodDTO.setTotalValue(BigDecimal.valueOf(100.50).doubleValue());

        PaymentMethod result = paymentMethodMapper.convert(Collections.singletonList(paymentMethodDTO), order).get(0);

        assertEquals(paymentMethodDTO.getPaymentMethodId(), result.getPaymentMethodId());
        assertEquals(paymentMethodDTO.getPaymentDate(), result.getPaymentDate());
        assertEquals(paymentMethodDTO.getAcquirerName(), result.getAcquirerName());
        assertEquals(paymentMethodDTO.getCardId(), result.getCardId());
        assertEquals(paymentMethodDTO.getId(), result.getId());
        assertEquals(paymentMethodDTO.getNumberCard(), result.getNumberCard());
        assertEquals(paymentMethodDTO.getTotalValue(), result.getTotalValue());
        assertEquals(order, result.getOrder());
    }

}
