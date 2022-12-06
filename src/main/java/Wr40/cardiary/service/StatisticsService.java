package Wr40.cardiary.service;

import Wr40.cardiary.model.dto.statistics.AvgTaxStatsDTO;
import Wr40.cardiary.model.dto.statistics.SumTaxStatsDTO;
import Wr40.cardiary.model.dto.statistics.TaxStatisticsDTO;
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

    private TaxTypeRepository taxTypeRepository;

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
