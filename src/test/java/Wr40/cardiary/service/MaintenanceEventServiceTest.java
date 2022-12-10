package Wr40.cardiary.service;


import Wr40.cardiary.exception.MaintenanceEventAlreadyExistsException;
import Wr40.cardiary.model.dto.maintenance.MaintenanceEventResponseDTO;
import Wr40.cardiary.model.entity.MaintenanceEvent;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.repo.MaintenanceEventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MaintenanceEventServiceTest {

    @Mock
    MaintenanceHistoryService maintenanceHistoryService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    private MaintenanceEventRepository maintenanceEventRepository;
    @InjectMocks
    private MaintenanceEventService maintenanceEventService;

    @Test
    @DisplayName("Should Save Maintenance Event When Maintenance History Have Null Object")
    void shouldSaveMaintenanceEventWhenMaintenanceHistoryHaveNullObject() {
        // Given
        Long mHId = 1L;
        MaintenanceEvent maintenanceEvent = new MaintenanceEvent();
        MaintenanceHistory mh = new MaintenanceHistory();
        mh.setId(mHId);

        MaintenanceEventResponseDTO mERDTO = new MaintenanceEventResponseDTO();

        Mockito.when(modelMapper.map(maintenanceEvent, MaintenanceEventResponseDTO.class)).thenReturn(mERDTO);
        Mockito.when(maintenanceHistoryService.getMaintenanceHistory(any(Long.class))).thenReturn(mh);
        Mockito.when(maintenanceEventRepository.save(any(MaintenanceEvent.class))).thenReturn(maintenanceEvent);
        Mockito.when(maintenanceHistoryService.updateMH(any(Long.class), any(MaintenanceHistory.class))).thenReturn(mh);

        // When
        MaintenanceEventResponseDTO maintenanceEventResponseDTO = maintenanceEventService.saveMaintenanceEvent(mHId, maintenanceEvent);

        // Then
        Assertions.assertEquals(mERDTO, maintenanceEventResponseDTO);
        verify(maintenanceEventRepository).save(maintenanceEvent);
    }

    @Test
    @DisplayName("Should Throw Exception When Saving Maintenance Event To Maintenance History With Already Assigned Maintenance Event")
    void shouldThrowExceptionWhenSavingMaintenanceEventToMaintenanceHistoryWithAlreadyAssignedMaintenanceEvent() {
        // Given
        MaintenanceEvent mEvent = new MaintenanceEvent();
        MaintenanceHistory mHistory = new MaintenanceHistory();
        mHistory.setMaintenanceEvent(mEvent);
        Long id = 1L;
        Mockito.when(maintenanceHistoryService.getMaintenanceHistory(any(Long.class))).thenReturn(mHistory);

        // When & Then
        assertThrows(MaintenanceEventAlreadyExistsException.class, () -> maintenanceEventService.saveMaintenanceEvent(id, mEvent));

    }
}
