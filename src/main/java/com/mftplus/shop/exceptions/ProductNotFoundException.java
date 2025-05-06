package com.mftplus.shop.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
        super("Product not found: ");
    }
}
