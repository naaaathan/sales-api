package com.ppi.salesapi.service;

import com.ppi.salesapi.model.Address;
import com.ppi.salesapi.model.Sell;

public interface SellService {

    Sell findSellById(Long id);

    void addProductToShopCart(Long idCart, Long idProduct);

    void addPaymentMethodToCart(Long idPayment, Long idCart);

    void addAddressToShopCart(Long idCart,Address address);
}
