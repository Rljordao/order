package br.com.ambev.order.application.mapper;

import br.com.ambev.order.application.dto.OrderItemDTO;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.OrderItem;

import java.util.List;

public class OrderItemMapper {

    private final DiscountMapper discountMapper;

    public OrderItemMapper(DiscountMapper discountMapper) {
        this.discountMapper = discountMapper;
    }

    public OrderItem convert(OrderItemDTO orderItemDTO, Order order) {
        if (orderItemDTO == null) {
            return null;
        }
        return OrderItem.builder()
                .totalItem(orderItemDTO.getTotalItem())
                .quantity(orderItemDTO.getQuantity())
                .unitPrice(orderItemDTO.getUnitPrice())
                .totalItem(orderItemDTO.getTotalItem())
                .productId(orderItemDTO.getProductId())
                .productName(orderItemDTO.getProductName())
                .id(orderItemDTO.getId())
                .order(order)
                .discount(discountMapper.convert(orderItemDTO.getDiscount(), order))
                .build();
    }

    public List<OrderItem> convert(List<OrderItemDTO> orderItemDTOList,  Order order) {
        return orderItemDTOList.stream()
                .map(item -> convert(item, order)).toList();
    }
}
