package Wr40.cardiary.controller;


import Wr40.cardiary.model.dto.maintenance.MaintenanceHistoryDTO;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.service.MaintenanceHistoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance_history")
@AllArgsConstructor
public class MaintenanceHistoryController {

    private MaintenanceHistoryService maintenanceHS;
    private ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/{vin}")
    @ResponseStatus(HttpStatus.CREATED)
    public MaintenanceHistory saveMH(@Valid @RequestBody MaintenanceHistoryDTO maintenance,
                                     @PathVariable(name = "vin") String vin) {
        MaintenanceHistory mh = modelMapper.map(maintenance, MaintenanceHistory.class);
        maintenanceHS.persistMaintenanceEventAndTechnicalService(mh,
                maintenance.getMaintenanceEventDTO(),
                maintenance.getTechnicalServiceDTO());

        return maintenanceHS.saveMH(vin, mh);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/{maintenance_id}")
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceHistory updateMH(@Valid @RequestBody MaintenanceHistoryDTO maintenance,
                                       @PathVariable(name = "maintenance_id") Long id) {
        MaintenanceHistory mh = modelMapper.map(maintenance, MaintenanceHistory.class);
        maintenanceHS.persistMaintenanceEventAndTechnicalService(mh,
                maintenance.getMaintenanceEventDTO(),
                maintenance.getTechnicalServiceDTO());

        return maintenanceHS.updateMH(id, mh);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/{maintenance_id}")
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceHistory getMaintenanceHistory(
            @PathVariable(name = "maintenance_id") Long maintenanceId) {
        return maintenanceHS.getMaintenanceHistory(maintenanceId);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<MaintenanceHistory> getAllMaintenanceHistory() {
        return maintenanceHS.getAllMaintenanceHistory();
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/car/{vin}")
    @ResponseStatus(HttpStatus.OK)
    public List<MaintenanceHistory> getAllMaintenanceHistoryForGivenCar(@PathVariable(name = "vin") String vin) {
        return maintenanceHS.getAllMaintenanceHistoryForGivenCar(vin);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @DeleteMapping("/{maintenance_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMaintenanceHistory(
            @PathVariable(name = "maintenance_id") Long maintenanceId) {
        maintenanceHS.deleteMaintenanceHistory(maintenanceId);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMaintenanceHistory() {
        maintenanceHS.deleteAllMaintenanceHistory();
    }

}
