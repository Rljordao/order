package br.com.ambev.order.interfaces.api.controller;

import br.com.ambev.openapi.api.OrdersApi;
import br.com.ambev.openapi.model.OrderModel;
import br.com.ambev.order.interfaces.api.controller.mapper.OrderMapper;
import br.com.ambev.order.usecase.FindOrderByOrderNumberUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;


@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController implements OrdersApi {

    private final FindOrderByOrderNumberUseCase  findOrderByOrderNumberUseCase;
    private final OrderMapper orderMapper;

    @Override
    public ResponseEntity<OrderModel> getOrder(String orderNumber) {
        var response = findOrderByOrderNumberUseCase.execute(new BigInteger(orderNumber));
        return ResponseEntity.ok(orderMapper.toModel(response));
    }
}
