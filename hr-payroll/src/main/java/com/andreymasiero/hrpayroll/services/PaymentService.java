package com.andreymasiero.hrpayroll.services;

import com.andreymasiero.hrpayroll.entities.Payment;
import com.andreymasiero.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String workerHost;
    private final WorkerFeignClient workerFeignClient;

    @Autowired
    public PaymentService(final WorkerFeignClient workerFeignClient) {
        this.workerFeignClient = workerFeignClient;
    }

    public Payment getPayment(Long workerId, Integer days) {
        var worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
