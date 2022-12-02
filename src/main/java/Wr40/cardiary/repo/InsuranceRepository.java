package Wr40.cardiary.repo;

import Wr40.cardiary.model.entity.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<InsuranceCompany, Long> {
    Optional<InsuranceCompany> findByName(String name);
}
