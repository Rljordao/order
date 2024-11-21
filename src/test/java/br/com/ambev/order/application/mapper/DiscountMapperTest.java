package br.com.ambev.order.application.mapper;

import br.com.ambev.order.application.dto.DiscountDTO;
import br.com.ambev.order.domain.model.Discount;
import br.com.ambev.order.domain.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class DiscountMapperTest {

    @InjectMocks
    private DiscountMapper discountMapper;

    @Mock
    private OrderMapper orderMapper;

    @Test
    void convert_shouldConvertDTOToDiscount() {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setDiscountValue(BigDecimal.valueOf(100));
        discountDTO.setPercentageDiscount(BigDecimal.valueOf(10));
        discountDTO.setDiscountType("Percentage");
        discountDTO.setOrder(null);

        Discount result = discountMapper.convert(discountDTO, new Order());

        assertEquals(discountDTO.getDiscountValue(), result.getDiscountValue());
        assertEquals(discountDTO.getPercentageDiscount(), result.getPercentageDiscount());
        assertEquals(discountDTO.getDiscountType(), result.getDiscountType());
        assertEquals(LocalDateTime.now().getDayOfYear(), result.getCreatedAt().getDayOfYear());
        assertEquals(LocalDateTime.now().getDayOfYear(), result.getUpdatedAt().getDayOfYear());
    }

    @Test
    void convert_shouldReturnNull_whenDiscountDTOIsNull() {
        Discount result = discountMapper.convert(null, null);

        assertNull(result);
    }
}
