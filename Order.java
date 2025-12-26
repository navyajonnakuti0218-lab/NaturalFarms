package com.example.naturalfarm.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private String orderId;
    private List<CartItem> items;
    private String paymentMethod;
    private String orderDate;

    public Order(String orderId, List<CartItem> items, String paymentMethod) {
        this.orderId = orderId;
        this.items = items;
        this.paymentMethod = paymentMethod;
        this.orderDate = LocalDate.now().toString();
    }

    public String getOrderId() { return orderId; }
    public List<CartItem> getItems() { return items; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getOrderDate() { return orderDate; }
}