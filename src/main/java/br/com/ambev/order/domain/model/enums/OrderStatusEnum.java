package br.com.ambev.order.domain.model.enums;

import java.util.Arrays;

public enum OrderStatusEnum {

    READY_FOR_BILLING(1, "READY FOR BILLING"),
    WAITING_FOR_BILLING(2, "WAITING FOR BILLING"),
    WAITING_FOR_CREDIT_ANALYSIS_RETURN(3, "WAITING FOR CREDIT ANALYSIS RETURN"),
    WAITING_FOR_GOODS_SEPARATION(4, "WAITING FOR GOODS SEPARATION"),
    INVOICE_ISSUED(5, "INVOICE ISSUED"),
    INVOICED(7, "INVOICED"),
    DELIVERED(8, "DELIVERED"),
    WAITING_FOR_PAYMENT_CAPTURE(9, "WAITING FOR PAYMENT CAPTURE"),
    WAITING_FOR_PAYMENT_CONFIRMATION(10, "WAITING FOR PAYMENT CONFIRMATION"),
    ORDER_SEPARATED(12, "ORDER SEPARATED"),
    WAITING_FOR_INVOICE_RETURN(13, "WAITING FOR INVOICE RETURN"),
    CANCELLED(14, "CANCELADO"),
    CALCULATED(15, "CALCULADO");

    private final int statusId;
    private final String name;

    OrderStatusEnum(int statusId, String name) {
        this.statusId = statusId;
        this.name = name;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getName() {
        return name;
    }


    public static OrderStatusEnum fromStatusId(int statusId) {
        return Arrays.stream(OrderStatusEnum.values())
                .filter(status -> status.getStatusId() == statusId)
                .findFirst().orElse(null);
    }
}
