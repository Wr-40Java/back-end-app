package Wr40.cardiary.service;

import Wr40.cardiary.model.entity.MaintenanaceEvent;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.repo.MaintenanceEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static Wr40.cardiary.util.Calculations.calculateMaintenanceCost;

@Service
@AllArgsConstructor
public class MaintenanaceEventService {

    private MaintenanceEventRepository maintenanceEventRepository;
    private MaintenanceHistoryService maintenanceHistoryService;
    @Transactional
    public MaintenanaceEvent saveMaintenanceEvent(Long mHistoryId, MaintenanaceEvent maintenanceEvent) {
//        MaintenanceHistory maintenanceHistory = maintenanceHistoryService.getMaintenanceHistory(mHistoryId);
        MaintenanaceEvent savedMaintenanceEvent = maintenanceEventRepository.save(maintenanceEvent);
//        maintenanceHistory.setMaintenanaceEvent(savedMaintenanceEvent);
//        calculateMaintenanceCost(maintenanceHistory);
//        maintenanceHistoryService.updateMH(mHistoryId,maintenanceHistory);

        return savedMaintenanceEvent;
    }
}

