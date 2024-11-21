package br.com.ambev.order.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderStatusNotFoundException extends RuntimeException {

    public OrderStatusNotFoundException(String status) {
        super("Status do pedido não encontrado: " + status);
    }
}