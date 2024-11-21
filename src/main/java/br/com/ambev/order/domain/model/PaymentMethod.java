package br.com.ambev.order.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "payment_methods")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethod {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = true, updatable = true)
    private Order order;

    @Column(name = "total_value", nullable = false)
    private Double totalValue;

    @Column(name = "payment_date")
    private String paymentDate;

    @Column(name = "payment_method_id")
    private Integer paymentMethodId;

    @Column(name = "card_id")
    private String cardId;

    @Column(name = "number_card")
    private String numberCard;

    @Column(name = "acquirer_name")
    private String acquirerName;

}
