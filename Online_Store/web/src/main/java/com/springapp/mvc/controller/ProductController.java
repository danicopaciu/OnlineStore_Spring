package com.springapp.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import persistence.model.Product;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"cart"})
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
    public String deleteProduct(@RequestParam(value = "productId") int productId,
                                @RequestParam(value = "page_name") String path,
                                ModelMap model) {
        productService.deleteProduct(productId);
        getProductList(model);
        return getViewName(path);
    }

    @RequestMapping(value = "/admin/UpdateProduct", method = RequestMethod.GET)
    public String printUpdateProduct(@RequestParam(value = "productId") int productId,
                                     ModelMap model) {
        getProduct(productId, model);
        return "update_product";
    }

    private void getProduct(@RequestParam(value = "productId") int productId, ModelMap model) {
        Product p = productService.viewProduct(productId);
        model.addAttribute("product", p);
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
    public String printAdminProductList(ModelMap model) {
        getProductList(model);
        return "admin";
    }

    @RequestMapping(value = "/user/ProductList", method = RequestMethod.GET)
    public String printUserProductList(ModelMap model) {
        getProductList(model);
        return "user";
    }

    @RequestMapping(value = "/user/buyProduct", method = RequestMethod.GET)
    public String printBuyProduct(@RequestParam(value = "productId") int productId,
                                  ModelMap model) {
        if (!model.containsAttribute("cart")) {
            model.addAttribute("cart", new ArrayList<Product>());
        }
        getProduct(productId, model);
        return "buyProduct";
    }

    @RequestMapping(value = "/user/buyProduct", method = RequestMethod.POST)
    public String buyProduct(@RequestParam(value = "productId") int productId,
                             @RequestParam(value = "product_quantity") Integer quantity,
                             @ModelAttribute("cart") List<Product> cart,
                             ModelMap model) {
        Product p;
        if (quantity != null) {
            p = productService.getRequestedProduct(productId, quantity);
            if (p != null) {
                if (!productService.existsProduct(cart, p)) {
                    cart.add(p);
                }
            } else {
                putProductInModel(productId, model, "The requested quantity isn't available!");
                return "buyProduct";
            }
        } else {
            putProductInModel(productId, model, "Quantity field must be not empty!");
            return "buyProduct";
        }
        return "redirect:/user/ProductList";
    }

    private void putProductInModel(int productId, ModelMap model, String message) {
        Product p;
        p = productService.viewProduct(productId);
        model.addAttribute("product", p);
        model.addAttribute("msg", message);
    }

    @RequestMapping(value = "/user/ShoppingCart", method = RequestMethod.GET)
    public String viewShoppingCart() {
        return "shopping_cart";
    }

    @RequestMapping(value = "/user/DeleteFromCart", method = RequestMethod.GET)
    public String deleteFromCart(@ModelAttribute(value = "cart") List<Product> cart,
                                 @RequestParam(value = "productId") int productId) {
        productService.removeProductFromCart(cart, productId);
        return "redirect:/user/ShoppingCart";
    }

    @RequestMapping(value = "/user/DeleteChart", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String deleteCart(@ModelAttribute(value = "cart") List<Product> cart) {
        if (cart != null) {
            productService.clearCart(cart);
        }
        return "";
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
