package com.springapp.mvc.service;

import com.springapp.mvc.model.Product;

import java.util.List;

public interface ProductService {

    public void addProduct(String name, double price, int quantity);

    public void updateProduct(int id, String name, double price, int quantity);

    public void deleteProduct(int productId);

    public Product viewProduct(int productId);

    public List<Product> viewAllProducts();

    public Product getRequestedProduct(int productId, int quantity);

    public boolean existsProduct(List<Product> productList, Product p);

    public void removeProductFromCart(List<Product> productList, int productId);

    public void clearCart(List<Product> productList);
}
