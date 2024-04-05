package com.payment.paymentservice.controller;

import com.payment.paymentservice.model.CompletedOrder;
import com.payment.paymentservice.model.GetOrder;
import com.payment.paymentservice.model.PaymentOrder;
import com.payment.paymentservice.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/paypal")
public class PaymentController {

    private final PayPalService payPalService;

    @Autowired
    public PaymentController(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @PostMapping("/init")
    public PaymentOrder createPayment(@RequestParam(name = "sum") Double sum) {
        return payPalService.createPayment(sum);
    }

    @PostMapping(value = "/capture")
    public CompletedOrder completePayment(@RequestParam("token") String token) {
        return payPalService.completePayment(token);
    }

    @GetMapping(value = "/get")
    public GetOrder getPayment(@RequestParam("token") String token) {
        return payPalService.getOrder(token);
    }

    @DeleteMapping("/delete")
    public void deletePayment(@RequestParam("token") String token) {
        payPalService.delete(token);
    }
}
