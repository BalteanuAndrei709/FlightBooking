package com.payment.paymentservice;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        final String GG_URL = "https://gg.deals/";
        Runtime rt = Runtime.getRuntime();
        Process x = rt.exec("rundll32 url.dll,FileProtocolHandler " + GG_URL);
        if (x.exitValue()== 0) {
            System.out.println("Process has finished");
        }

    }
}

