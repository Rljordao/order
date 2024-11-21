package br.com.ambev.order.application.dto;


import java.time.LocalDateTime;

public class HistoricStatusDTO {

    private Long id;

    private OrderDTO order;

    private OrderStatusDTO status;

    private String description;

    private Long operatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
