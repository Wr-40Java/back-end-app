package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.maintenance.MaintenanceEventDTO;
import Wr40.cardiary.model.dto.maintenance.MaintenanceEventResponseDTO;
import Wr40.cardiary.model.entity.MaintenanceEvent;
import Wr40.cardiary.service.MaintenanceEventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
