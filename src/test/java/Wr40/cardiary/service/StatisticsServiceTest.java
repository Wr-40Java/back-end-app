package Wr40.cardiary.service;

import Wr40.cardiary.model.dto.statistics.AvgTaxStatsDTO;
import Wr40.cardiary.model.dto.statistics.SumTaxStatsDTO;
import Wr40.cardiary.model.dto.statistics.TaxStatisticsDTO;
import Wr40.cardiary.repo.TaxTypeRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {

    @Mock
    private TaxTypeRepository taxTypeRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    @Test
    public void whenSearchedForTaxStatistic_shouldReturnProperObjects() {

        //given
        List<SumTaxStatsDTO> taxStatsWithSumOfTransactionsDTO = new ArrayList<>();
        List<AvgTaxStatsDTO> taxStatsWithAVGOfTransactionsDTO = new ArrayList<>();

        SumTaxStatsDTO sumTaxStatsDTO = new SumTaxStatsDTO(BigDecimal.valueOf(2000.00), "property_tax", "once_after_i_buy", "national_institution");
        SumTaxStatsDTO sumTaxStatsDTO2 = new SumTaxStatsDTO(BigDecimal.valueOf(100.00), "income_tax", "once_per_month", "national_institution");
        taxStatsWithSumOfTransactionsDTO.add(sumTaxStatsDTO);
        taxStatsWithSumOfTransactionsDTO.add(sumTaxStatsDTO2);

        AvgTaxStatsDTO avgTaxStatsDTO = new AvgTaxStatsDTO(BigDecimal.valueOf(2000.00), "property_tax", "once_after_i_buy", "national_institution");
        AvgTaxStatsDTO avgTaxStatsDTO2 = new AvgTaxStatsDTO(BigDecimal.valueOf(100.00), "income_tax", "once_per_month", "national_institution");
        taxStatsWithAVGOfTransactionsDTO.add(avgTaxStatsDTO);
        taxStatsWithAVGOfTransactionsDTO.add(avgTaxStatsDTO2);

        TaxStatisticsDTO taxStatisticsDTO = new TaxStatisticsDTO(taxStatsWithAVGOfTransactionsDTO, taxStatsWithSumOfTransactionsDTO);

        String sumSample = "2000.00;property_tax;once_after_i_buy;national_institution";
        String sumSample2 = "100.00;income_tax;once_per_month;national_institution";
        List<String> sumSamples = new ArrayList<>();
        sumSamples.add(sumSample);
        sumSamples.add(sumSample2);

        String avgSample = "2000.00;property_tax;once_after_i_buy;national_institution";
        String avgSample2 = "100.00;income_tax;once_per_month;national_institution";
        List<String> avgSamples = new ArrayList<>();
        avgSamples.add(avgSample);
        avgSamples.add(avgSample2);

        Mockito.when(taxTypeRepository.getTaxStatsWithSumOfTransactions()).thenReturn(sumSamples);
        Mockito.when(taxTypeRepository.getTaxStatsWithAVGOfTransactions()).thenReturn(avgSamples);

        //when
        TaxStatisticsDTO actual = statisticsService.getTaxStatistics();

        assertThat(actual).isEqualTo(taxStatisticsDTO);
        assertThat(actual.getAvgTaxStats()).isEqualTo(taxStatsWithAVGOfTransactionsDTO);
        assertThat(actual.getSumTaxStats()).isEqualTo(taxStatsWithSumOfTransactionsDTO);
        assertThat(actual.getAvgTaxStats()).containsExactlyInAnyOrderElementsOf(taxStatsWithAVGOfTransactionsDTO);
        assertThat(actual.getSumTaxStats()).containsExactlyInAnyOrderElementsOf(taxStatsWithSumOfTransactionsDTO);

        Mockito.verify(taxTypeRepository).getTaxStatsWithSumOfTransactions();
        Mockito.verify(taxTypeRepository).getTaxStatsWithAVGOfTransactions();
    }

}