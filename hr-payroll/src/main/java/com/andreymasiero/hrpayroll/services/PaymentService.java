package com.andreymasiero.hrpayroll.services;

import com.andreymasiero.hrpayroll.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public Payment getPayment(Long workerId, Integer days) {
        return new Payment("Gloria Maria", 200.0, days);
    }
}
