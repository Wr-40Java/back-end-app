package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchMaintenanceHistoryException;
import Wr40.cardiary.model.dto.maintenance.MaintenanceEventDTO;
import Wr40.cardiary.model.dto.technicalService.TechnicalServiceDTO;
import Wr40.cardiary.model.entity.Car;
import Wr40.cardiary.model.entity.MaintenanceEvent;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.model.entity.TechnicalService;
import Wr40.cardiary.repo.MaintenanceHistoryRepository;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MaintenanceHistoryServiceTest {

    @Mock
    private MaintenanceHistoryRepository maintenanceHistoryRepository;
    @InjectMocks
    private MaintenanceHistoryService maintenanceHistoryService;
    @Mock
    private CarService carService;

    @Mock
    private ModelMapper modelMapper;

    @Autowired
    private Validator validator;

    @Test
    @DisplayName("Should Save Maintenance History When Saving")
    void shouldSaveMaintenanceHistoryWhenSaving() {
        // Given
        String vinNumber = "14526450";
        Car car = new Car();
        car.setVINnumber(vinNumber);
        car.setMaintenanceHistories(new ArrayList<>());

        MaintenanceHistory mh = new MaintenanceHistory();
        when(maintenanceHistoryRepository.save(mh)).thenReturn(mh);
        when(carService.getCar(vinNumber)).thenReturn(car);
        when(carService.updateCar(car)).thenReturn(car);

        // When
        MaintenanceHistory mhSaved = maintenanceHistoryService.saveMH(vinNumber, mh);

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

        when(maintenanceHistoryRepository.findById(1L)).thenReturn(Optional.of(mh1));
        when(maintenanceHistoryRepository.save(mh1)).thenReturn(mh2);

        MaintenanceHistory updateMH = maintenanceHistoryService.updateMH(1L, mh2);
        assertEquals("Technical maintenance", updateMH.getDescription());
    }

    @Test
    @DisplayName("Should Throw Exception When Update Of Maintenance History Is Not In Database")
    void shouldThrowExceptionWhenUpdateOfMaintenanceHistoryIsNotInDatabase() {
        // Given
        MaintenanceHistory mh1 = new MaintenanceHistory();
        Long id = 1L;
        when(maintenanceHistoryRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoSuchMaintenanceHistoryException.class, () -> maintenanceHistoryService.updateMH(id, mh1));
    }

    @Test
    @DisplayName("Should Return Maintenance History When Maintenance History Exists In Database")
    void shouldReturnMaintenanceHistoryWhenMaintenanceHistoryExistsInDatabase() {
        // Given
        MaintenanceHistory mh1 = new MaintenanceHistory();
        Long id = 1L;
        when(maintenanceHistoryRepository.existsById(id)).thenReturn(true);
        when(maintenanceHistoryRepository.getReferenceById(id)).thenReturn(mh1);

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
        when(maintenanceHistoryRepository.existsById(id)).thenThrow(NoSuchMaintenanceHistoryException.class);

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
        when(maintenanceHistoryRepository.findAll()).thenReturn(mhLists);

        // When
        List<MaintenanceHistory> mhListsSaved = maintenanceHistoryService.getAllMaintenanceHistory();

        // Then

        assertEquals(mhLists, mhListsSaved);
        verify(maintenanceHistoryRepository).findAll();
    }

    @Test
    @DisplayName("Should Delete Maintenance History When Given Id Is Correct")
    void shouldDeleteMaintenanceHistoryWhenGivenIdIsCorrect() {
        // Given
        Long id = 1L;
        when(maintenanceHistoryRepository.existsById(id)).thenReturn(true);

        // When
        maintenanceHistoryService.deleteMaintenanceHistory(id);

        // Then
        verify(maintenanceHistoryRepository).deleteById(id);
    }

    @Test
    @DisplayName("Should Delete All Maintenance History When DeleteAll Was Requested")
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
        when(maintenanceHistoryRepository.existsById(id)).thenThrow(NoSuchMaintenanceHistoryException.class);

        // When & Then
        assertThrows(NoSuchMaintenanceHistoryException.class, () -> maintenanceHistoryService.deleteMaintenanceHistory(id));
    }

    @Test
    @DisplayName("Should Return All Maintenance Histories When Given Car Is Present")
    void shouldReturnAllMaintenanceHistoriesWhenGivenCarIsPresent() {
        // Given
        String vinNumber = "124352123";
        Car car = new Car();
        MaintenanceHistory mh1 = new MaintenanceHistory();
        mh1.setDescription("First maintenance");
        MaintenanceHistory mh2 = new MaintenanceHistory();
        mh2.setDescription("Second maintenance");
        car.setMaintenanceHistories(List.of(mh1, mh2));
        when(carService.getCar(any())).thenReturn(car);
        // When
        List<MaintenanceHistory> mhListsSaved = maintenanceHistoryService.getAllMaintenanceHistoryForGivenCar(vinNumber);

        // Then
        assertEquals(car.getMaintenanceHistories(), mhListsSaved);
        verify(carService).getCar(vinNumber);
    }

    @Test
    @DisplayName("Should Persist Maintenance Event and Technical Service When Inputs Are Correct")
    void shouldPersistMaintenanceEventAndTechnicalServiceWhenInputsAreCorrect() {
        // Given
        MaintenanceHistory mh = new MaintenanceHistory();
        MaintenanceEventDTO maintenanceEventDTO = new MaintenanceEventDTO();
        maintenanceEventDTO.setDescription("Simple service")
                .setCost(120L)
                .setCompanyResponsibleForName("AGawoda")
                .setCompanyResponsibleForPhoneNumber(785424870L)
                .setNextVisitSchedule(LocalDateTime.now().plusYears(1L));
        TechnicalServiceDTO technicalServiceDTO = new TechnicalServiceDTO();
        technicalServiceDTO.setDescription("Technical issue")
                .setCost(120L)
                .setCompanyResponsibleForName("AGawoda")
                .setCompanyResponsibleForPhoneNumber(785424870L)
                .setReason("Engine not starting");
        MaintenanceEvent maintenanceEvent = new MaintenanceEvent();
        maintenanceEvent.setDescription("Simple service")
                .setCost(new BigDecimal(120L))
                .setCompanyResponsibleForName("AGawoda")
                .setCompanyResponsibleForPhoneNumber(785424870L)
                .setNextVisitSchedule(LocalDateTime.now().plusYears(1L));
        TechnicalService technicalService = new TechnicalService();
        technicalService.setDescription("Technical issue")
                .setCost(new BigDecimal(120L))
                .setCompanyResponsibleForName("AGawoda")
                .setCompanyResponsibleForPhoneNumber(785424870L)
                .setReason("Engine not starting");
        when(modelMapper.map(maintenanceEventDTO, MaintenanceEvent.class)).thenReturn(maintenanceEvent);
        when(modelMapper.map(technicalServiceDTO, TechnicalService.class)).thenReturn(technicalService);

        // When
        maintenanceHistoryService.persistMaintenanceEventAndTechnicalService(mh, maintenanceEventDTO, technicalServiceDTO);
        // Then
        assertEquals(mh.getTechnicalService(), technicalService);
        assertEquals(mh.getMaintenanceEvent(), maintenanceEvent);
    }
}
