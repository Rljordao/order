package br.com.ambev.order.interfaces.api.controller.mapper;

import br.com.ambev.openapi.model.OrderItemModel;
import br.com.ambev.order.application.dto.OrderItemDTO;
import br.com.ambev.order.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",  uses = { DiscountMapper.class})
public interface OrderItemMapper {

    @Mapping(target = "order", ignore = true)
    OrderItemDTO toDTO(OrderItemModel model);

    OrderItemModel toModel(OrderItem dto);
}

