package Wr40.cardiary.util;

import Wr40.cardiary.model.entity.MaintenanceEvent;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.model.entity.TechnicalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class CalculationsTest {

    @Test
    @DisplayName("Should Return Overall Cost Equals To Zero When Maintenance Event and Technical Service Are Null")
    void shouldReturnOverallCostEqualsToZerWhenMaintenanceEventAndTechnicalServiceAreNull() {
        // Given
        MaintenanceHistory mh = new MaintenanceHistory();
        // When
        Calculations.calculateOverallMaintenanceCost(mh);
        // Then
        assertThat(mh.getOverallCost()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Should Return Overall Cost Equals To Zero When Maintenance Event And Technical Service Have No Costs")
    void shouldReturnOverallCostEqualsToZeroWhenMaintenanceEventAndTechnicalServiceHaveNoCosts() {
        // Given
        MaintenanceHistory mh = new MaintenanceHistory();
        TechnicalService technicalService = new TechnicalService();
        MaintenanceEvent maintenanceEvent = new MaintenanceEvent();
        mh.setTechnicalService(technicalService);
        mh.setMaintenanceEvent(maintenanceEvent);
        // When
        Calculations.calculateOverallMaintenanceCost(mh);
        // Then
        assertThat(mh.getOverallCost()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Should Return Overall Cost Equals To Technical Service When Technical Service Has Cost")
    void shouldReturnOverallCostEqualsToTechnicalServiceWhenTechnicalServiceHasCost() {
        // Given
        MaintenanceHistory mh = new MaintenanceHistory();
        TechnicalService technicalService = new TechnicalService();
        long costOfTechnicalService = 120L;
        technicalService.setCost(BigDecimal.valueOf(costOfTechnicalService));
        mh.setTechnicalService(technicalService);
        // When
        Calculations.calculateOverallMaintenanceCost(mh);
        // Then
        assertThat(mh.getOverallCost()).isEqualTo(BigDecimal.valueOf(costOfTechnicalService));
    }

    @Test
    @DisplayName("Should Return Overall Cost Equals To Maintenance Service When Maintenance Service Has Cost")
    void shouldReturnOverallCostEqualsToMaintenanceServiceWhenMaintenanceServiceHasCost() {
        // Given
        MaintenanceHistory mh = new MaintenanceHistory();
        MaintenanceEvent maintenanceEvent = new MaintenanceEvent();
        long costOfMaintenanceService = 120L;
        maintenanceEvent.setCost(BigDecimal.valueOf(costOfMaintenanceService));
        mh.setMaintenanceEvent(maintenanceEvent);
        // When
        Calculations.calculateOverallMaintenanceCost(mh);
        // Then
        assertThat(mh.getOverallCost()).isEqualTo(BigDecimal.valueOf(costOfMaintenanceService));
    }

    @Test
    @DisplayName("Should Return Sum Of Two Cost When They Are Present")
    void shouldReturnSumOfTwoCostWhenTheyArePresent() {
        // Given
        MaintenanceHistory mh = new MaintenanceHistory();
        MaintenanceEvent maintenanceEvent = new MaintenanceEvent();
        long costOfMaintenanceService = 120L;
        maintenanceEvent.setCost(BigDecimal.valueOf(costOfMaintenanceService));
        mh.setMaintenanceEvent(maintenanceEvent);

        TechnicalService technicalService = new TechnicalService();
        long costOfTechnicalService = 120L;
        technicalService.setCost(BigDecimal.valueOf(costOfTechnicalService));
        mh.setTechnicalService(technicalService);

        long sumTotal = Long.sum(costOfTechnicalService, costOfMaintenanceService);
        // When
        Calculations.calculateOverallMaintenanceCost(mh);
        // Then
        assertThat(mh.getOverallCost()).isEqualTo(BigDecimal.valueOf(sumTotal));
    }
}
