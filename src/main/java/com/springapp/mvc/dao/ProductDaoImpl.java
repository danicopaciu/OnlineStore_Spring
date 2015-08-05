package com.springapp.mvc.dao;

import com.springapp.mvc.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel.Copaciu on 8/5/2015.
 */
public class ProductDaoImpl implements ProductDao{

    private List<Product> productList;

    public void create (){
        productList = new ArrayList<Product>();
        for(int i = 0; i < 3; i++){
            productList.add(new Product(i, "Product" + i, 4.0, 2));
        }
    }

    public boolean addProduct(Product p){
        if(productList != null){
            int id = productList.size();
            p.setId(id);
            return productList.add(p);
        }
        return false;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
