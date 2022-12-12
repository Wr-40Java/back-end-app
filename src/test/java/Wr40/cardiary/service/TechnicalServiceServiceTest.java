package Wr40.cardiary.service;

import Wr40.cardiary.exception.TechnicalServiceAlreadyExistsException;
import Wr40.cardiary.model.dto.technicalService.TechnicalServiceResponseDTO;
import Wr40.cardiary.model.dto.technicalService.TechnicalServiceDTO;
import Wr40.cardiary.model.entity.TechnicalService;
import Wr40.cardiary.model.entity.MaintenanceHistory;
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
public class TechnicalServiceServiceTest {

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
}
