package com.springapp.mvc.dao;

import com.springapp.mvc.model.Product;

import java.util.List;

/**
 * Created by Daniel.Copaciu on 8/5/2015.
 */
public interface ProductDao {

    public void create ();
    public boolean addProduct(Product p);
    public List<Product> getProductList();
}
