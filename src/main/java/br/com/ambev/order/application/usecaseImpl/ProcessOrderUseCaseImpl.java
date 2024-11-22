package br.com.ambev.order.application.usecaseImpl;


import br.com.ambev.order.application.dto.OrderDTO;
import br.com.ambev.order.application.mapper.OrderMapper;
import br.com.ambev.order.usecase.CreateOrderUseCase;
import br.com.ambev.order.usecase.ExistsByOrderNumberUseCase;
import br.com.ambev.order.usecase.OrderIdGeneratorUseCase;
import br.com.ambev.order.usecase.ProcessOrderUseCase;

import java.math.BigInteger;
import java.time.ZoneId;

public class ProcessOrderUseCaseImpl implements ProcessOrderUseCase {


    private final OrderIdGeneratorUseCase orderIdGeneratorUseCase;
    private final OrderMapper orderMapper;
    private final CreateOrderUseCase createOrderUseCase;
    private final ExistsByOrderNumberUseCase existsByOrderNumberUseCase;

    public ProcessOrderUseCaseImpl(OrderIdGeneratorUseCase orderIdGeneratorUseCase, OrderMapper orderMapper, CreateOrderUseCase createOrderUseCase, ExistsByOrderNumberUseCase existsByOrderNumberUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.orderIdGeneratorUseCase = orderIdGeneratorUseCase;
        this.orderMapper = orderMapper;
        this.existsByOrderNumberUseCase = existsByOrderNumberUseCase;
    }

    @Override
    public void execute(OrderDTO orderDTO) {
        long creationDateLong = orderDTO.getCreationDate()
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
        BigInteger orderId = orderIdGeneratorUseCase.execute(orderDTO.getCustomer().getCustomerId(), creationDateLong, orderDTO.getItems().size());
        orderDTO.setOrderNumber(orderId);
        if (!existsByOrderNumberUseCase.execute(orderId))
            createOrderUseCase.execute(orderMapper.convertToOrder(orderDTO));
    }
}
