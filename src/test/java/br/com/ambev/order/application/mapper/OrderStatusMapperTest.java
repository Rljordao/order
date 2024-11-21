package br.com.ambev.order.application.mapper;


import br.com.ambev.order.application.dto.OrderStatusDTO;
import br.com.ambev.order.domain.model.OrderStatus;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class OrderStatusMapperTest {

    @InjectMocks
    private OrderStatusMapper orderStatusMapper;

    @Test
    void convertToOrderStatus_shouldConvertDTOToOrderStatus() {
        Long statusId = 1L;
        OrderStatusDTO statusDTO = new OrderStatusDTO();
        statusDTO.setId(statusId);
        OrderStatus result = orderStatusMapper.convertToOrderStatus(statusDTO);
        assertEquals(OrderStatusEnum.fromStatusId(statusId.intValue()), result.getStatus());
    }

    @Test
    void convertToOrderStatus_shouldReturnNull_whenStatusDTOIsNull() {
        OrderStatus result = orderStatusMapper.convertToOrderStatus(null);
        assertNull(result);
    }

}
