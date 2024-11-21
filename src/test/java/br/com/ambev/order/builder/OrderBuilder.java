package br.com.ambev.order.builder;

import br.com.ambev.order.application.dto.OrderDTO;
import br.com.ambev.order.domain.model.Order;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderBuilder {
    public static Order orderBuild() {
        return Order.builder()
                .id(1L)
                .orderNumber(new BigInteger("76543456"))
                .totalValue(new BigDecimal("200.00"))
                .itemsTotalValue(new BigDecimal("180.00"))
                .itemsDiscountTotalValue(new BigDecimal("10.00"))
                .creationDate(LocalDateTime.now())
                .modificationDate(LocalDateTime.now())
                .billingDate(LocalDateTime.now())
                .dateSale(LocalDateTime.now())
                .status(OrderStatusBuilder.orderStatusBuild())
                .customer(CustomerBuilder.customerBuild())
                .deliveryAddress(DeliveryAddressBuilder.deliveryAddressBuild())
                .paymentMethods(Arrays.asList(PaymentMethodBuilder.paymentMethodBuild()))
                .items(new ArrayList<>(Arrays.asList(OrderItemBuilder.orderItemBuild(), OrderItemBuilder.orderItemBuild())))
                .build();
    }

}
