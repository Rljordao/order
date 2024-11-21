package br.com.ambev.order.application.dto;


import br.com.ambev.order.application.dto.enums.OrderStatusEnum;

public class OrderStatusDTO {

    private Long id;

    private OrderStatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
}
