package Wr40.cardiary.controller;


import Wr40.cardiary.model.dto.MaintenanceHistoryDTO;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.service.MaintenanceHistoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cardiary/maintenanceHistory")
@AllArgsConstructor
public class MaintenanceHistoryController {

    private MaintenanceHistoryService maintenanceHS;
    private ModelMapper modelMapper;

    @PostMapping("/save/{vin}")
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceHistory saveMH(@Valid @RequestBody MaintenanceHistoryDTO maintenance,
                                     @PathVariable(name = "vin") String vin) {
        MaintenanceHistory mh = modelMapper.map(maintenance, MaintenanceHistory.class);
        return maintenanceHS.saveMH(vin, mh);
    }

    @PutMapping("/update/{maintenance_id}")
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceHistory updateMH(@Valid @RequestBody MaintenanceHistoryDTO maintenance,
                                       @PathVariable(name = "maintenance_id") Long id) {
        MaintenanceHistory mh = modelMapper.map(maintenance, MaintenanceHistory.class);
        return maintenanceHS.updateMH(id, mh);
    }
}
