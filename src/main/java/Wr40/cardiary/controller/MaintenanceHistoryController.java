package Wr40.cardiary.controller;


import Wr40.cardiary.model.dto.MaintenanceHistoryDTO;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.service.MaintenanceHistoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cardiary/maintenancehistory")
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

    @GetMapping("/get/{maintenance_id}")
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceHistory getMaintenanceHistory(
            @PathVariable(name = "maintenance_id") Long maintenanceId) {
        return maintenanceHS.getMaintenanceHistory(maintenanceId);
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<MaintenanceHistory> getAllMaintenanceHistory() {
        return maintenanceHS.getAllMaintenanceHistory();
    }

    @DeleteMapping("/delete/{maintenance_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMaintenanceHistory(
            @PathVariable(name = "maintenance_id") Long maintenanceId) {
        maintenanceHS.deleteMaintenanceHistory(maintenanceId);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMaintenanceHistory() {
        maintenanceHS.deleteAllMaintenanceHistory();
    }

}
