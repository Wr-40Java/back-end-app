package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.MaintenanaceEventDTO;
import Wr40.cardiary.model.entity.MaintenanaceEvent;
import Wr40.cardiary.service.MaintenanaceEventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cardiary/maintenanceevent")
@AllArgsConstructor
public class MaintenanceEventController {

    private MaintenanaceEventService maintenanaceEventService;
    private ModelMapper modelMapper;

    @PostMapping("/save/{maintenance_history_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanaceEvent saveMaintenanceEvent(@Valid @RequestBody MaintenanaceEventDTO maintenanaceEventDTO,
                                     @PathVariable(name = "maintenance_history_id") Long mHistoryId) {
        MaintenanaceEvent maintenanceEvent = modelMapper.map(maintenanaceEventDTO, MaintenanaceEvent.class);
        return maintenanaceEventService.saveMaintenanceEvent(mHistoryId, maintenanceEvent);
    }
}
