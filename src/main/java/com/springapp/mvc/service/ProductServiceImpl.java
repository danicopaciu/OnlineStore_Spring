package com.springapp.mvc.service;

import com.springapp.mvc.model.Product;

import java.util.List;

/**
 * Created by Daniel.Copaciu on 8/5/2015.
 */
public class ProductServiceImpl implements ProductService {

    @Override
    public boolean addProduct(Product p) {
        return false;
    }

    @Override
    public boolean updateProduct(Product p) {
        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {
        return false;
    }

    @Override
    public Product viewProduct(int productId) {
        return null;
    }

    @Override
    public List<Product> viewAllProducts() {
        return null;
    }
}
