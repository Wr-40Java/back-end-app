package Wr40.cardiary.repo;

import Wr40.cardiary.model.entity.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<InsuranceCompany, Long> {
    Optional<InsuranceCompany> findByName(String name);

    @Query("SELECT ie from InsuranceCompany ie")
    List<InsuranceCompany> getAllInsuranceCompanies();
}
