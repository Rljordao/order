package br.com.ambev.order.application.gateway;


import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;


import java.math.BigInteger;


public interface OrderGateway {

    Order fetchOrderById(Long id);

    Order saveOrder(Order order);

    void deleteOrder(Long id);

    Boolean existsByOrderNumber(BigInteger orderNumber);

    Order findOrderByOrderNumber(BigInteger orderNumber);

    void setOrderStatus(Order order, OrderStatusEnum statusEnum);
}
