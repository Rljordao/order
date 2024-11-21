package br.com.ambev.order.application.usecaseImpl;

import br.com.ambev.order.application.gateway.OrderGateway;
import br.com.ambev.order.domain.exception.OrderValidationException;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;
import br.com.ambev.order.usecase.CalculateOrderTotalUseCase;
import br.com.ambev.order.usecase.CreateOrderUseCase;
import br.com.ambev.order.usecase.FindAndSetOrderStatusUseCase;
import br.com.ambev.order.usecase.ValidateOrderValueUseCase;

import static java.util.Objects.isNull;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final ValidateOrderValueUseCase validateOrderValueUseCase;
    private final CalculateOrderTotalUseCase calculateOrderTotalUseCase;
    private final OrderGateway orderGateway;
    private final FindAndSetOrderStatusUseCase findAndSetOrderStatusUseCase;

    public CreateOrderUseCaseImpl(ValidateOrderValueUseCase validateOrderValueUseCase, CalculateOrderTotalUseCase calculateOrderTotalUseCase, OrderGateway orderGateway, FindAndSetOrderStatusUseCase findAndSetOrderStatusUseCase) {
        this.validateOrderValueUseCase = validateOrderValueUseCase;
        this.calculateOrderTotalUseCase = calculateOrderTotalUseCase;
        this.orderGateway = orderGateway;
        this.findAndSetOrderStatusUseCase = findAndSetOrderStatusUseCase;
    }

    @Override
    public Order execute(Order order) {
        validateOrder(order);
        calculateOrderTotalUseCase.execute(order);
        findAndSetOrderStatusUseCase.execute(order, OrderStatusEnum.CALCULATED);
        validateOrderValueUseCase.execute(order);

        return orderGateway.saveOrder(order);
    }



    public void validateOrder(Order order) {
        if (isNull(order.getItems()) || order.getItems().isEmpty()) {
            throw new OrderValidationException("O pedido deve ter pelo menos um item");
        }
    }
}

