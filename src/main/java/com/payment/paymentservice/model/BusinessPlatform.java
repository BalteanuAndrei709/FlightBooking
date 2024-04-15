package com.payment.paymentservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents the operator from which a user will purchase a flight ticket. To distinguish these operators when
 * creating a purchase, the iban field is used.
 * <p>
 * This class also contains the clientId and clientSecret for each operator, so that paypal will now to which operator we
 * complete a purchase.
 */
@Document
public class BusinessPlatform {
    @Id
    private String id;
    private String iban;
    private String clientId;
    private String clientSecret;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public BusinessPlatform(String id, String iban, String clientId, String clientSecret) {
        this.id = id;
        this.iban = iban;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public BusinessPlatform() {
    }
}
