package br.com.ambev.order.interfaces.api.controller.mapper;

import br.com.ambev.openapi.model.OrderModel;
import br.com.ambev.order.application.dto.OrderDTO;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.enums.OrderStatusEnum;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", uses = {OrderItemMapper.class, PaymentMethodMapper.class})
public interface OrderMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "items", source = "items")
    @Mapping(target = "paymentMethods", source = "paymentMethods")
    OrderDTO toDTO(OrderModel model);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "items", source = "items")
    @Mapping(target = "paymentMethods", source = "paymentMethods")
    OrderModel toModel(Order dto);


    @Named("toEnum")
    default OrderStatusEnum toEnum(String status) {
        return status != null ? OrderStatusEnum.valueOf(status.toUpperCase()) : null;
    }

    @Named("toString")
    default String toString(OrderStatusEnum status) {
        return status != null ? status.name().toLowerCase() : null;
    }

}


