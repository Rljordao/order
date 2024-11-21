package br.com.ambev.order.interfaces.api.controller.mapper;

import br.com.ambev.openapi.model.PaymentMethodModel;
import br.com.ambev.order.application.dto.PaymentMethodDTO;
import br.com.ambev.order.domain.model.PaymentMethod;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "order", ignore = true)
    PaymentMethodDTO toDTO(PaymentMethodModel model);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PaymentMethodModel toModel(PaymentMethod paymentMethod);
}
