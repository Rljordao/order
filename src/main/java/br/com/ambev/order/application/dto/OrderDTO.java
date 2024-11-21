package br.com.ambev.order.application.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    private Long id;

    private BigInteger orderNumber;

    private String independentKey;

    private LocalDateTime modificationDate;

    private LocalDateTime creationDate;

    private OrderStatusDTO status;

    private CustomerDTO customer;

    private DeliveryAddressDTO deliveryAddress;

    private List<PaymentMethodDTO> paymentMethods;

    private List<OrderItemDTO> items;

    private BigDecimal totalValue;

    private BigDecimal itemsTotalValue;

    private BigDecimal itemsDiscountTotalValue;

    private LocalDateTime billingDate;

    private LocalDateTime dateSale;

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setOrderNumber(BigInteger orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getIndependentKey() {
        return independentKey;
    }

    public void setIndependentKey(String independentKey) {
        this.independentKey = independentKey;
    }

    public BigInteger getOrderNumber() {
        return orderNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public OrderStatusDTO getStatus() {
        return status;
    }

    public void setStatus(OrderStatusDTO status) {
        this.status = status;
    }

    public DeliveryAddressDTO getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddressDTO deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<PaymentMethodDTO> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethodDTO> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getItemsTotalValue() {
        return itemsTotalValue;
    }

    public void setItemsTotalValue(BigDecimal itemsTotalValue) {
        this.itemsTotalValue = itemsTotalValue;
    }

    public BigDecimal getItemsDiscountTotalValue() {
        return itemsDiscountTotalValue;
    }

    public void setItemsDiscountTotalValue(BigDecimal itemsDiscountTotalValue) {
        this.itemsDiscountTotalValue = itemsDiscountTotalValue;
    }

    public LocalDateTime getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDateTime billingDate) {
        this.billingDate = billingDate;
    }

    public LocalDateTime getDateSale() {
        return dateSale;
    }

    public void setDateSale(LocalDateTime dateSale) {
        this.dateSale = dateSale;
    }
}
