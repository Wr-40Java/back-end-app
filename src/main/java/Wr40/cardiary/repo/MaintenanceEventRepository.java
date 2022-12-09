package Wr40.cardiary.repo;

import Wr40.cardiary.model.entity.MaintenanceEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceEventRepository extends JpaRepository<MaintenanceEvent, Long> {
    @Query(value = """
            select CONCAT (round(SUM(maintenanace_event.cost),2), ';' ,maintenanace_event.description, ';', maintenanace_event.company_responsible_for_name) AS "info" from maintenance_history left join maintenanace_event on maintenance_history.maintenanace_event_id = maintenanace_event.id
                        group by maintenanace_event.description, maintenanace_event.company_responsible_for_name
                        order by round(SUM(maintenanace_event.cost),2)""", nativeQuery = true)

    List<String> getMaintenanceStatsWithSumOfExpenses();

    @Query(value = """
            select CONCAT (round(AVG(maintenanace_event.cost),2), ';' ,maintenanace_event.description, ';', maintenanace_event.company_responsible_for_name) AS "info" from maintenance_history left join maintenanace_event on maintenance_history.maintenanace_event_id = maintenanace_event.id
                        group by maintenanace_event.description, maintenanace_event.company_responsible_for_name
                        order by round(AVG(maintenanace_event.cost),2)""", nativeQuery = true)

    List<String> getMaintenanceStatsWithAvgOfExpenses();


}