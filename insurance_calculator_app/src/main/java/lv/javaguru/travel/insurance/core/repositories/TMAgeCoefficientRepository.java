package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TMAgeCoefficient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TMAgeCoefficientRepository extends JpaRepository<TMAgeCoefficient, Long> {

    @Cacheable(cacheNames = {"TMAgeCoefficientCache"}, key = "#age", condition="#result != null")
    @Query("SELECT ac from TMAgeCoefficient ac " +
            "where ac.ageFrom <= :age " +
            "and ac.ageTo >= :age")
    Optional<TMAgeCoefficient> findByAge(
            @Param("age") int age
    );
}
