package com.andreymasiero.hrpayroll.services;

import com.andreymasiero.hrpayroll.entities.Payment;
import com.andreymasiero.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String workerHost;
    private final RestTemplate restApi;

    @Autowired
    public PaymentService(final RestTemplate restApi) {
        this.restApi = restApi;
    }

    public Payment getPayment(Long workerId, Integer days) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", workerId.toString());
        var worker = restApi.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
