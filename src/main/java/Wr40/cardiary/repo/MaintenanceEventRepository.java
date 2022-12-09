package Wr40.cardiary.repo;

import Wr40.cardiary.model.entity.MaintenanceEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceEventRepository extends JpaRepository<MaintenanceEvent, Long> {
    @Query(value = """
            select CONCAT (round(SUM(maintenance_event.cost),2), ';', maintenance_event.company_responsible_for_name, ';', maintenance_event.description) AS "info" from maintenance_history left join maintenance_event on maintenance_history.maintenance_event_id = maintenance_event.id
            group by maintenance_event.description, maintenance_event.company_responsible_for_name
            order by round(SUM(maintenance_event.cost),2)""", nativeQuery = true)

    List<String> getMaintenanceStatsWithSumOfExpenses();

    @Query(value = """
            select CONCAT (round(AVG(maintenance_event.cost),2), ';', maintenance_event.description, ';', maintenance_event.company_responsible_for_name) AS "info" from maintenance_history left join maintenance_event on maintenance_history.maintenance_event_id = maintenance_event.id
            group by maintenance_event.description, maintenance_event.company_responsible_for_name
            order by round(AVG(maintenance_event.cost),2)""", nativeQuery = true)

    List<String> getMaintenanceStatsWithAvgOfExpenses();


}