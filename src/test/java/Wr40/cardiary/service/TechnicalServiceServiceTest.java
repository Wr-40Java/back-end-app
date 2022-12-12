package Wr40.cardiary.service;

import Wr40.cardiary.exception.NoSuchTechnicalServiceFoundException;
import Wr40.cardiary.model.dto.technicalService.TechnicalServiceResponseDTO;
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
    @DisplayName("Should Throw Exception When Maintenance Event Is Not Present In Database")
    void shouldThrowExceptionWhenMaintenanceEventIsNotPresentInDatabase() {
        // Given
        Long tServiceId = 1L;
        TechnicalService technicalService = new TechnicalService();
        Mockito.when(technicalServiceRepository.existsById(any(Long.class))).thenReturn(false);

        // When & Then
        assertThrows(NoSuchTechnicalServiceFoundException.class, () -> technicalServiceService.updateTechnicalService(tServiceId, technicalService));
    }
}
