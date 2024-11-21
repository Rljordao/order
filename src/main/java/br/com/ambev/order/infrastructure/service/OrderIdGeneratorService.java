package br.com.ambev.order.infrastructure.service;


import br.com.ambev.order.application.gateway.OrderIdGeneratorGateway;
import br.com.ambev.order.domain.exception.OrderIdGenerationException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class OrderIdGeneratorService implements OrderIdGeneratorGateway {

    @Override
    public BigInteger generateOrderId(long customerId, long timestamp, int size) {
        try {
            String input = customerId + "|" + timestamp + "|" + size;

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());


            BigInteger orderId = new BigInteger(1, hashBytes);

            BigInteger maxOrderId = new BigInteger("99999999999999999999999999999999999999");
            orderId = orderId.mod(maxOrderId);

            return orderId;
        } catch (NoSuchAlgorithmException e) {
            throw new OrderIdGenerationException("Erro ao gerar o hash do ID do pedido", e);
        }
    }
}


