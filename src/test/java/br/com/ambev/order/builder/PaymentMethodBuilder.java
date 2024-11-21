package br.com.ambev.order.builder;

import br.com.ambev.order.domain.model.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentMethodBuilder {
    public static PaymentMethod paymentMethodBuild() {
        return PaymentMethod.builder()
                .id(1L)
                .paymentMethodId(2)
                .paymentDate(LocalDate.now().toString())
                .acquirerName("Acquirer A")
                .cardId("1234567890123456")
                .numberCard("1234-5678-9876-5432")
                .totalValue(new BigDecimal("150.00").doubleValue())
                .build();
    }

}
