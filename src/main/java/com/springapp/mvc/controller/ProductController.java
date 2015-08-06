package com.springapp.mvc.controller;

import com.springapp.mvc.model.Product;
import com.springapp.mvc.service.ProductService;
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
    private ProductService productService;

    @RequestMapping(value = "/admin/AddProduct", method = RequestMethod.GET)
    public String printAddProduct(ModelMap model) {
        getProductList(model);
        return "add_product";
    }

    @RequestMapping(value = "/admin/AddProduct", method = RequestMethod.POST)
    public String addProduct(@RequestParam(value = "product_name") String name,
                             @RequestParam(value = "product_price") double price,
                             @RequestParam(value = "product_quantity") int quantity,
                             ModelMap model) {
        productService.addProduct(name, price, quantity);
        getProductList(model);
        return "add_product";
    }

    @RequestMapping(value = "/admin/DeleteProduct", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam(value = "productId") int productId, @RequestParam(value = "page_name") String path,
                                ModelMap model) {
        productService.deleteProduct(productId);
        getProductList(model);
        return getViewName(path);
    }

    @RequestMapping(value = "/admin/UpdateProduct", method = RequestMethod.GET)
    public String printUpdateProduct(@RequestParam(value = "productId") int productId,
                                     ModelMap model) {
        model.addAttribute("productId", productId);
        return "update_product";
    }

    @RequestMapping(value = "/admin/UpdateProduct", method = RequestMethod.POST)
    public String updateProduct(@RequestParam(value = "product_id") int id,
                                @RequestParam(value = "product_name") String name,
                                @RequestParam(value = "product_price") double price,
                                @RequestParam(value = "product_quantity") int quantity,
                                ModelMap model) {

        productService.updateProduct(id, name, price, quantity);
        model.addAttribute("msg", "Product " + name + " was successfully updated");
        getProductList(model);
        return "admin";
    }

    @RequestMapping(value = "/admin/ProductList", method = RequestMethod.GET)
    public String printProductList(ModelMap model) {
        getProductList(model);
        return "admin";
    }

    private List<Product> getProductList(ModelMap model) {
        List<Product> productList = productService.viewAllProducts();
        if (productList != null) {
            model.addAttribute("productList", productList);
        }
        return productList;
    }

    private String getViewName(String path) {
        String[] pathList = path.split("/");
        String pageNameExtension = pathList[pathList.length - 1];
        String[] pageNameExtensionArray = pageNameExtension.split("\\.");
        return pageNameExtensionArray[0];
    }
}
