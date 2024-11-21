package br.com.ambev.order.application.mapper;

import br.com.ambev.order.application.dto.DiscountDTO;
import br.com.ambev.order.domain.model.Discount;
import br.com.ambev.order.domain.model.Order;

import java.time.LocalDateTime;

public class DiscountMapper {

    public Discount convert(DiscountDTO discountDTO, Order order) {
        if (discountDTO == null) {
            return null;
        }

        return Discount.builder()
                .order(order)
                .discountValue(discountDTO.getDiscountValue())
                .percentageDiscount(discountDTO.getPercentageDiscount())
                .discountType(discountDTO.getDiscountType())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
