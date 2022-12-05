package Wr40.cardiary.service;

import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.MaintenanaceEvent;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.model.entity.TechnicalService;
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
}