package br.com.ambev.order.application.mapper;

import br.com.ambev.order.application.dto.DeliveryAddressDTO;
import br.com.ambev.order.domain.model.DeliveryAddress;

public class DeliveryAddressMapper {

    public DeliveryAddress convert(DeliveryAddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        return DeliveryAddress.builder()
                .addressDescription(addressDTO.getAddressDescription())
                .cep(addressDTO.getCep())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .number(addressDTO.getNumber())
                .contactPhone(addressDTO.getContactPhone())
                .recipientName(addressDTO.getRecipientName())
                .build();
    }

}
