package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.maintenance.MaintenanceEventDTO;
import Wr40.cardiary.model.dto.maintenance.MaintenanceEventResponseDTO;
import Wr40.cardiary.model.entity.MaintenanceEvent;
import Wr40.cardiary.service.MaintenanceEventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance_event")
@AllArgsConstructor
public class MaintenanceEventController {

    private MaintenanceEventService maintenanceEventService;
    private ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/maintenance_history/{maintenance_history_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceEventResponseDTO saveMaintenanceEvent(@Valid @RequestBody MaintenanceEventDTO maintenanaceEventDTO,
                                                            @PathVariable(name = "maintenance_history_id") Long mHistoryId) {
        MaintenanceEvent maintenanceEvent = modelMapper.map(maintenanaceEventDTO, MaintenanceEvent.class);
        return maintenanceEventService.saveMaintenanceEvent(mHistoryId, maintenanceEvent);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/{maintenance_event_id}")
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceEventResponseDTO updateMaintenanceEvent(@Valid @RequestBody MaintenanceEventDTO maintenanceEventDTO,
                                                              @PathVariable(name = "maintenance_event_id") Long mEventId) {
        MaintenanceEvent maintenanceEvent = modelMapper.map(maintenanceEventDTO, MaintenanceEvent.class);
        return maintenanceEventService.updateMaintenanceEvent(mEventId, maintenanceEvent);

    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{maintenance_event_id}")
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceEventResponseDTO getMaintenanceEvent(@PathVariable(name = "maintenance_event_id") Long mEventId) {
        return maintenanceEventService.getMaintenanceEvent(mEventId);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<MaintenanceEventResponseDTO> getAllMaintenanceEvent() {
        return maintenanceEventService.getAllMaintenanceEvent();
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("/{maintenance_event_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMaintenanceEvent(@PathVariable(name = "maintenance_event_id") Long mEventId) {
        maintenanceEventService.deleteMaintenanceEvent(mEventId);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllMaintenanceEvent() {
        maintenanceEventService.deleteAllMaintenanceEvent();
    }
}
