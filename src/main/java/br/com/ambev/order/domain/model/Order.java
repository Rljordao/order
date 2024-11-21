package br.com.ambev.order.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", nullable = false)
    private BigInteger orderNumber;

    @Column(name = "independent_key")
    private String independentKey;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modification_date")
    private LocalDateTime modificationDate;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private LocalDateTime creationDate;


    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = true)
    private OrderStatus status;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = true)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_address_id", referencedColumnName = "id", nullable = true)
    private DeliveryAddress deliveryAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<PaymentMethod> paymentMethods;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Column(name = "items_total_value")
    private BigDecimal itemsTotalValue;

    @Column(name = "items_discount_total_value")
    private BigDecimal itemsDiscountTotalValue;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "billing_date")
    private LocalDateTime billingDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_sale_date")
    private LocalDateTime dateSale;

}
