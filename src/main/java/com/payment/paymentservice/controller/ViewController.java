package com.payment.paymentservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {
    @GetMapping("payment/success")
    public String successPayment() {
        return "success";
    }
    @GetMapping("/payment/cancel")
    public String cancelPayment() {
        return "error";
    }
}
