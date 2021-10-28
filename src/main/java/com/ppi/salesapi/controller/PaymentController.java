package com.ppi.salesapi.controller;

import com.ppi.salesapi.model.Payment;
import com.ppi.salesapi.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/save")
    public Long savePayment(@RequestBody Payment payment) {

        return paymentService.savePayment(payment);

    }

    @GetMapping(value = "/{id}")
    private Payment findPaymentById(@PathVariable(value = "id") Long id) {

        return paymentService.findPaymentById(id);

    }


}
