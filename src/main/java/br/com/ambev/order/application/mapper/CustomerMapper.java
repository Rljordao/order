package br.com.ambev.order.application.mapper;

import br.com.ambev.order.application.dto.CustomerDTO;
import br.com.ambev.order.domain.model.Customer;

public class CustomerMapper {

    public Customer convert(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }
        return Customer.builder()
                .id(customerDTO.getId())
                .customerId(customerDTO.getCustomerId())
                .documentNumber(customerDTO.getDocumentNumber())
                .email(customerDTO.getEmail())
                .name(customerDTO.getName())
                .build();
    }
}
