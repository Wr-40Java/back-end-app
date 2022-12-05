package Wr40.cardiary.test.service;

import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.repo.MaintenanceHistoryRepository;
import Wr40.cardiary.service.CarService;
import Wr40.cardiary.service.MaintenanceHistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MaintenanceHistoryServiceTest {

    @Mock
    private MaintenanceHistoryRepository maintenanceHRepo;
    @InjectMocks
    private MaintenanceHistoryService maintenanceHService;
    @Mock
    private CarService carService;

    @Test
    @DisplayName("Should Save Maintenance History When Saving")
    void shouldSaveMaintenanceHistoryWhenSaving() {
        // Given
        String vinNumber = "VinTest";
        Car car = new Car();
        car.setVINnumber(vinNumber);
        car.setMaintenanceHistories(new ArrayList<>());

        MaintenanceHistory mh = new MaintenanceHistory();
        Mockito.when(maintenanceHRepo.save(mh)).thenReturn(mh);
        Mockito.when(carService.getCar(vinNumber)).thenReturn(car);

        // When
        MaintenanceHistory mhSaved = maintenanceHService.saveMH("VinTest", mh);

        // Then
        Assertions.assertEquals(mhSaved, mh);
        verify(maintenanceHRepo).save(mh);
    }
}
