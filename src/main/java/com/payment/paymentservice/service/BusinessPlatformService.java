package com.payment.paymentservice.service;

import com.payment.paymentservice.model.BusinessPlatform;
import com.payment.paymentservice.repository.BusinessPlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BusinessPlatformService {

    private final BusinessPlatformRepository businessPlatformRepository;

    @Autowired
    public BusinessPlatformService(BusinessPlatformRepository businessPlatformRepository) {
        this.businessPlatformRepository = businessPlatformRepository;
    }

    public Mono<BusinessPlatform> findByIban(String iban) {
        return businessPlatformRepository.findBusinessPlatformByIban(iban);
    }
}
