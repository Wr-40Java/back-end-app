package Wr40.cardiary.controller;

import Wr40.cardiary.model.dto.statistics.TaxStatisticsDTO;
import Wr40.cardiary.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cardiary/statistics")
@AllArgsConstructor
public class StatisticsController {
    private StatisticsService statisticsService;
    @GetMapping("/tax")
    @ResponseStatus(HttpStatus.OK)
    public TaxStatisticsDTO getInsuranceTypes() {
        return statisticsService.getTaxStatistics();
    }
}
