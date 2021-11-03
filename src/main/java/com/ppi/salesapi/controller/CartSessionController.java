package com.ppi.salesapi.controller;

import com.ppi.salesapi.model.Address;
import com.ppi.salesapi.model.Payment;
import com.ppi.salesapi.model.Product;
import com.ppi.salesapi.model.Sell;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/session")
public class CartSessionController {


    @GetMapping
    public Sell processSell(HttpSession session) {

        List<Product> storedProductList = (List<Product>) session.getAttribute("SESSION_PRODUCTS_DATA");
        Address address = (Address) session.getAttribute("SESSION_ADDRESS_DATA");
        Payment payment = (Payment) session.getAttribute("SESSION_PAYMENT_DATA");

        Sell sell = new Sell();
        sell.setProduct(storedProductList);
        sell.setAddress(address);
        sell.setPayment(payment);

        return sell;
    }


    @PostMapping("/persistProduct")
    public void persistProduct(@RequestBody Product product, HttpSession session) {

        Assert.notNull(product, "Product cannot be null");

        List<Product> storedProductList = (List<Product>) session.getAttribute("SESSION_PRODUCTS_DATA");

        if (storedProductList == null) {
            storedProductList = new ArrayList<>();
        }
        storedProductList.add(product);
        session.setAttribute("SESSION_PRODUCTS_DATA", storedProductList);

    }

    @PostMapping("/persistAddress")
    public void persistAddress(@RequestBody Address address, HttpSession session) {

        Assert.notNull(address, "address cannot be null");

        session.setAttribute("SESSION_ADDRESS_DATA", address);


    }

    @PostMapping("/removeProduct/{index}")
    public void removeProduct(@PathVariable int index, HttpSession session) {

        List<Product> productList = (List<Product>) session.getAttribute("SESSION_PRODUCTS_DATA");

        productList.remove(index);

        session.setAttribute("SESSION_PRODUCTS_DATA", productList);

    }

    @PostMapping("/persistPayment")
    public void persistPayment(@RequestBody Payment payment, HttpSession session) {

        Assert.notNull(payment, "payment cannot be null");

        session.setAttribute("SESSION_PAYMENT_DATA", payment);

    }

    @PostMapping("/destroy")
    public void destroySession(HttpServletRequest request) {
        request.getSession().invalidate();

    }

    @GetMapping("/getSession")
    public String getSession(HttpSession session) {

        return session.getId();

    }


}
