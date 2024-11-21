package br.com.ambev.order.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Erro ao gerar o ID do pedido")
public class OrderIdGenerationException extends RuntimeException {

    public OrderIdGenerationException(String message) {
        super(message);
    }

    public OrderIdGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}

