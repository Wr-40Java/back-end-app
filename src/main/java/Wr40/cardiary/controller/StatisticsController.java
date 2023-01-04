package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.statistics.MaintenanceStatisticsDTO;
import Wr40.cardiary.model.dto.statistics.TaxStatisticsDTO;
import Wr40.cardiary.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/statistics")
@AllArgsConstructor
public class StatisticsController {
    private StatisticsService statisticsSercvice;
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/maintenance")
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceStatisticsDTO getInsuranceTypes() {
        return statisticsSercvice.getMaintenanceStatistics();
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/tax")
    @ResponseStatus(HttpStatus.OK)
    public TaxStatisticsDTO getMaintenanceTypes() {
        return statisticsSercvice.getTaxStatistics();
    }
}
