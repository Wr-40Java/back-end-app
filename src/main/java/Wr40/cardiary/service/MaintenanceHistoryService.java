package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchMaintenanceHistoryException;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.MaintenanaceEvent;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.repo.MaintenanceEventRepository;
import Wr40.cardiary.repo.MaintenanceHistoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
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
        log.info(car.getVINnumber());
//        MaintenanaceEvent savedMaintenanceEvent = maintenanaceEventService.saveMaintenanceEvent(1L,maintenanceHistory.getMaintenanaceEvent());
//        MaintenanaceEvent savedMaintenanceEvent = maintenanceEventRepository.save(maintenanceHistory.getMaintenanaceEvent());
//        maintenanceEventRepository.flush();
//        maintenanceHistory.setMaintenanaceEvent(savedMaintenanceEvent);
        calculateMaintenanceCost(maintenanceHistory);

        MaintenanceHistory savedMH = maintenanceHistRepo.save(maintenanceHistory);

        List<MaintenanceHistory> maintenanceHistories = car.getMaintenanceHistories();
        maintenanceHistories.add(savedMH);
        car.setMaintenanceHistories(maintenanceHistories);
//        carService.updateCar(car);

        return savedMH;
    }

    public MaintenanceHistory updateMH(Long maintenanceId, MaintenanceHistory mh) {
        MaintenanceHistory savedMH = maintenanceHistRepo.findById(maintenanceId).orElseThrow(NoSuchMaintenanceHistoryException::new);
        savedMH.setDescription(mh.getDescription());
        savedMH.setMaintenanaceEvent(mh.getMaintenanaceEvent());
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