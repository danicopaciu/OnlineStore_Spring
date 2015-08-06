package com.springapp.mvc.service;

import com.springapp.mvc.dao.ProductDao;
import com.springapp.mvc.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public void addProduct(String name, double price, int quantity) {
        Product p = new Product(name, price, quantity);
        productDao.addProduct(p);
    }

    @Override
    @Transactional
    public void updateProduct(int id, String name, double price, int quantity) {
        Product p = new Product(id, name, price, quantity);
        productDao.updateProduct(p);

    }

    @Override
    @Transactional
    public void deleteProduct(int productId) {
        productDao.deleteProduct(productId);
    }

    @Override
    @Transactional
    public Product viewProduct(int productId) {
        return productDao.viewProduct(productId);
    }

    @Override
    @Transactional
    public List<Product> viewAllProducts() {
        return productDao.viewAllProducts();
    }
}
