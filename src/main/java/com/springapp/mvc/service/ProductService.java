package com.springapp.mvc.service;

import com.springapp.mvc.model.Product;

import java.util.List;

public interface ProductService {

    public void addProduct(String name, double price, int quantity);

    public void updateProduct(int id, String name, double price, int quantity);

    public void deleteProduct(int productId);

    public Product viewProduct(int productId);

    public List<Product> viewAllProducts();
}
