package Wr40.cardiary.service;

import Wr40.cardiary.model.entity.MaintenanceEvent;
import Wr40.cardiary.repo.MaintenanceEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MaintenanceEventService {

    private MaintenanceEventRepository maintenanceEventRepository;
    private MaintenanceHistoryService maintenanceHistoryService;
    @Transactional
    public MaintenanceEvent saveMaintenanceEvent(Long mHistoryId, MaintenanceEvent maintenanceEvent) {
//        MaintenanceHistory maintenanceHistory = maintenanceHistoryService.getMaintenanceHistory(mHistoryId);
        MaintenanceEvent savedMaintenanceEvent = maintenanceEventRepository.save(maintenanceEvent);
//        maintenanceHistory.setMaintenanaceEvent(savedMaintenanceEvent);
//        calculateMaintenanceCost(maintenanceHistory);
//        maintenanceHistoryService.updateMH(mHistoryId,maintenanceHistory);

        return savedMaintenanceEvent;
    }
}

