package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchMaintenanceHistoryException;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.repo.MaintenanceHistoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    @DisplayName("Should Update Given Maintenance History When Update Maintenance History")
    void shouldUpdateGivenMaintenanceHistoryWhenUpdateMaintenanceHistory() {
        MaintenanceHistory mh1 = new MaintenanceHistory();
        MaintenanceHistory mh2 = new MaintenanceHistory();
        mh1.setId(1L);
        mh2.setId(mh1.getId());
        mh2.setDescription("Technical maintenance");

        Mockito.when(maintenanceHRepo.findById(1L)).thenReturn(Optional.of(mh1));
        Mockito.when(maintenanceHRepo.save(mh1)).thenReturn(mh2);

        MaintenanceHistory updateMH = maintenanceHService.updateMH(1L, mh2);
        assertEquals("Technical maintenance", updateMH.getDescription());
    }

    @Test
    @DisplayName("Should Throw Exception When Update Maintenance History Is Not In Database")
    void shouldThrowExceptionWhenUpdateMaintenanceHistoryIsNotInDatabase() {
        // Given
        MaintenanceHistory mh1 = new MaintenanceHistory();
        Long id = 1L;

        Mockito.when(maintenanceHRepo.findById(mh1.getId())).thenThrow(NoSuchMaintenanceHistoryException.class);

        // When & Then
        assertThrows(NoSuchMaintenanceHistoryException.class, () -> maintenanceHService.updateMH(id, mh1));
    }
}
