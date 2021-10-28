package com.ppi.salesapi.service;

import com.ppi.salesapi.model.Payment;

public interface PaymentService {

    Long savePayment(Payment payment);

    Payment findPaymentById(Long id);
}
