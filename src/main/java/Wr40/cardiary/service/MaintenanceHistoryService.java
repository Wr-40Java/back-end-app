package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchMaintenanceHistoryException;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.repo.MaintenanceHistoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MaintenanceHistoryService {
    private MaintenanceHistoryRepository maintenanceHistRepo;
    private CarService carService;

    @Transactional
    public MaintenanceHistory saveMH(String carVin, MaintenanceHistory maintenanceHistory) {
        Car car = carService.getCar(carVin);

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
        savedMH.setMaintenanceEvent(mh.getMaintenanceEvent());
        savedMH.setTechnicalService(mh.getTechnicalService());
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