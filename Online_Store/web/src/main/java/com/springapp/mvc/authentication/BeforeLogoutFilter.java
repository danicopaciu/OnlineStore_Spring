package com.springapp.mvc.authentication;

import persistence.model.Product;
import service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by daniel.copaciu on 8/7/2015.
 */
public class BeforeLogoutFilter implements Filter {

    @Autowired
    private ProductService productService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart != null) {
            if (cart.size() != 0) {
                productService.clearCart(cart);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
