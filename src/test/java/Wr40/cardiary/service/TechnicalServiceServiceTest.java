package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchMaintenanceEventFoundException;
import Wr40.cardiary.exception.NoSuchTechnicalServiceFoundException;
import Wr40.cardiary.exception.UnableToDeleteMaintenanceEventException;
import Wr40.cardiary.exception.UnableToDeleteTechnicalServiceException;
import Wr40.cardiary.model.dto.technicalService.TechnicalServiceResponseDTO;
import Wr40.cardiary.model.entity.MaintenanceEvent;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.model.entity.TechnicalService;
import Wr40.cardiary.repo.TechnicalServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TechnicalServiceServiceTest {

    @Mock
    MaintenanceHistoryService maintenanceHistoryService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    private TechnicalServiceRepository technicalServiceRepository;
    @InjectMocks
    private TechnicalServiceService technicalServiceService;

    @Test
    @DisplayName("Should Save Technical Service When Maintenance History Have Null Object")
    void shouldSaveTechnicalServiceWhenMaintenanceHistoryHaveNullObject() {
        // Given
        Long maintenanceHistoryId = 1L;
        TechnicalService technicalService = new TechnicalService();
        MaintenanceHistory maintenanceHistory = new MaintenanceHistory();
        maintenanceHistory.setId(maintenanceHistoryId);

        TechnicalServiceResponseDTO technicalServiceResponseDTO = new TechnicalServiceResponseDTO();

        Mockito.when(modelMapper.map(technicalService, TechnicalServiceResponseDTO.class)).thenReturn(technicalServiceResponseDTO);
        Mockito.when(maintenanceHistoryService.getMaintenanceHistory(any(Long.class))).thenReturn(maintenanceHistory);
        Mockito.when(technicalServiceRepository.save(any(TechnicalService.class))).thenReturn(technicalService);
        Mockito.when(maintenanceHistoryService.updateMH(any(Long.class), any(MaintenanceHistory.class))).thenReturn(maintenanceHistory);

        // When
        TechnicalServiceResponseDTO technicalServiceResponseDTO1 = technicalServiceService.saveTechnicalService(maintenanceHistoryId, technicalService);

        // Then
        Assertions.assertEquals(technicalServiceResponseDTO, technicalServiceResponseDTO1);
        verify(technicalServiceRepository).save(technicalService);
    }

    @Test
    @DisplayName("Should Update Given Technical Service When Given Id Is Correct")
    void shouldUpdateGivenTechnicalServiceWhenGivenIdIsCorrect() {
        //Given
        TechnicalService tService1 = new TechnicalService();
        TechnicalService tService2 = new TechnicalService();
        Long mEventId = 1L;
        tService2.setDescription("Technical maintenance");
        TechnicalServiceResponseDTO tSRDTO = new TechnicalServiceResponseDTO();
        tSRDTO.setDescription(tService2.getDescription());

        Mockito.when(technicalServiceRepository.existsById(any(Long.class))).thenReturn(true);
        Mockito.when(technicalServiceRepository.save(any(TechnicalService.class))).thenReturn(tService2);
        Mockito.when(modelMapper.map(tService2, TechnicalServiceResponseDTO.class)).thenReturn(tSRDTO);

        // When
        TechnicalServiceResponseDTO updateTS = technicalServiceService.updateTechnicalService(mEventId, tService1);

        // Then
        assertEquals("Technical maintenance", updateTS.getDescription());
    }

    @Test
    @DisplayName("Should Throw Exception When Technical Service Is Not Present In Database")
    void shouldThrowExceptionWhenTechnicalServiceIsNotPresentInDatabase() {
        // Given
        Long tServiceId = 1L;
        TechnicalService technicalService = new TechnicalService();
        Mockito.when(technicalServiceRepository.existsById(any(Long.class))).thenReturn(false);

        // When & Then
        assertThrows(NoSuchTechnicalServiceFoundException.class, () -> technicalServiceService.updateTechnicalService(tServiceId, technicalService));
    }

    @Test
    @DisplayName("Should Throw Exception When Getting Technical Service Which Is Not Present In Database")
    void shouldThrowExceptionWhenGettingTechnicalServiceWhichIsNotPresentInDatabase() {
        // Given
        Long mEventId = 1L;
        Mockito.when(technicalServiceRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoSuchTechnicalServiceFoundException.class, () -> technicalServiceService.getTechnicalService(mEventId));
    }

    @Test
    @DisplayName("Should Get Technical Service When It Is Present In Database")
    void shouldGetTechnicalServiceWhenItIsPresentInDatabase() {
        // Given
        TechnicalService tService = new TechnicalService();
        TechnicalServiceResponseDTO sTRDTO = new TechnicalServiceResponseDTO();
        Long mEventId = 1L;

        Mockito.when(technicalServiceRepository.findById(any(Long.class))).thenReturn(Optional.of(tService));
        Mockito.when(modelMapper.map(tService, TechnicalServiceResponseDTO.class)).thenReturn(sTRDTO);

        // When
        TechnicalServiceResponseDTO technicalServiceSaved = technicalServiceService.getTechnicalService(mEventId);

        // Then
        Assertions.assertEquals(sTRDTO, technicalServiceSaved);
        verify(technicalServiceRepository).findById(mEventId);
    }

    @Test
    @DisplayName("Should Provide All Technical Service When Getting All Technical Service")
    void shouldProvideAllTechnicalServiceWhenGettingAllTechnicalService() {
        // Given
        List<TechnicalService> tServiceList = new ArrayList<>();
        TechnicalService mEvent = new TechnicalService();
        TechnicalServiceResponseDTO sTRDTO = new TechnicalServiceResponseDTO();
        List<TechnicalServiceResponseDTO> listTSRDTO = new ArrayList<>();
        listTSRDTO.add(sTRDTO);
        tServiceList.add(mEvent);
        Mockito.when(technicalServiceRepository.findAll()).thenReturn(tServiceList);
        Mockito.when(modelMapper.map(mEvent, TechnicalServiceResponseDTO.class)).thenReturn(sTRDTO);
        // Given
        List<TechnicalServiceResponseDTO> mEventListsSaved = technicalServiceService.getAllMaintenanceEvent();

        // When
        assertEquals(listTSRDTO, mEventListsSaved);
        verify(technicalServiceRepository).findAll();
    }

    @Test
    @DisplayName("Should Delete Technical Service When Given Id Is Present In Database")
    void shouldDeleteMaintenanceEventWhenGivenIdIsPresentInDatabase() {
        // Given
        TechnicalService technicalService = new TechnicalService();
        Long technicalServiceId = 1L;

        Mockito.when(technicalServiceRepository.findById(any(Long.class))).thenReturn(Optional.of(technicalService));
        Mockito.when(technicalServiceRepository.existsById(any(Long.class))).thenReturn(false);

        // When
        technicalServiceService.deleteTechnicalService(technicalServiceId);

        // Then
        verify(technicalServiceRepository).delete(technicalService);
        verify(technicalServiceRepository).existsById(technicalServiceId);
    }

    @Test
    @DisplayName("Should Delete All Technical Service When Requested")
    void shouldDeleteAllTechnicalServiceWhenRequested() {
        // Given
        technicalServiceService.deleteAllTechnicalService();

        // When
        verify(technicalServiceRepository).deleteAll();
    }

    @Test
    @DisplayName("Should Throw Exception When Unable To Delete From Database")
    void shouldThrowExceptionWhenUnableToDeleteFromDatabase() {
        // Given
        TechnicalService technicalService = new TechnicalService();
        Long technicalServiceId = 1L;
        Mockito.when(technicalServiceRepository.findById(any(Long.class))).thenReturn(Optional.of(technicalService));
        Mockito.when(technicalServiceRepository.existsById(any(Long.class))).thenReturn(true);

        // When
        assertThrows(UnableToDeleteTechnicalServiceException.class, () -> technicalServiceService.deleteTechnicalService(technicalServiceId));
    }

    @Test
    @DisplayName("Should Throw Exception When Unable To Delete All Technical Service From Database")
    void shouldThrowExceptionWhenUnableToDeleteAllTechnicalServiceFromDatabase() {
        // Given
        List<TechnicalService> technicalServiceList = new ArrayList<>();
        TechnicalService technicalService = new TechnicalService();
        technicalServiceList.add(technicalService);
        Mockito.when(technicalServiceRepository.findAll()).thenReturn(technicalServiceList);

        // When
        assertThrows(UnableToDeleteTechnicalServiceException.class, () -> technicalServiceService.deleteAllTechnicalService());
    }

    @Test
    @DisplayName("Should Throw Exception When Deleting Technical Service With Wrong Id")
    void shouldThrowExceptionWhenDeletingTechnicalServiceWithWrongId() {
        // Given
        Long technicalServiceId = 1L;

        // When
        assertThrows(NoSuchTechnicalServiceFoundException.class, () -> technicalServiceService.deleteTechnicalService(technicalServiceId));
    }
}
