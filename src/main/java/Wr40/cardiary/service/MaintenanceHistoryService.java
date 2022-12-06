package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchMaintenanceHistoryException;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.repo.MaintenanceHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class MaintenanceHistoryService {
    private MaintenanceHistoryRepository maintenanceHistRepo;
    private CarService carService;

    public MaintenanceHistory saveMH(String carVin, MaintenanceHistory maintenanceHistory) {
        Car car = carService.getCar(carVin);

        setMaintenanceCost(maintenanceHistory);
        MaintenanceHistory savedMH = maintenanceHistRepo.save(maintenanceHistory);

        List<MaintenanceHistory> maintenanceHistories = car.getMaintenanceHistories();
        maintenanceHistories.add(savedMH);
        car.setMaintenanceHistories(maintenanceHistories);
        carService.updateCar(car);

        return savedMH;
    }

    public MaintenanceHistory updateMH(Long maintenanceId, MaintenanceHistory mh) {
        MaintenanceHistory savedMH = maintenanceHistRepo.findById(maintenanceId).orElseThrow(NoSuchMaintenanceHistoryException::new);
        savedMH.setDescription(mh.getDescription());
        return maintenanceHistRepo.save(savedMH);
    }

    public MaintenanceHistory getMaintenanceHistory(Long maintenanceId) {
        if (!maintenanceHistRepo.existsById(maintenanceId)) {
            throw new NoSuchMaintenanceHistoryException();
        }
        return maintenanceHistRepo.getReferenceById(maintenanceId);
    }

    private void setMaintenanceCost(MaintenanceHistory maintenanceHistory) {
//        MaintenanaceEvent event = maintenanceHistory.getMaintenanaceEvent();
//        TechnicalService service = maintenanceHistory.getTechnicalService();
        BigDecimal overallCost = new BigDecimal(0);
//        if (event != null) {
//            overallCost = overallCost.add(event.getCost());
//        }
//        if (service != null) {
//            overallCost = overallCost.add(service.getCost());
//        }
        maintenanceHistory.setOverallCost(overallCost);
    }

    public List<MaintenanceHistory> getAllMaintenanceHistory() {
        return maintenanceHistRepo.findAll();
    }
}