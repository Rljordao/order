package br.com.ambev.order.application.mapper;

import br.com.ambev.order.application.dto.OrderDTO;
import br.com.ambev.order.domain.model.Order;

public class OrderMapper {

    private final OrderStatusMapper orderStatusMapper;
    private final CustomerMapper customerMapper;
    private final DeliveryAddressMapper deliveryAddressMapper;
    private final PaymentMethodMapper paymentMethodMapper;
    private final OrderItemMapper orderItemMapper;

    public OrderMapper(OrderStatusMapper orderStatusMapper, CustomerMapper customerMapper, DeliveryAddressMapper deliveryAddressMapper, PaymentMethodMapper paymentMethodMapper, OrderItemMapper orderItemMapper) {
        this.orderStatusMapper = orderStatusMapper;
        this.customerMapper = customerMapper;
        this.deliveryAddressMapper = deliveryAddressMapper;
        this.paymentMethodMapper = paymentMethodMapper;
        this.orderItemMapper = orderItemMapper;
    }

    public Order convertToOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }
        var order =  Order.builder()
                .id(orderDTO.getId())
                .orderNumber(orderDTO.getOrderNumber())
                .independentKey(orderDTO.getIndependentKey())
                .modificationDate(orderDTO.getModificationDate())
                .creationDate(orderDTO.getCreationDate())
                .totalValue(orderDTO.getTotalValue())
                .itemsTotalValue(orderDTO.getItemsTotalValue())
                .itemsDiscountTotalValue(orderDTO.getItemsDiscountTotalValue())
                .billingDate(orderDTO.getBillingDate())
                .dateSale(orderDTO.getDateSale())
                .status(orderStatusMapper.convertToOrderStatus(orderDTO.getStatus()))
                .customer(customerMapper.convert(orderDTO.getCustomer()))
                .deliveryAddress(deliveryAddressMapper.convert(orderDTO.getDeliveryAddress()))
                .build();
        var payment = paymentMethodMapper.convert(orderDTO.getPaymentMethods(), order);
        var items = orderItemMapper.convert(orderDTO.getItems(), order);
        order.setItems(items);
        order.setPaymentMethods(payment);
        return order;
    }



}

