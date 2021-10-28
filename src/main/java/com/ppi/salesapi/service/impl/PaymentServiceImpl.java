package com.ppi.salesapi.service.impl;

import com.ppi.salesapi.model.Payment;
import com.ppi.salesapi.repository.PaymentRepository;
import com.ppi.salesapi.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Long savePayment(Payment payment) {

        Assert.notNull(payment,"Payment cannot be null");

        Payment persistedPayment = new Payment();

        persistedPayment.setNumber(payment.getNumber());
        persistedPayment.setCvv(payment.getCvv());
        persistedPayment.setName(payment.getName());
        persistedPayment.setDate(payment.getDate());

        paymentRepository.saveAndFlush(payment);

        return persistedPayment.getId();

    }

    public Payment findPaymentById(Long id) {

        Assert.notNull(id,"Id cannot be null");

        return paymentRepository.findPaymentById(id);


    }


}
