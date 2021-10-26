package com.ppi.salesapi.controller;

import com.ppi.salesapi.model.Address;
import com.ppi.salesapi.model.ShopCart;
import com.ppi.salesapi.service.impl.ShopCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shopCart")
public class CartController {

    @Autowired
    private ShopCartServiceImpl shopCartService;

    @GetMapping(value = "/{id}")
    public ShopCart findById(@PathVariable("id") Long id) {

        return shopCartService.findShopCartById(id);

    }

    @PostMapping(value = "/addCart")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createShopCart() {

        return shopCartService.createShopCart();

    }

    @PostMapping(value = "/addProduct/{idCart}/{idProduct}")
    @ResponseStatus(HttpStatus.OK)
    public void addProductToShopCart(@PathVariable("idCart") Long idCart, @PathVariable("idProduct") Long idProduct) {

        shopCartService.addProductToShopCart(idCart, idProduct);

    }


    @PostMapping(value = "/addPaymentMethod/{idPayment}/{idCart}")
    public void addPaymentMethodToCart(@PathVariable("idPayment") Long idPayment, @PathVariable("idCart") Long idCart) {

        shopCartService.addPaymentMethodToCart(idPayment, idCart);

    }

    @PostMapping(value = "/addAddress/{idCart}")
    @ResponseStatus(HttpStatus.OK)
    public void addAddressToShopCart(@PathVariable("idCart") Long idCart, @RequestBody Address address) {

        shopCartService.addAddressToShopCart(idCart, address);

    }


}
