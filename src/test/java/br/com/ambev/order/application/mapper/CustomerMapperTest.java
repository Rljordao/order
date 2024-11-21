package br.com.ambev.order.application.mapper;


import br.com.ambev.order.application.dto.CustomerDTO;
import br.com.ambev.order.domain.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class CustomerMapperTest {

    @InjectMocks
    private CustomerMapper customerMapper;

    @Test
    void convertToCustomer_shouldConvertDTOToCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setCustomerId(12345L);
        customerDTO.setDocumentNumber("12345678901");
        customerDTO.setEmail("customer@example.com");
        customerDTO.setName("João Silva");

        Customer result = customerMapper.convert(customerDTO);

        assertEquals(customerDTO.getId(), result.getId());
        assertEquals(customerDTO.getCustomerId(), result.getCustomerId());
        assertEquals(customerDTO.getDocumentNumber(), result.getDocumentNumber());
        assertEquals(customerDTO.getEmail(), result.getEmail());
        assertEquals(customerDTO.getName(), result.getName());
    }

    @Test
    void convertToCustomer_shouldReturnNull_whenCustomerDTOIsNull() {
        Customer result = customerMapper.convert(null);
        assertNull(result);
    }
}
