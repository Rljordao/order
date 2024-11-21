package br.com.ambev.order.application.usecaseImpl;

import br.com.ambev.order.application.gateway.OrderStatusGateway;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;
import br.com.ambev.order.usecase.FindAndSetOrderStatusUseCase;

public class FindAndSetOrderStatusUseCaseImpl implements FindAndSetOrderStatusUseCase {

    private final OrderStatusGateway orderStatusGateway;

    public FindAndSetOrderStatusUseCaseImpl(OrderStatusGateway orderStatusGateway) {
        this.orderStatusGateway = orderStatusGateway;
    }

    @Override
    public void execute(Order order, OrderStatusEnum statusEnum) {
        var newStatus = orderStatusGateway.getStatus(statusEnum);
        order.setStatus(newStatus);
    }
}
