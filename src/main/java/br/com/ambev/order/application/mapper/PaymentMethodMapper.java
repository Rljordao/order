package br.com.ambev.order.application.mapper;

import br.com.ambev.order.application.dto.PaymentMethodDTO;
import br.com.ambev.order.domain.model.Order;
import br.com.ambev.order.domain.model.PaymentMethod;

import java.util.List;

public class PaymentMethodMapper {

    public PaymentMethod convert(PaymentMethodDTO paymentMethodsDTO, Order order) {
        if (paymentMethodsDTO == null) {
            return null;
        }
        return PaymentMethod.builder()
                .paymentMethodId(paymentMethodsDTO.getPaymentMethodId())
                .paymentDate(paymentMethodsDTO.getPaymentDate())
                .acquirerName(paymentMethodsDTO.getAcquirerName())
                .cardId(paymentMethodsDTO.getCardId())
                .id(paymentMethodsDTO.getId())
                .numberCard(paymentMethodsDTO.getNumberCard())
                .totalValue(paymentMethodsDTO.getTotalValue())
                .acquirerName(paymentMethodsDTO.getAcquirerName())
                .order(order)
                .build();
    }


    public List<PaymentMethod> convert(List<PaymentMethodDTO> paymentMethodDTOList, Order order) {
        return paymentMethodDTOList.stream()
                .map(payment -> convert(payment, order)).toList();
    }
}
