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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MaintenanceHistoryServiceTest {

    @Mock
    private MaintenanceHistoryRepository maintenanceHistoryRepository;
    @InjectMocks
    private MaintenanceHistoryService maintenanceHistoryService;
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
        Mockito.when(maintenanceHistoryRepository.save(mh)).thenReturn(mh);
        Mockito.when(carService.getCar(vinNumber)).thenReturn(car);
        Mockito.when(carService.updateCar(car)).thenReturn(car);

        // When
        MaintenanceHistory mhSaved = maintenanceHistoryService.saveMH("VinTest", mh);

        // Then
        Assertions.assertEquals(mh, mhSaved);
        verify(maintenanceHistoryRepository).save(mh);
    }

    @Test
    @DisplayName("Should Update Given Maintenance History When Update Maintenance History")
    void shouldUpdateGivenMaintenanceHistoryWhenUpdateMaintenanceHistory() {
        MaintenanceHistory mh1 = new MaintenanceHistory();
        MaintenanceHistory mh2 = new MaintenanceHistory();
        mh1.setId(1L);
        mh2.setId(mh1.getId());
        mh2.setDescription("Technical maintenance");

        Mockito.when(maintenanceHistoryRepository.findById(1L)).thenReturn(Optional.of(mh1));
        Mockito.when(maintenanceHistoryRepository.save(mh1)).thenReturn(mh2);

        MaintenanceHistory updateMH = maintenanceHistoryService.updateMH(1L, mh2);
        assertEquals("Technical maintenance", updateMH.getDescription());
    }

    @Test
    @DisplayName("Should Throw Exception When Update Of Maintenance History Is Not In Database")
    void shouldThrowExceptionWhenUpdateOfMaintenanceHistoryIsNotInDatabase() {
        // Given
        MaintenanceHistory mh1 = new MaintenanceHistory();
        Long id = 1L;
        Mockito.when(maintenanceHistoryRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoSuchMaintenanceHistoryException.class, () -> maintenanceHistoryService.updateMH(id, mh1));
    }

    @Test
    @DisplayName("Should Return Maintenance History When Maintenance History Exists In Database")
    void shouldReturnMaintenanceHistoryWhenMaintenanceHistoryExistsInDatabase() {
        // Given
        MaintenanceHistory mh1 = new MaintenanceHistory();
        Long id = 1L;
        Mockito.when(maintenanceHistoryRepository.existsById(id)).thenReturn(true);
        Mockito.when(maintenanceHistoryRepository.getReferenceById(id)).thenReturn(mh1);

        // When
        MaintenanceHistory savedMH = maintenanceHistoryService.getMaintenanceHistory(id);

        // Then
        assertEquals(mh1, savedMH);
        verify(maintenanceHistoryRepository).existsById(id);
        verify(maintenanceHistoryRepository).getReferenceById(id);
    }

    @Test
    @DisplayName("Should Throw Exception When Get By Id Maintenance History Is Not In Database")
    void shouldThrowExceptionWhenGetByIdUpdateOfMaintenanceHistoryIsNotInDatabase() {
        // Given
        Long id = 1L;
        Mockito.when(maintenanceHistoryRepository.existsById(id)).thenThrow(NoSuchMaintenanceHistoryException.class);

        // When & Then
        assertThrows(NoSuchMaintenanceHistoryException.class, () -> maintenanceHistoryService.getMaintenanceHistory(id));
    }

    @Test
    @DisplayName("Should Provide All Maintenance History When Getting All Maintenance History")
    void shouldProvideAllMaintenanceHistoryWhenGettingAllMaintenanceHistory() {
        // Given
        List<MaintenanceHistory> mhLists = new ArrayList<>();
        MaintenanceHistory mh = new MaintenanceHistory();
        mhLists.add(mh);
        Mockito.when(maintenanceHistoryRepository.findAll()).thenReturn(mhLists);

        // Given
        List<MaintenanceHistory> mhListsSaved = maintenanceHistoryService.getAllMaintenanceHistory();

        // When

        assertEquals(mhLists, mhListsSaved);
        verify(maintenanceHistoryRepository).findAll();
    }

    @Test
    @DisplayName("Should Delete Maintenance History When Given Id Is Correct")
    void shouldDeleteMaintenanceHistoryWhenGivenIdIsCorrect() {
        // Given
        Long id = 1L;
        Mockito.when(maintenanceHistoryRepository.existsById(id)).thenReturn(true);

        // When
        maintenanceHistoryService.deleteMaintenanceHistory(id);

        // Then
        verify(maintenanceHistoryRepository).deleteById(id);
    }

    @Test
    @DisplayName("Should Delete All Maintenance History When Given Id Is Correct")
    void shouldDeleteAllMaintenanceHistoryWhenGivenIdIsCorrect() {
        // Given & When
        maintenanceHistoryService.deleteAllMaintenanceHistory();

        // Then
        verify(maintenanceHistoryRepository).deleteAll();
    }

    @Test
    @DisplayName("Should Throw Exception When Deleting Maintenance History With Wrong Id")
    void shouldThrowExceptionWhenDeletingMaintenanceHistoryWithWrongId() {
        Long id = 1L;
        Mockito.when(maintenanceHistoryRepository.existsById(id)).thenThrow(NoSuchMaintenanceHistoryException.class);

        // When & Then
        assertThrows(NoSuchMaintenanceHistoryException.class, () -> maintenanceHistoryService.deleteMaintenanceHistory(id));
    }

}
