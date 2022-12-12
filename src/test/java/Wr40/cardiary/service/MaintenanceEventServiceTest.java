package Wr40.cardiary.service;


import Wr40.cardiary.exception.MaintenanceEventAlreadyExistsException;
import Wr40.cardiary.exception.NoSuchMaintenanceEventFoundException;
import Wr40.cardiary.exception.UnableToDeleteMaintenanceEventException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    @DisplayName("Should Update Given Maintenance History When Update Maintenance History")
    void shouldUpdateGivenMaintenanceHistoryWhenUpdateMaintenanceHistory() {
        //Given
        MaintenanceEvent mEvent1 = new MaintenanceEvent();
        MaintenanceEvent mEvent2 = new MaintenanceEvent();
        Long mEventId = 1L;
        mEvent2.setDescription("Technical maintenance");
        MaintenanceEventResponseDTO mERDTO = new MaintenanceEventResponseDTO();
        mERDTO.setDescription(mEvent2.getDescription());

        Mockito.when(maintenanceEventRepository.existsById(any(Long.class))).thenReturn(true);
        Mockito.when(maintenanceEventRepository.save(any(MaintenanceEvent.class))).thenReturn(mEvent2);
        Mockito.when(modelMapper.map(mEvent2, MaintenanceEventResponseDTO.class)).thenReturn(mERDTO);

        // When
        MaintenanceEventResponseDTO updateMH = maintenanceEventService.updateMaintenanceEvent(mEventId, mEvent1);

        // Then
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
        assertThrows(NoSuchMaintenanceEventFoundException.class, () -> maintenanceEventService.updateMaintenanceEvent(mEventId, mEvent));
    }

    @Test
    @DisplayName("Should Throw Exception When Getting ME Which Is Not Present In Database")
    void shouldThrowExceptionWhenGettingMeWhichIsNotPresentInDatabase() {
        // Given
        Long mEventId = 1L;
        Mockito.when(maintenanceEventRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoSuchMaintenanceEventFoundException.class, () -> maintenanceEventService.getMaintenanceEvent(mEventId));
    }

    @Test
    @DisplayName("Should Get Maintenance Event When Getting Maintenance Event By Id")
    void shouldGetMaintenanceEventWhenGettingMeById() {
        // Given
        MaintenanceEvent mEvent = new MaintenanceEvent();
        MaintenanceEventResponseDTO mERDTO = new MaintenanceEventResponseDTO();
        Long mEventId = 1L;

        Mockito.when(maintenanceEventRepository.findById(any(Long.class))).thenReturn(Optional.of(mEvent));
        Mockito.when(modelMapper.map(mEvent, MaintenanceEventResponseDTO.class)).thenReturn(mERDTO);

        // When
        MaintenanceEventResponseDTO maintenanceEventActual = maintenanceEventService.getMaintenanceEvent(mEventId);

        // Then
        Assertions.assertEquals(mERDTO, maintenanceEventActual);
        verify(maintenanceEventRepository).findById(mEventId);
    }

    @Test
    @DisplayName("Should Provide All Maintenance Event When Getting All Maintenance Event")
    void shouldProvideAllMaintenanceEventWhenGettingAllMaintenanceEvent() {
        // Given
        List<MaintenanceEvent> mELists = new ArrayList<>();
        MaintenanceEvent mEvent = new MaintenanceEvent();
        MaintenanceEventResponseDTO mERDTO = new MaintenanceEventResponseDTO();
        List<MaintenanceEventResponseDTO> listMERDTO = new ArrayList<>();
        listMERDTO.add(mERDTO);
        mELists.add(mEvent);
        Mockito.when(maintenanceEventRepository.findAll()).thenReturn(mELists);
        Mockito.when(modelMapper.map(mEvent, MaintenanceEventResponseDTO.class)).thenReturn(mERDTO);
        // Given
        List<MaintenanceEventResponseDTO> mEventListsSaved = maintenanceEventService.getAllMaintenanceEvent();

        // When
        assertEquals(listMERDTO, mEventListsSaved);
        verify(maintenanceEventRepository).findAll();
    }

    @Test
    @DisplayName("Should Delete Maintenance Event When Given Id Is Present In Database")
    void shouldDeleteMaintenanceEventWhenGivenIdIsPresentInDatabase() {
        // Given
        MaintenanceEvent mEvent = new MaintenanceEvent();
        Long mEventId = 1L;

        Mockito.when(maintenanceEventRepository.findById(any(Long.class))).thenReturn(Optional.of(mEvent));
        Mockito.when(maintenanceEventRepository.existsById(any(Long.class))).thenReturn(false);

        // When
        maintenanceEventService.deleteMaintenanceEvent(mEventId);

        // Then
        verify(maintenanceEventRepository).delete(mEvent);
        verify(maintenanceEventRepository).existsById(mEventId);
    }

    @Test
    @DisplayName("Should Throw Exception When Deleting Maintenance Event With Wrong Id")
    void shouldThrowExceptionWhenDeletingMaintenanceEventWithWrongId() {
        // Given
        Long id = 1L;

        // When & Then
        assertThrows(NoSuchMaintenanceEventFoundException.class, () -> maintenanceEventService.deleteMaintenanceEvent(id));
    }

    @Test
    @DisplayName("Should Throw Exception When Unable To Delete From Database")
    void shouldThrowExceptionWhenUnableToDeleteFromDatabase() {
        // Given
        MaintenanceEvent mEvent = new MaintenanceEvent();
        Long mEventId = 1L;
        Mockito.when(maintenanceEventRepository.findById(any(Long.class))).thenReturn(Optional.of(mEvent));
        Mockito.when(maintenanceEventRepository.existsById(any(Long.class))).thenReturn(true);
        // When & Then

        assertThrows(UnableToDeleteMaintenanceEventException.class, () -> maintenanceEventService.deleteMaintenanceEvent(mEventId));
    }

    @Test
    @DisplayName("Should Delete All Maintenance Event When Delete All Was Requested")
    void shouldDeleteAllMaintenanceEventWhenDeleteAllWasRequested() {
        // Given
        maintenanceEventService.deleteAllMaintenanceEvent();

        // When & Then
        verify(maintenanceEventRepository).deleteAll();
    }

    @Test
    @DisplayName("Should Throw Exception When Unable To Delete All Maintenance Event From Database")
    void shouldThrowExceptionWhenUnableToDeleteAllMaintenanceEventFromDatabase() {
        // Given
        List<MaintenanceEvent> listME = new ArrayList<>();
        MaintenanceEvent mEvent = new MaintenanceEvent();
        listME.add(mEvent);
        Mockito.when(maintenanceEventRepository.findAll()).thenReturn(listME);

        // When & Then
        assertThrows(UnableToDeleteMaintenanceEventException.class, () -> maintenanceEventService.deleteAllMaintenanceEvent());
    }
}
