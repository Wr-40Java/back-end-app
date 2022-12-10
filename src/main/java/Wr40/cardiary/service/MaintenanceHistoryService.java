package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchMaintenanceHistoryException;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.MaintenanceEvent;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.repo.MaintenanceEventRepository;
import Wr40.cardiary.repo.MaintenanceHistoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static Wr40.cardiary.util.Calculations.calculateMaintenanceCost;

@Service
@AllArgsConstructor
@Slf4j
public class MaintenanceHistoryService {
    private MaintenanceHistoryRepository maintenanceHistRepo;
    private CarService carService;
//    @Lazy
//    private MaintenanaceEventService maintenanaceEventService;
    private MaintenanceEventRepository maintenanceEventRepository;

    @Transactional
    public MaintenanceHistory saveMH(String carVin, MaintenanceHistory maintenanceHistory) {
        Car car = carService.getCar(carVin);

//        MaintenanceEvent savedMaintenanceEvent = maintenanceEventRepository.save(maintenanceHistory.getMaintenanceEvent());
//        maintenanceHistory.setMaintenanceEvent(savedMaintenanceEvent);
//        calculateMaintenanceCost(maintenanceHistory);

        MaintenanceHistory savedMH = maintenanceHistRepo.save(maintenanceHistory);

        List<MaintenanceHistory> maintenanceHistories = car.getMaintenanceHistories();
        maintenanceHistories.add(savedMH);
        car.setMaintenanceHistories(maintenanceHistories);
        Car updateCar = carService.updateCar(car);

        return updateCar.getMaintenanceHistories().get(0);
    }

    public MaintenanceHistory updateMH(Long maintenanceId, MaintenanceHistory mh) {
        MaintenanceHistory savedMH = maintenanceHistRepo.findById(maintenanceId).orElseThrow(NoSuchMaintenanceHistoryException::new);
        savedMH.setDescription(mh.getDescription());
        savedMH.setMaintenanceEvent(mh.getMaintenanceEvent());
        return maintenanceHistRepo.save(savedMH);
    }

    public MaintenanceHistory getMaintenanceHistory(Long maintenanceId) {
        if (!maintenanceHistRepo.existsById(maintenanceId)) {
            throw new NoSuchMaintenanceHistoryException();
        }
        return maintenanceHistRepo.getReferenceById(maintenanceId);
    }

    public List<MaintenanceHistory> getAllMaintenanceHistory() {
        return maintenanceHistRepo.findAll();
    }

    public void deleteMaintenanceHistory(Long id) {
        if (!maintenanceHistRepo.existsById(id)) {
            throw new NoSuchMaintenanceHistoryException();
        }
        maintenanceHistRepo.deleteById(id);
    }

    public void deleteAllMaintenanceHistory() {
        maintenanceHistRepo.deleteAll();
    }
}