package com.payment.paymentservice.controller;

import com.payment.paymentservice.model.CompletedOrder;
import com.payment.paymentservice.model.PaymentOrder;
import com.payment.paymentservice.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/paypal")
public class PaymentController {

    private final PayPalService payPalService;

    @Autowired
    public PaymentController(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @PostMapping("/init")
    public PaymentOrder createPayment(@RequestParam(name = "sum") BigDecimal sum) {
        return payPalService.createPayment(sum);
    }

    @PostMapping(value = "/capture")
    public CompletedOrder completePayment(@RequestParam("token") String token) {
        return payPalService.completePayment(token);
    }
}
