package Wr40.cardiary.service;

import Wr40.cardiary.model.dto.statistics.AvgMaintenanceStatsDTO;
import Wr40.cardiary.model.dto.statistics.SumMaintenanceStatsDTO;
import Wr40.cardiary.model.dto.statistics.MaintenanceStatisticsDTO;
import Wr40.cardiary.repo.MaintenanceEventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class StatisticsService {

    private MaintenanceEventRepository maintenanceEventRepository;

    public MaintenanceStatisticsDTO getMaintenanceStatistics() {
        List<SumMaintenanceStatsDTO> maintenanceStatsWithSumOfExpensesDTO = new ArrayList<>();
        List<AvgMaintenanceStatsDTO> maintenanceStatsWithAvgOfExpensesDTO = new ArrayList<>();
        List<String> maintenanceStatsWithSumOfExpenses = maintenanceEventRepository.getMaintenanceStatsWithSumOfExpenses();
        List<String> maintenanceStatsWithAvgOfExpenses = maintenanceEventRepository.getMaintenanceStatsWithAvgOfExpenses();

        for (String object : maintenanceStatsWithSumOfExpenses) {
            String[] objectStatArray = object.split(";");
            SumMaintenanceStatsDTO sumMaintenanceStatsDTO = new SumMaintenanceStatsDTO(
                    BigDecimal.valueOf(Double.parseDouble(objectStatArray[0])), objectStatArray[1], objectStatArray[3]);
            maintenanceStatsWithSumOfExpensesDTO.add(sumMaintenanceStatsDTO);
        }

        for (String object : maintenanceStatsWithAvgOfExpenses) {
            String[] objectStatArray = object.split(";");
            AvgMaintenanceStatsDTO avgMaintenanceStatsDTO = new AvgMaintenanceStatsDTO(
                    BigDecimal.valueOf(Double.parseDouble(objectStatArray[0])), objectStatArray[1], objectStatArray[3]);
            maintenanceStatsWithAvgOfExpensesDTO.add(avgMaintenanceStatsDTO);
        }
        return new MaintenanceStatisticsDTO(maintenanceStatsWithAvgOfExpensesDTO, maintenanceStatsWithSumOfExpensesDTO);
    }
}

