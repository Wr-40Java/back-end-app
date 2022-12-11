package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.maintenance.MaintenanceEventDTO;
import Wr40.cardiary.model.dto.maintenance.MaintenanceEventResponseDTO;
import Wr40.cardiary.model.entity.MaintenanceEvent;
import Wr40.cardiary.service.MaintenanceEventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance_event")
@AllArgsConstructor
public class MaintenanceEventController {

    private MaintenanceEventService maintenanceEventService;
    private ModelMapper modelMapper;

    @PostMapping("/maintenance_history/{maintenance_history_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceEventResponseDTO saveMaintenanceEvent(@Valid @RequestBody MaintenanceEventDTO maintenanaceEventDTO,
                                                            @PathVariable(name = "maintenance_history_id") Long mHistoryId) {
        MaintenanceEvent maintenanceEvent = modelMapper.map(maintenanaceEventDTO, MaintenanceEvent.class);
        return maintenanceEventService.saveMaintenanceEvent(mHistoryId, maintenanceEvent);
    }

    @PutMapping("/{maintenance_event_id}")
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceEventResponseDTO updateMaintenanceEvent(@Valid @RequestBody MaintenanceEventDTO maintenanceEventDTO,
                                                              @PathVariable(name = "maintenance_event_id") Long mEventId) {
        MaintenanceEvent maintenanceEvent = modelMapper.map(maintenanceEventDTO, MaintenanceEvent.class);
        return maintenanceEventService.updateMaintenanceEvent(mEventId, maintenanceEvent);

    }

    @GetMapping("/{maintenance_event_id}")
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceEventResponseDTO getMaintenanceEvent(@PathVariable(name = "maintenance_event_id") Long mEventId) {
        return maintenanceEventService.getMaintenanceEvent(mEventId);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<MaintenanceEventResponseDTO> getAllMaintenanceEvent() {
        return maintenanceEventService.getAllMaintenanceEvent();
    }

    @DeleteMapping("/{maintenance_event_id}")
    public ResponseEntity<String> deleteMaintenanceEvent(@PathVariable(name = "maintenance_event_id") Long mEventId) {
        maintenanceEventService.deleteMaintenanceEvent(mEventId);
        return new ResponseEntity<>("Row Deleted: " + mEventId, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteAllMaintenanceEvent() {
        Long rowsAffected = maintenanceEventService.deleteAllMaintenanceEvent();
        return new ResponseEntity<>("Rows deleted: " + rowsAffected, HttpStatus.NO_CONTENT);
    }
}
