package br.com.ambev.order.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DiscountExceedsTotalAmountException extends RuntimeException {

    public DiscountExceedsTotalAmountException(String message) {
        super(message);
    }

    public DiscountExceedsTotalAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
