package Wr40.cardiary.util;

import Wr40.cardiary.model.entity.MaintenanceEvent;
import Wr40.cardiary.model.entity.MaintenanceHistory;
import Wr40.cardiary.model.entity.TechnicalService;

import java.math.BigDecimal;

public class Calculations {

    private Calculations() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static void calculateOverallMaintenanceCost(MaintenanceHistory maintenanceHistory) {
        MaintenanceEvent event = maintenanceHistory.getMaintenanceEvent();
        TechnicalService technicalService = maintenanceHistory.getTechnicalService();
        BigDecimal overallCost = new BigDecimal(0);

        if (event != null) {
            if (event.getCost() != null) {
                overallCost = overallCost.add(event.getCost());
            }
        }
        if (technicalService != null) {
            if (technicalService.getCost() != null) {
                overallCost = overallCost.add(technicalService.getCost());
            }
        }
        maintenanceHistory.setOverallCost(overallCost);
    }
}
