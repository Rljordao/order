package br.com.ambev.order.interfaces.api.controller.mapper;


import br.com.ambev.openapi.model.DiscountModel;
import br.com.ambev.order.domain.model.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiscountMapper {

    @Mapping(target = "order", ignore = true)
    DiscountModel toModel(Discount discount);
}
