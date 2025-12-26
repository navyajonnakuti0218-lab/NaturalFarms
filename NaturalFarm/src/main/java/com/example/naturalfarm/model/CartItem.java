package com.example.naturalfarm.model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    // --- THIS IS THE MISSING METHOD THAT CAUSED THE ERROR ---
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}