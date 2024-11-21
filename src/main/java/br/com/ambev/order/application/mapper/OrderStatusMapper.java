package br.com.ambev.order.application.mapper;

import br.com.ambev.order.application.dto.OrderStatusDTO;
import br.com.ambev.order.domain.model.OrderStatus;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;

import java.util.Objects;

public class OrderStatusMapper {

    public OrderStatus convertToOrderStatus(OrderStatusDTO statusDTO) {
        if (statusDTO == null) {
            return null;
        }
        return OrderStatus.builder()
                .status(Objects.nonNull(statusDTO.getId())?  OrderStatusEnum.fromStatusId(statusDTO.getId().intValue()): null)
                .id(statusDTO.getId())
                .build();
    }
}
