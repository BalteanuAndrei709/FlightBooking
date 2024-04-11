package com.payment.paymentservice;

import com.payment.paymentservice.model.OrderStatus;
import com.payment.paymentservice.service.OrderService;

public class Main {
    private static final long EXPIRATION_TIME = 10000 * 2;

    private OrderService orderService;

    public static void main(String[] args) throws InterruptedException {

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setStatus("INITIATED");
        orderStatus.setOrderId("TIME_TEST");

        System.out.println("Initial Status of ORDER:");
        System.out.println("/////////////////////////////////////////////////");
        System.out.println(orderStatus.getOrderId() + "\t" + orderStatus.getStatus());

        long creationTime = System.currentTimeMillis();

        while (true) {

            long result = System.currentTimeMillis() - creationTime;
            if (result > EXPIRATION_TIME) {
                orderStatus.setStatus("CANCELED");
                System.out.println("Initial Status of ORDER:");
                System.out.println("/////////////////////////////////////////////////");
                System.out.println(orderStatus.getOrderId() + "\t" + orderStatus.getStatus());
                break;
            } else {
                System.out.println("Waiting for payment to get completed");
                Thread.sleep(1000);
            }
        }

    }
}
