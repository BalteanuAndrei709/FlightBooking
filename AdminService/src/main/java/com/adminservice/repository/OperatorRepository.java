package com.adminservice.repository;

import com.adminservice.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Persistence layer for the Operator.
 */
@Repository
public interface OperatorRepository extends JpaRepository<Operator, Integer> {

    Optional<Operator> findByName(String name);

    boolean existsByName(String name);

    boolean existsByIban(String iban);

    boolean existsByApiSearch(String apiSearch);
}
