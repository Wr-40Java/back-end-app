package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.MaintenanaceEventDTO;
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

    @PostMapping("/{maintenance_history_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceEvent saveMaintenanceEvent(@Valid @RequestBody MaintenanaceEventDTO maintenanaceEventDTO,
                                                 @PathVariable(name = "maintenance_history_id") Long mHistoryId) {
        MaintenanceEvent maintenanceEvent = modelMapper.map(maintenanaceEventDTO, MaintenanceEvent.class);
        return maintenanceEventService.saveMaintenanceEvent(mHistoryId, maintenanceEvent);
    }
}
