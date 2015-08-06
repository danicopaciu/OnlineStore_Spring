package com.springapp.mvc.dao;

import com.springapp.mvc.model.Product;

import java.util.List;

/**
 * Created by Daniel.Copaciu on 8/5/2015.
 */
public interface ProductDao {

    public void create ();
    public List<Product> getProductList();
    public boolean addProduct(Product p);
    public boolean updateProduct(Product p);
    public boolean deleteProduct(int productId);
    public Product viewProduct(int productId);
    public List<Product> viewAllProducts();
}
