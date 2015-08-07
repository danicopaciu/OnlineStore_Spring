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

    @Override
    @Transactional
    public Product getRequestedProduct(int productId, int quantity) {
        Product p = productDao.viewProduct(productId);
        int prevQuantity = p.getQuantity();
        if(prevQuantity - quantity >= 0){
            p.setQuantity(prevQuantity - quantity);
            return new Product(p.getId(), p.getName(), p.getPrice(), quantity);
        }
        return null;
    }

    @Override
    public boolean existsProduct(List<Product> productList, Product p){
        for (Product aProduct: productList){
            if(aProduct.getId() == p.getId()){
                aProduct.setQuantity(aProduct.getQuantity() + p.getQuantity());
                return true;
            }
        }
        return false;
    }

    private void updateProductQuantity(Product aProduct) {
        Product persistentProduct = productDao.viewProduct(aProduct.getId());
        persistentProduct.setQuantity(persistentProduct.getQuantity() + aProduct.getQuantity());
    }

    @Override
    @Transactional
    public void removeProductFromCart(List<Product> productList, int productId) {
        if (productList != null){
            for (Product aProduct : productList){
                if (aProduct.getId() == productId){
                    updateProductQuantity(aProduct);
                    productList.remove(aProduct);
                    break;
                }
            }
        }
    }

    @Override
    @Transactional
    public void clearCart(List<Product> productList) {
        if(productList != null){
            for(Product aProduct : productList){
                updateProductQuantity(aProduct);
            }
            productList.clear();
        }
    }


}
