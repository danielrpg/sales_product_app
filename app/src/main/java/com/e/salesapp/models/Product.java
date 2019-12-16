package com.e.salesapp.models;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String productName;
    private int quantity;
    /*TODO modify database
    private boolean available;
    private int requiredQuantity;*/
    private Double price;
    private String category;

    public Product(String productName, int quantity, Double price, String category) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
