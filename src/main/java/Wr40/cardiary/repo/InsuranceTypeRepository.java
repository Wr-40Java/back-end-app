package Wr40.cardiary.repo;

import Wr40.cardiary.model.entity.InsuranceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface InsuranceTypeRepository extends JpaRepository<InsuranceType, Long> {
    Optional<InsuranceType> findByType(String type);
    @Modifying
    @Query(value = "update insurance_type set (description, costs_per_year, covered_compensation) = " +
            "(:description, :costs_per_year, :covered_compensation) WHERE type = :type", nativeQuery = true)
    Integer UpdateInsuranceTypeTuple(@Param("description") String description, @Param("costs_per_year") BigDecimal costs_per_year,
                                           @Param("covered_compensation") BigDecimal covered_compensation, @Param("type") String type);
}
