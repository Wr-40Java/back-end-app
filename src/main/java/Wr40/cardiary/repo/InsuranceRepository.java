package Wr40.cardiary.repo;

import Wr40.cardiary.model.entity.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<InsuranceCompany, Long> {
    Optional<InsuranceCompany> findByName(String name);

    @Query("SELECT ie from InsuranceCompany ie")
    List<InsuranceCompany> getAllInsuranceCompanies();

    @Query(value = "select insurance_company.id as ic_id, insurance_company.name, insurance_company.description as ic_description, " +
            "insurance_company.insurance_type_id, insurance_company.phone_number, insurance_type.* from car " +
            "inner join insurance_company on car.id = insurance_company.id inner join insurance_type " +
            "on insurance_company.insurance_type_id = insurance_type.id where car.vin_number = :vin", nativeQuery = true)
    List<InsuranceCompany> getAllIncuranceCompaniesWithType(@Param("vin") String VINNumber);
}
