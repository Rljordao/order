package br.com.ambev.order.application.mapper;

import br.com.ambev.order.application.dto.DeliveryAddressDTO;
import br.com.ambev.order.domain.model.DeliveryAddress;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class DeliveryAddressMapperTest {

    @InjectMocks
    private DeliveryAddressMapper deliveryAddressMapper;

    @Test
    void convertToDeliveryAddress_shouldConvertDTOToDeliveryAddress() {
        DeliveryAddressDTO addressDTO = new DeliveryAddressDTO();
        addressDTO.setAddressDescription("Rua A, 123");
        addressDTO.setCep("12345-678");
        addressDTO.setCity("São Paulo");
        addressDTO.setState("SP");
        addressDTO.setNumber("123");
        addressDTO.setContactPhone("11987654321");
        addressDTO.setRecipientName("João Silva");

        DeliveryAddress result = deliveryAddressMapper.convert(addressDTO);

        assertEquals(addressDTO.getAddressDescription(), result.getAddressDescription());
        assertEquals(addressDTO.getCep(), result.getCep());
        assertEquals(addressDTO.getCity(), result.getCity());
        assertEquals(addressDTO.getState(), result.getState());
        assertEquals(addressDTO.getNumber(), result.getNumber());
        assertEquals(addressDTO.getContactPhone(), result.getContactPhone());
        assertEquals(addressDTO.getRecipientName(), result.getRecipientName());
    }

    @Test
    void convertToDeliveryAddress_shouldReturnNull_whenAddressDTOIsNull() {
        DeliveryAddress result = deliveryAddressMapper.convert(null);
        assertNull(result);
    }
}
