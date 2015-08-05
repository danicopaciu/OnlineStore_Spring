package com.springapp.mvc.controller;

import com.springapp.mvc.dao.ProductDao;
import com.springapp.mvc.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/AddProduct", method = RequestMethod.GET)
    public String printAddProduct(ModelMap model){
        List<Product> productList = productDao.getProductList();
        if(productList == null || productList.isEmpty()){
            productDao.create();
        }
        model.addAttribute("productList", productDao.getProductList());
        return "add_product";
    }

    @RequestMapping(value = "/AddProduct", method = RequestMethod.POST)
    public String addProduct(@RequestParam (value = "product_name") String name,
                             @RequestParam (value = "product_price") double price,
                             @RequestParam (value = "product_quantity") int quantity,
                            ModelMap model){
        List<Product> productList = productDao.getProductList();
        if(productList != null){
            Product p = new Product(0, name, price, quantity);
            if(productDao.addProduct(p)){
                model.addAttribute("msg", "Product with ID: " + p.getId() + " was successfully added!");
            }
        }
        model.addAttribute("productList", productDao.getProductList());
        return "add_product";
    }
}
