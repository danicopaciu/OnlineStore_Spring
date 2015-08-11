package persistence.dao;

import persistence.model.Product;

import java.util.List;

/**
 * Created by Daniel.Copaciu on 8/5/2015.
 */
public interface ProductDao {

    public void addProduct(Product p);

    public void updateProduct(Product p);

    public void deleteProduct(int productId);

    public Product viewProduct(int productId);

    public List<Product> viewAllProducts();
}
