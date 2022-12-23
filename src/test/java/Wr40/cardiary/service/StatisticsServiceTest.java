package Wr40.cardiary.service;

import Wr40.cardiary.model.dto.statistics.*;
import Wr40.cardiary.repo.MaintenanceEventRepository;
import Wr40.cardiary.repo.TaxRepository;
import Wr40.cardiary.repo.TaxTypeRepository;
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

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {

    @Mock
    private MaintenanceEventRepository maintenanceEventRepository;
    @Mock
    private TaxTypeRepository taxTypeRepository;
    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    @Test
    public void smoke(){}

//    @Test
//    public void whenSearchedForMaintenanceStatistics_shouldReturnAppropriateObjects() {
//
//        // given
//        List<SumMaintenanceStatsDTO> maintenanceStatsWithSumOfExpensesDTO = new ArrayList<>();
//        List<AvgMaintenanceStatsDTO> maintenanceStatsWithAvgOfExpensesDTO = new ArrayList<>();
//
//        SumMaintenanceStatsDTO sumMaintenanceStatsDTO = new SumMaintenanceStatsDTO(BigDecimal.valueOf(1000.00), "car_repair_shop_1", "engine cylinder repair");
//        SumMaintenanceStatsDTO sumMaintenanceStatsDTO2 = new SumMaintenanceStatsDTO(BigDecimal.valueOf(125.00), "car_repair_shop_2", "xenon bulb replacement");
//        SumMaintenanceStatsDTO sumMaintenanceStatsDTO3 = new SumMaintenanceStatsDTO(BigDecimal.valueOf(75.00), "car_repair_shop_3", "tyre replacement");
//        maintenanceStatsWithSumOfExpensesDTO.add(sumMaintenanceStatsDTO);
//        maintenanceStatsWithSumOfExpensesDTO.add(sumMaintenanceStatsDTO2);
//        maintenanceStatsWithSumOfExpensesDTO.add(sumMaintenanceStatsDTO3);
//
//        AvgMaintenanceStatsDTO avgMaintenanceStatsDTO = new AvgMaintenanceStatsDTO(BigDecimal.valueOf(1000.00), "car_repair_shop_1", "engine cylinder repair");
//        AvgMaintenanceStatsDTO avgMaintenanceStatsDTO2 = new AvgMaintenanceStatsDTO(BigDecimal.valueOf(125.00), "car_repair_shop_2", "xenon bulb replacement");
//        AvgMaintenanceStatsDTO avgMaintenanceStatsDTO3 = new AvgMaintenanceStatsDTO(BigDecimal.valueOf(75.00), "car_repair_shop_3", "tyre replacement");
//        maintenanceStatsWithAvgOfExpensesDTO.add(avgMaintenanceStatsDTO);
//        maintenanceStatsWithAvgOfExpensesDTO.add(avgMaintenanceStatsDTO2);
//        maintenanceStatsWithAvgOfExpensesDTO.add(avgMaintenanceStatsDTO3);
//
//        MaintenanceStatisticsDTO maintenanceStatisticsDTO = new MaintenanceStatisticsDTO(maintenanceStatsWithSumOfExpensesDTO, maintenanceStatsWithAvgOfExpensesDTO);
//
//        String expenseData = "1000.00;car_repair_shop_1;engine cylinder repair";
//        String expenseData2 = "125.00;car_repair_shop_2;xenon bulb replacement";
//        String expenseData3 = "75.00;car_repair_shop_3;tyre replacement";
//
//        List<String> sumExpensesData = new ArrayList<>();
//        sumExpensesData.add(expenseData);
//        sumExpensesData.add(expenseData2);
//        sumExpensesData.add(expenseData3);
//
//        List<String> avgExpensesData = new ArrayList<>();
//        avgExpensesData.add(expenseData);
//        avgExpensesData.add(expenseData2);
//        avgExpensesData.add(expenseData3);
//
//        Mockito.when(maintenanceEventRepository.getMaintenanceStatsWithSumOfExpenses()).thenReturn(sumExpensesData);
//        Mockito.when(maintenanceEventRepository.getMaintenanceStatsWithAvgOfExpenses()).thenReturn(avgExpensesData);
//
//        // when
//        MaintenanceStatisticsDTO current = statisticsService.getMaintenanceStatistics();
//
//        assertThat(current).isEqualTo(maintenanceStatisticsDTO);
//        assertThat(current.getSumMaintenanceStats()).isEqualTo(maintenanceStatsWithSumOfExpensesDTO);
//        assertThat(current.getAvgMaintenanceStats()).isEqualTo(maintenanceStatsWithAvgOfExpensesDTO);
//        assertThat(current.getSumMaintenanceStats()).containsExactlyInAnyOrderElementsOf(maintenanceStatsWithSumOfExpensesDTO);
//        assertThat(current.getAvgMaintenanceStats()).containsExactlyInAnyOrderElementsOf(maintenanceStatsWithAvgOfExpensesDTO);
//
//        Mockito.verify(maintenanceEventRepository).getMaintenanceStatsWithSumOfExpenses();
//        Mockito.verify(maintenanceEventRepository).getMaintenanceStatsWithAvgOfExpenses();
//    }
//    public void whenSearchedForTaxStatistic_shouldReturnProperObjects() {
//
//        //given
//        List<SumTaxStatsDTO> taxStatsWithSumOfTransactionsDTO = new ArrayList<>();
//        List<AvgTaxStatsDTO> taxStatsWithAVGOfTransactionsDTO = new ArrayList<>();
//
//        SumTaxStatsDTO sumTaxStatsDTO = new SumTaxStatsDTO(BigDecimal.valueOf(2000.00), "property_tax", "once_after_i_buy", "national_institution");
//        SumTaxStatsDTO sumTaxStatsDTO2 = new SumTaxStatsDTO(BigDecimal.valueOf(100.00), "income_tax", "once_per_month", "national_institution");
//        taxStatsWithSumOfTransactionsDTO.add(sumTaxStatsDTO);
//        taxStatsWithSumOfTransactionsDTO.add(sumTaxStatsDTO2);
//
//        AvgTaxStatsDTO avgTaxStatsDTO = new AvgTaxStatsDTO(BigDecimal.valueOf(2000.00), "property_tax", "once_after_i_buy", "national_institution");
//        AvgTaxStatsDTO avgTaxStatsDTO2 = new AvgTaxStatsDTO(BigDecimal.valueOf(100.00), "income_tax", "once_per_month", "national_institution");
//        taxStatsWithAVGOfTransactionsDTO.add(avgTaxStatsDTO);
//        taxStatsWithAVGOfTransactionsDTO.add(avgTaxStatsDTO2);
//
//        TaxStatisticsDTO taxStatisticsDTO = new TaxStatisticsDTO(taxStatsWithAVGOfTransactionsDTO, taxStatsWithSumOfTransactionsDTO);
//
//        String sumSample = "2000.00;property_tax;once_after_i_buy;national_institution";
//        String sumSample2 = "100.00;income_tax;once_per_month;national_institution";
//        List<String> sumSamples = new ArrayList<>();
//        sumSamples.add(sumSample);
//        sumSamples.add(sumSample2);
//
//        String avgSample = "2000.00;property_tax;once_after_i_buy;national_institution";
//        String avgSample2 = "100.00;income_tax;once_per_month;national_institution";
//        List<String> avgSamples = new ArrayList<>();
//        avgSamples.add(avgSample);
//        avgSamples.add(avgSample2);
//
//        Mockito.when(taxTypeRepository.getTaxStatsWithSumOfTransactions()).thenReturn(sumSamples);
//        Mockito.when(taxTypeRepository.getTaxStatsWithAVGOfTransactions()).thenReturn(avgSamples);
//
//        //when
//        TaxStatisticsDTO actual = statisticsService.getTaxStatistics();
//
//        assertThat(actual).isEqualTo(taxStatisticsDTO);
//        assertThat(actual.getAvgTaxStats()).isEqualTo(taxStatsWithAVGOfTransactionsDTO);
//        assertThat(actual.getSumTaxStats()).isEqualTo(taxStatsWithSumOfTransactionsDTO);
//        assertThat(actual.getAvgTaxStats()).containsExactlyInAnyOrderElementsOf(taxStatsWithAVGOfTransactionsDTO);
//        assertThat(actual.getSumTaxStats()).containsExactlyInAnyOrderElementsOf(taxStatsWithSumOfTransactionsDTO);
//
//        Mockito.verify(taxTypeRepository).getTaxStatsWithSumOfTransactions();
//        Mockito.verify(taxTypeRepository).getTaxStatsWithAVGOfTransactions();
//    }

}
