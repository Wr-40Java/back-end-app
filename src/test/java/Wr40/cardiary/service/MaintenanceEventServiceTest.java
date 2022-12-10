package Wr40.cardiary.service;


import Wr40.cardiary.exception.MaintenanceEventAlreadyExistsException;
import Wr40.cardiary.exception.NoSuchMaintenanceEventFoundException;
import Wr40.cardiary.model.dto.maintenance.MaintenanceEventDTO;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.postgresql.hostchooser.HostRequirement.any;

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

    @Test
    @DisplayName("Should Update Given Maintenance History When Update Maintenance History")
    void shouldUpdateGivenMaintenanceHistoryWhenUpdateMaintenanceHistory() {
        MaintenanceEvent mEvent1 = new MaintenanceEvent();
        MaintenanceEvent mEvent2 = new MaintenanceEvent();
        Long mEventId = 1L;
        mEvent2.setDescription("Technical maintenance");
        MaintenanceEventResponseDTO mERDTO = new MaintenanceEventResponseDTO();
        mERDTO.setDescription(mEvent2.getDescription());

        Mockito.when(maintenanceEventRepository.existsById(any(Long.class))).thenReturn(true);
        Mockito.when(maintenanceEventRepository.save(any(MaintenanceEvent.class))).thenReturn(mEvent2);
        Mockito.when(modelMapper.map(mEvent2,MaintenanceEventResponseDTO.class)).thenReturn(mERDTO);
        MaintenanceEventResponseDTO updateMH = maintenanceEventService.updateMaintenanceEvent(mEventId, mEvent1);
        assertEquals("Technical maintenance", updateMH.getDescription());
    }

    @Test
    @DisplayName("Should Throw Exception When Maintenance Event Is Not Present In Database")
    void shouldThrowExceptionWhenMaintenanceEventIsNotPresentInDatabase() {
        // Given
        Long mEventId = 1L;
        MaintenanceEvent mEvent = new MaintenanceEvent();
        Mockito.when(maintenanceEventRepository.existsById(any(Long.class))).thenReturn(false);

        // When & Then
        assertThrows(NoSuchMaintenanceEventFoundException.class, () -> maintenanceEventService.updateMaintenanceEvent(mEventId,mEvent));
    }
}
