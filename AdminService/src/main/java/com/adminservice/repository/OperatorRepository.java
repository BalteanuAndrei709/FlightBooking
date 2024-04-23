package com.adminservice.repository;

import com.adminservice.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    @Query("SELECT DISTINCT o FROM Operator o JOIN o.allFlights f WHERE f.leaving = :leaving AND " +
            "f.destination = :destination")
    List<Operator> findOperatorsByLeavingAndDestination(@Param("leaving") String leaving,
                                                        @Param("destination") String destination);
    @Query("SELECT DISTINCT o FROM Operator o JOIN o.allFlights f WHERE f.leaving = :leaving")
    List<Operator> findOperatorsByLeaving(@Param("leaving") String leaving);
}
