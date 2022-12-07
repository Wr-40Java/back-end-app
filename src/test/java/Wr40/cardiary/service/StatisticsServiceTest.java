package Wr40.cardiary.service;

import Wr40.cardiary.model.dto.statistics.AvgMaintenanceStatsDTO;
import Wr40.cardiary.model.dto.statistics.SumMaintenanceStatsDTO;
import Wr40.cardiary.model.dto.statistics.MaintenanceStatisticsDTO;
import Wr40.cardiary.repo.MaintenanceEventRepository;
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

    @InjectMocks
    private StatisticsService statisticsService;

    @Test
    public void whenSearchedForMaintenanceStatistics_shouldReturnAppropriateObjects() {

        // given
        List<SumMaintenanceStatsDTO> maintenanceStatsWithSumOfExpensesDTO = new ArrayList<>();
        List<AvgMaintenanceStatsDTO> maintenanceStatsWithAvgOfExpensesDTO = new ArrayList<>();

        SumMaintenanceStatsDTO sumMaintenanceStatsDTO = new SumMaintenanceStatsDTO(BigDecimal.valueOf(1000.00), "car_repair_shop_1", "engine cylinder repair");
        SumMaintenanceStatsDTO sumMaintenanceStatsDTO2 = new SumMaintenanceStatsDTO(BigDecimal.valueOf(125.00), "car_repair_shop_2", "xenon bulb replacement");
        SumMaintenanceStatsDTO sumMaintenanceStatsDTO3 = new SumMaintenanceStatsDTO(BigDecimal.valueOf(75.00), "car_repair_shop_3", "tyre replacement");
        maintenanceStatsWithSumOfExpensesDTO.add(sumMaintenanceStatsDTO);
        maintenanceStatsWithSumOfExpensesDTO.add(sumMaintenanceStatsDTO2);
        maintenanceStatsWithSumOfExpensesDTO.add(sumMaintenanceStatsDTO3);

        AvgMaintenanceStatsDTO avgMaintenanceStatsDTO = new AvgMaintenanceStatsDTO(BigDecimal.valueOf(1000.00), "car_repair_shop_1", "engine cylinder repair");
        AvgMaintenanceStatsDTO avgMaintenanceStatsDTO2 = new AvgMaintenanceStatsDTO(BigDecimal.valueOf(125.00), "car_repair_shop_2", "xenon bulb replacement");
        AvgMaintenanceStatsDTO avgMaintenanceStatsDTO3 = new AvgMaintenanceStatsDTO(BigDecimal.valueOf(75.00), "car_repair_shop_3", "tyre replacement");
        maintenanceStatsWithAvgOfExpensesDTO.add(avgMaintenanceStatsDTO);
        maintenanceStatsWithAvgOfExpensesDTO.add(avgMaintenanceStatsDTO2);
        maintenanceStatsWithAvgOfExpensesDTO.add(avgMaintenanceStatsDTO3);

        MaintenanceStatisticsDTO maintenanceStatisticsDTO = new MaintenanceStatisticsDTO(maintenanceStatsWithSumOfExpensesDTO, maintenanceStatsWithAvgOfExpensesDTO);

        String expenseData = "1000.00;car_repair_shop_1;engine cylinder repair";
        String expenseData2 = "125.00;car_repair_shop_2;xenon bulb replacement";
        String expenseData3 = "75.00;car_repair_shop_3;tyre replacement";

        List<String> sumExpensesData = new ArrayList<>();
        sumExpensesData.add(expenseData);
        sumExpensesData.add(expenseData2);
        sumExpensesData.add(expenseData3);

        List<String> avgExpensesData = new ArrayList<>();
        avgExpensesData.add(expenseData);
        avgExpensesData.add(expenseData2);
        avgExpensesData.add(expenseData3);

        Mockito.when(maintenanceEventRepository.getMaintenanceStatsWithSumOfExpenses()).thenReturn(sumExpensesData);
        Mockito.when(maintenanceEventRepository.getMaintenanceStatsWithAvgOfExpenses()).thenReturn(avgExpensesData);

        // when
        MaintenanceStatisticsDTO current = statisticsService.getMaintenanceStatistics();

        assertThat(current).isEqualTo(maintenanceStatisticsDTO);
        assertThat(current.getSumMaintenanceStats()).isEqualTo(maintenanceStatsWithSumOfExpensesDTO);
        assertThat(current.getAvgMaintenanceStats()).isEqualTo(maintenanceStatsWithAvgOfExpensesDTO);
        assertThat(current.getSumMaintenanceStats()).containsExactlyInAnyOrderElementsOf(maintenanceStatsWithSumOfExpensesDTO);
        assertThat(current.getAvgMaintenanceStats()).containsExactlyInAnyOrderElementsOf(maintenanceStatsWithAvgOfExpensesDTO);

        Mockito.verify(maintenanceEventRepository).getMaintenanceStatsWithSumOfExpenses();
        Mockito.verify(maintenanceEventRepository).getMaintenanceStatsWithAvgOfExpenses();
    }
}
