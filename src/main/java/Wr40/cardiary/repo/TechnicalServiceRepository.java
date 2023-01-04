package Wr40.cardiary.repo;

import Wr40.cardiary.model.entity.TechnicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicalServiceRepository extends JpaRepository<TechnicalService, Long> {
    @Query(
            value = """
                        select CONCAT (round(SUM(technical_service.cost),2), ';', technical_service.company_responsible_for_name, ';', technical_service.reason, ';', technical_service.description)
                        AS "info" from maintenance_history left join technical_service on maintenance_history.technical_service_id = technical_service.id 
                        group by technical_service.description, technical_service.company_responsible_for_name
                        order by round(SUM(technical_service.cost),2) """, nativeQuery = true)

    List<String> getTechnicalServiceStatsWithSumOfExpenses();

    @Query(
            value = """
                        select CONCAT (round(AVG(technical_service.cost),2), ';', technical_service.company_responsible_for_name, ';', technical_service.reason, ';', technical_service.description)
                        AS "info" from maintenance_history left join technical_service on maintenance_history.technical_service_id = technical_service.id 
                        group by technical_service.description, technical_service.company_responsible_for_name
                        order by round(SUM(technical_service.cost),2) """, nativeQuery = true)

    List<String> getTechnicalServiceStatsWithAvgOfExpenses();


}
