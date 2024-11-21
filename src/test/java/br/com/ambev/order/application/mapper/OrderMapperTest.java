package br.com.ambev.order.application.mapper;

import br.com.ambev.order.application.dto.OrderDTO;
import br.com.ambev.order.application.dto.PaymentMethodDTO;
import br.com.ambev.order.builder.OrderBuilder;
import br.com.ambev.order.builder.PaymentMethodBuilder;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.Customer;
import br.com.ambev.order.domain.model.DeliveryAddress;
import br.com.ambev.order.domain.model.PaymentMethod;
import br.com.ambev.order.domain.model.OrderItem;
import br.com.ambev.order.domain.model.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderMapperTest {

    @InjectMocks
    private OrderMapper orderMapper;

    @Mock
    private OrderStatusMapper orderStatusMapper;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private DeliveryAddressMapper deliveryAddressMapper;

    @Mock
    private PaymentMethodMapper paymentMethodMapper;

    @Mock
    private OrderItemMapper orderItemMapper;

    @Mock
    private OrderStatus orderStatus;

    @Mock
    private Customer customer;

    @Mock
    private DeliveryAddress deliveryAddress;

    private PaymentMethod paymentMethod;

    private OrderItem orderItem;
    private Order order;

    @Test
    void convertToOrder_shouldConvertDTOToOrder() {
        order = OrderBuilder.orderBuild();
        paymentMethod = PaymentMethodBuilder.paymentMethodBuild();
        paymentMethod.setOrder(order);

        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setOrderNumber(new BigInteger("34567897"));
        orderDTO.setIndependentKey("independentKey");
        orderDTO.setModificationDate(LocalDateTime.now());
        orderDTO.setCreationDate(LocalDateTime.now());
        orderDTO.setTotalValue(BigDecimal.valueOf(100.00));
        orderDTO.setItemsTotalValue(BigDecimal.valueOf(90.00));
        orderDTO.setItemsDiscountTotalValue(BigDecimal.valueOf(10.00));
        orderDTO.setBillingDate(LocalDateTime.now());
        orderDTO.setDateSale(LocalDateTime.now());


        when(orderStatusMapper.convertToOrderStatus(orderDTO.getStatus())).thenReturn(orderStatus);
        when(customerMapper.convert(orderDTO.getCustomer())).thenReturn(customer);
        when(deliveryAddressMapper.convert(orderDTO.getDeliveryAddress())).thenReturn(deliveryAddress);

        Order result = orderMapper.convertToOrder(orderDTO);


        assertNotNull(result);
        assertEquals(orderDTO.getId(), result.getId());
        assertEquals(orderDTO.getOrderNumber(), result.getOrderNumber());
        assertEquals(orderDTO.getIndependentKey(), result.getIndependentKey());
        assertEquals(orderDTO.getModificationDate(), result.getModificationDate());
        assertEquals(orderDTO.getCreationDate(), result.getCreationDate());
        assertEquals(orderDTO.getTotalValue(), result.getTotalValue());
        assertEquals(orderDTO.getItemsTotalValue(), result.getItemsTotalValue());
        assertEquals(orderDTO.getItemsDiscountTotalValue(), result.getItemsDiscountTotalValue());
        assertEquals(orderDTO.getBillingDate(), result.getBillingDate());
        assertEquals(orderDTO.getDateSale(), result.getDateSale());
        assertEquals(orderStatus, result.getStatus());
        assertEquals(customer, result.getCustomer());
        assertEquals(deliveryAddress, result.getDeliveryAddress());

    }

    @Test
    void convertToOrder_shouldReturnNull_whenOrderDTOIsNull() {
        Order result = orderMapper.convertToOrder(null);
        assertNull(result);
    }
}
