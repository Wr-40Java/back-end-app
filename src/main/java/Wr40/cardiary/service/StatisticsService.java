package Wr40.cardiary.service;

import Wr40.cardiary.model.dto.statistics.*;
import Wr40.cardiary.repo.MaintenanceEventRepository;
import Wr40.cardiary.repo.TaxTypeRepository;
import Wr40.cardiary.repo.TechnicalServiceRepository;
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

    private TechnicalServiceRepository technicalServiceRepository;
    private MaintenanceEventRepository maintenanceEventRepository;
    private TaxTypeRepository taxTypeRepository;

    public TechnicalServiceStatisticsDTO getTechnicalServiceStats() {
        List<SumTechnicalServiceStatsDTO> technicalServiceStatsWithSumOfExpensesDTO = new ArrayList<>();
        List<AvgTechnicalServiceStatsDTO> technicalServiceStatsWithAvgOfExpensesDTO = new ArrayList<>();
        List<String> technicalServiceStatsWithSumOfExpenses = technicalServiceRepository.getTechnicalServiceStatsWithSumOfExpenses();
        System.out.println(technicalServiceStatsWithSumOfExpenses);
        List<String> technicalServiceStatsWithAvgOfExpenses = technicalServiceRepository.getTechnicalServiceStatsWithAvgOfExpenses();

        for (String object : technicalServiceStatsWithSumOfExpenses) {
            String[] objectStatsArray = object.split(";");
            SumTechnicalServiceStatsDTO sumTechnicalServiceStatsDTO = new SumTechnicalServiceStatsDTO(
                    BigDecimal.valueOf(Double.parseDouble(objectStatsArray[0])), objectStatsArray[1], objectStatsArray[2], objectStatsArray[3]);
            technicalServiceStatsWithSumOfExpensesDTO.add(sumTechnicalServiceStatsDTO);
        }

        for (String object : technicalServiceStatsWithAvgOfExpenses) {
            String[] objectStatArray = object.split(";");
            AvgTechnicalServiceStatsDTO avgTechnicalServiceStatsDTO = new AvgTechnicalServiceStatsDTO(
                    BigDecimal.valueOf(Double.parseDouble(objectStatArray[0])), objectStatArray[1], objectStatArray[2], objectStatArray[3]);
            technicalServiceStatsWithAvgOfExpensesDTO.add(avgTechnicalServiceStatsDTO);

        }
        return new TechnicalServiceStatisticsDTO(technicalServiceStatsWithSumOfExpensesDTO, technicalServiceStatsWithAvgOfExpensesDTO);
    }

        public MaintenanceStatisticsDTO getMaintenanceStatistics () {
            List<SumMaintenanceStatsDTO> maintenanceStatsWithSumOfExpensesDTO = new ArrayList<>();
            List<AvgMaintenanceStatsDTO> maintenanceStatsWithAvgOfExpensesDTO = new ArrayList<>();
            List<String> maintenanceStatsWithSumOfExpenses = maintenanceEventRepository.getMaintenanceStatsWithSumOfExpenses();
            System.out.println(maintenanceStatsWithSumOfExpenses);
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

        public TaxStatisticsDTO getTaxStatistics () {
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
