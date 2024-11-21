package br.com.ambev.order.builder;

import br.com.ambev.order.domain.model.Discount;

import java.math.BigDecimal;

public class DiscountBuilder {
    public static Discount discountBuild() {
        return Discount.builder()
                .discountValue(new BigDecimal("10.00"))
                .percentageDiscount(new BigDecimal("5.00"))
                .discountType("PERCENTAGE")
                .build();
    }

}
