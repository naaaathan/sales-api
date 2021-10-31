package com.ppi.salesapi.controller;

import com.ppi.salesapi.model.Sell;
import com.ppi.salesapi.service.impl.SellServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sell")
public class SellController {

    @Autowired
    private SellServiceImpl shopCartService;

    @GetMapping(value = "/{id}")
    public Sell findById(@PathVariable("id") Long id) {

        return shopCartService.findSellById(id);

    }


    @PostMapping("/finalize")
    @ResponseStatus(HttpStatus.OK)
    public Long concludeSell(@RequestBody Sell sell){

        return shopCartService.concludeSell(sell);

    }


}
