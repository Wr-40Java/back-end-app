package Wr40.cardiary.service;

import Wr40.cardiary.exception.MaintenanceEventAlreadyExistsException;
import Wr40.cardiary.exception.NoSuchMaintenanceEventFoundException;
import Wr40.cardiary.exception.UnableToDeleteMaintenanceEventException;
import Wr40.cardiary.model.dto.maintenance.MaintenanceEventResponseDTO;
import Wr40.cardiary.model.entity.MaintenanceEvent;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.repo.MaintenanceEventRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static Wr40.cardiary.util.Calculations.calculateMaintenanceCost;

@Service
@AllArgsConstructor
public class MaintenanceEventService {

    private MaintenanceEventRepository maintenanceEventRepository;
    private MaintenanceHistoryService maintenanceHistoryService;
    private ModelMapper modelMapper;

    @Transactional
    public MaintenanceEventResponseDTO saveMaintenanceEvent(Long mHistoryId, MaintenanceEvent maintenanceEvent) {
        MaintenanceHistory maintenanceHistory = maintenanceHistoryService.getMaintenanceHistory(mHistoryId);
        if (maintenanceHistory.getMaintenanceEvent() != null) {
            throw new MaintenanceEventAlreadyExistsException();
        }

        MaintenanceEvent savedMaintenanceEvent = maintenanceEventRepository.save(maintenanceEvent);
        maintenanceHistory.setMaintenanceEvent(savedMaintenanceEvent);
        calculateMaintenanceCost(maintenanceHistory);
        maintenanceHistoryService.updateMH(mHistoryId, maintenanceHistory);

        return modelMapper.map(savedMaintenanceEvent, MaintenanceEventResponseDTO.class);
    }

    public MaintenanceEventResponseDTO updateMaintenanceEvent(Long mEventId, MaintenanceEvent maintenanceEvent) {
        if (!maintenanceEventRepository.existsById(mEventId)) {
            throw new NoSuchMaintenanceEventFoundException();
        }
        maintenanceEvent.setId(mEventId);
        MaintenanceEvent savedMaintenanceEvent = maintenanceEventRepository.save(maintenanceEvent);

        return modelMapper.map(savedMaintenanceEvent, MaintenanceEventResponseDTO.class);
    }

    public MaintenanceEventResponseDTO getMaintenanceEvent(Long mEventId) {
        MaintenanceEvent maintenanceEvent = maintenanceEventRepository.findById(mEventId)
                .orElseThrow(NoSuchMaintenanceEventFoundException::new);

        return modelMapper.map(maintenanceEvent, MaintenanceEventResponseDTO.class);
    }

    public List<MaintenanceEventResponseDTO> getAllMaintenanceEvent() {
        return maintenanceEventRepository.findAll()
                .stream()
                .map(me -> modelMapper.map(me, MaintenanceEventResponseDTO.class))
                .toList();
    }

    public void deleteMaintenanceEvent(Long mEventId) {
        MaintenanceEvent maintenanceEvent = maintenanceEventRepository.findById(mEventId)
                .orElseThrow(NoSuchMaintenanceEventFoundException::new);
        maintenanceEventRepository.delete(maintenanceEvent);
        if (maintenanceEventRepository.existsById(mEventId)) {
            throw new UnableToDeleteMaintenanceEventException();
        }
    }

    public Long deleteAllMaintenanceEvent() {
        long sizeOfMaintenanceEventTable = maintenanceEventRepository.findAll().size();
        maintenanceEventRepository.deleteAll();
        long sizeAfterDelete = maintenanceEventRepository.findAll().size();
        if (sizeAfterDelete == 0L) {
            return sizeOfMaintenanceEventTable;
        } else {
            throw new UnableToDeleteMaintenanceEventException();
        }
    }
}

