package com.payment.paymentservice.controller;

import com.payment.paymentservice.model.CompletedOrder;
import com.payment.paymentservice.model.GetOrder;
import com.payment.paymentservice.model.PaymentOrder;
import com.payment.paymentservice.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/paypal")
public class PaymentController {

    private final PayPalService payPalService;

    @Autowired
    public PaymentController(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @PostMapping("/init")
    public Mono<PaymentOrder> createPayment(@RequestParam(name = "sum") Double sum,
                                            @RequestParam(name = "iban") String iban) {
        return payPalService.createPayment(sum, iban);
    }

    @PostMapping(value = "/capture")
    public Mono<CompletedOrder> completePayment(@RequestParam("token") String token,
                                                @RequestParam(name = "iban") String iban) {
        return payPalService.completePayment(token, iban);
    }

    @GetMapping(value = "/get")
    public Mono<GetOrder> getPayment(@RequestParam("token") String token,
                                     @RequestParam(name = "iban") String iban) {
        return payPalService.getOrder(token, iban);
    }

    @PostMapping("/time")
    public Mono<CompletedOrder> captureOrder(@RequestParam("token") String token,
                                             @RequestParam(name = "iban") String iban) {
        return payPalService.captureOrder(token, iban);
    }
}
