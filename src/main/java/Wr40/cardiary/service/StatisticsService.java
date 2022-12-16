package Wr40.cardiary.service;

import Wr40.cardiary.model.dto.statistics.*;
import Wr40.cardiary.repo.MaintenanceEventRepository;
import Wr40.cardiary.repo.TaxTypeRepository;
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
    private TaxTypeRepository taxTypeRepository;

    public MaintenanceStatisticsDTO getMaintenanceStatistics() {
        List<SumMaintenanceStatsDTO> maintenanceStatsWithSumOfExpensesDTO = new ArrayList<>();
        List<AvgMaintenanceStatsDTO> maintenanceStatsWithAvgOfExpensesDTO = new ArrayList<>();
        List<String> maintenanceStatsWithSumOfExpenses = maintenanceEventRepository.getMaintenanceStatsWithSumOfExpenses();
        List<String> maintenanceStatsWithAvgOfExpenses = maintenanceEventRepository.getMaintenanceStatsWithAvgOfExpenses();

        for (String object : maintenanceStatsWithSumOfExpenses) {
            String[] objectStatArray = object.split(";");
            SumMaintenanceStatsDTO sumMaintenanceStatsDTO = new SumMaintenanceStatsDTO(
                    BigDecimal.valueOf(Double.parseDouble(objectStatArray[0])), objectStatArray[1], objectStatArray[2]);
            maintenanceStatsWithSumOfExpensesDTO.add(sumMaintenanceStatsDTO);
        }

        for (String object : maintenanceStatsWithAvgOfExpenses) {
            String[] objectStatArray = object.split(";");
            AvgMaintenanceStatsDTO avgMaintenanceStatsDTO = new AvgMaintenanceStatsDTO(
                    BigDecimal.valueOf(Double.parseDouble(objectStatArray[0])), objectStatArray[1], objectStatArray[2]);
            maintenanceStatsWithAvgOfExpensesDTO.add(avgMaintenanceStatsDTO);
        }
        return new MaintenanceStatisticsDTO(maintenanceStatsWithSumOfExpensesDTO, maintenanceStatsWithAvgOfExpensesDTO);
    }

    public TaxStatisticsDTO getTaxStatistics() {
        List<SumTaxStatsDTO> taxStatsWithSumOfTransactionsDTO = new ArrayList<>();
        List<AvgTaxStatsDTO> taxStatsWithAVGOfTransactionsDTO = new ArrayList<>();
        List<String> taxStatsWithSumOfTransactions = taxTypeRepository.getTaxStatsWithSumOfTransactions();
        List<String> taxStatsWithAVGOfTransactions = taxTypeRepository.getTaxStatsWithAVGOfTransactions();

        for (String object : taxStatsWithSumOfTransactions) {
            String[] objectStatArray = object.split(";");
            SumTaxStatsDTO sumTaxStatsDTO = new SumTaxStatsDTO(
                    BigDecimal.valueOf(Double.parseDouble(objectStatArray[0])), objectStatArray[1], objectStatArray[2], objectStatArray[3]);
            taxStatsWithSumOfTransactionsDTO.add(sumTaxStatsDTO);
        }

        for (String object : taxStatsWithAVGOfTransactions) {
            String[] objectStatArray = object.split(";");
            AvgTaxStatsDTO sumTaxStatsDTO = new AvgTaxStatsDTO(
                    BigDecimal.valueOf(Double.parseDouble(objectStatArray[0])), objectStatArray[1], objectStatArray[2], objectStatArray[3]);
            taxStatsWithAVGOfTransactionsDTO.add(sumTaxStatsDTO);
        }
        return new TaxStatisticsDTO(taxStatsWithAVGOfTransactionsDTO, taxStatsWithSumOfTransactionsDTO);
    }
}
