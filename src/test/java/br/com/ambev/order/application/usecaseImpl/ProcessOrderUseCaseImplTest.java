package br.com.ambev.order.application.usecaseImpl;


import br.com.ambev.order.application.dto.CustomerDTO;
import br.com.ambev.order.application.dto.OrderDTO;
import br.com.ambev.order.application.dto.OrderItemDTO;
import br.com.ambev.order.application.mapper.OrderMapper;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.usecase.CreateOrderUseCase;
import br.com.ambev.order.usecase.ExistsByOrderNumberUseCase;
import br.com.ambev.order.usecase.OrderIdGeneratorUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ProcessOrderUseCaseImplTest {

    @Mock
    private OrderIdGeneratorUseCase orderIdGeneratorUseCase;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private CreateOrderUseCase createOrderUseCase;

    @Mock
    private ExistsByOrderNumberUseCase existsByOrderNumberUseCase;

    @InjectMocks
    private ProcessOrderUseCaseImpl processOrderUseCase;

    private OrderDTO orderDTO;
    private BigInteger generatedOrderId;
    private CustomerDTO customer;
    private List<OrderItemDTO> items;

    @BeforeEach
    void setUp() {
        customer = new CustomerDTO();
        customer.setCustomerId(12345L);

        OrderItemDTO item1 = new OrderItemDTO();
        item1.setUnitPrice(new BigDecimal(100));
        item1.setQuantity(2);

        items = Arrays.asList(item1);

        orderDTO = new OrderDTO();
        orderDTO.setCustomer(customer);
        orderDTO.setItems(items);
        orderDTO.setCreationDate(LocalDateTime.now());

        generatedOrderId = BigInteger.valueOf(9876543210L);
    }

    @Test
    void execute_shouldGenerateOrderIdAndCreateOrder_WhenOrderDoesNotExist() {
        when(orderIdGeneratorUseCase.execute(
                customer.getCustomerId(),
                orderDTO.getCreationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                items.size()))
                .thenReturn(generatedOrderId);

        when(existsByOrderNumberUseCase.execute(generatedOrderId)).thenReturn(false);

        Order order = new Order();
        when(orderMapper.convertToOrder(orderDTO)).thenReturn(order);

        processOrderUseCase.execute(orderDTO);

        assertEquals(generatedOrderId, orderDTO.getOrderNumber());

        verify(orderIdGeneratorUseCase).execute(
                customer.getCustomerId(),
                orderDTO.getCreationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                items.size());
        verify(existsByOrderNumberUseCase).execute(generatedOrderId);
        verify(orderMapper).convertToOrder(orderDTO);
        verify(createOrderUseCase).execute(order);
    }

    @Test
    void execute_shouldNotCreateOrder_WhenOrderAlreadyExists() {
        when(orderIdGeneratorUseCase.execute(
                customer.getCustomerId(),
                orderDTO.getCreationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                items.size()))
                .thenReturn(generatedOrderId);

        when(existsByOrderNumberUseCase.execute(generatedOrderId)).thenReturn(true);

        processOrderUseCase.execute(orderDTO);

        assertEquals(generatedOrderId, orderDTO.getOrderNumber());

        verify(orderIdGeneratorUseCase).execute(
                customer.getCustomerId(),
                orderDTO.getCreationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                items.size());
        verify(existsByOrderNumberUseCase).execute(generatedOrderId);

        verify(orderMapper, org.mockito.Mockito.never()).convertToOrder(orderDTO);
        verify(createOrderUseCase, org.mockito.Mockito.never()).execute(org.mockito.Mockito.any());
    }
}
