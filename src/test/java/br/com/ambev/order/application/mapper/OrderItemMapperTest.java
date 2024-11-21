package br.com.ambev.order.application.mapper;

import br.com.ambev.order.application.dto.OrderItemDTO;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.OrderItem;
import br.com.ambev.order.domain.model.Discount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderItemMapperTest {

    @InjectMocks
    private OrderItemMapper orderItemMapper;

    @Mock
    private DiscountMapper discountMapper;

    @Mock
    private Discount discount;

    @Test
    void convert_shouldConvertDTOToOrderItem() {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(1L);
        orderItemDTO.setProductId(100L);
        orderItemDTO.setProductName("Product A");
        orderItemDTO.setQuantity(10);
        orderItemDTO.setUnitPrice(BigDecimal.valueOf(50.00));
        orderItemDTO.setTotalItem(BigDecimal.valueOf(500.00));

        when(discountMapper.convert(orderItemDTO.getDiscount(), new Order())).thenReturn(discount);

        OrderItem result = orderItemMapper.convert(orderItemDTO, new Order());

        assertEquals(orderItemDTO.getId(), result.getId());
        assertEquals(orderItemDTO.getProductId(), result.getProductId());
        assertEquals(orderItemDTO.getProductName(), result.getProductName());
        assertEquals(orderItemDTO.getQuantity(), result.getQuantity());
        assertEquals(orderItemDTO.getUnitPrice(), result.getUnitPrice());
        assertEquals(orderItemDTO.getTotalItem(), result.getTotalItem());
        assertEquals(discount, result.getDiscount());
    }

    @Test
    void convert_shouldReturnNull_whenOrderItemDTOIsNull() {
        OrderItemDTO orderItemDTO = null;
        OrderItem result = orderItemMapper.convert(orderItemDTO, new Order());
        assertNull(result);
    }

    @Test
    void convertList_shouldConvertListOfDTOsToListOfOrderItems() {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(1L);
        orderItemDTO.setProductId(100L);
        orderItemDTO.setProductName("Product A");
        orderItemDTO.setQuantity(10);
        orderItemDTO.setUnitPrice(BigDecimal.valueOf(50.00));
        orderItemDTO.setTotalItem(BigDecimal.valueOf(500.00));

        when(discountMapper.convert(orderItemDTO.getDiscount(), new Order())).thenReturn(discount);

        OrderItem result = orderItemMapper.convert(Collections.singletonList(orderItemDTO), new Order()).get(0);

        assertEquals(orderItemDTO.getId(), result.getId());
        assertEquals(orderItemDTO.getProductId(), result.getProductId());
        assertEquals(orderItemDTO.getProductName(), result.getProductName());
        assertEquals(orderItemDTO.getQuantity(), result.getQuantity());
        assertEquals(orderItemDTO.getUnitPrice(), result.getUnitPrice());
        assertEquals(orderItemDTO.getTotalItem(), result.getTotalItem());
        assertEquals(discount, result.getDiscount());
    }
}
