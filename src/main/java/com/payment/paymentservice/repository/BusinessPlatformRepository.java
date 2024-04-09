package com.payment.paymentservice.repository;

import com.payment.paymentservice.model.BusinessPlatform;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BusinessPlatformRepository extends ReactiveMongoRepository<BusinessPlatform, String> {

    Mono<BusinessPlatform> findBusinessPlatformByIban(String iban);
}
