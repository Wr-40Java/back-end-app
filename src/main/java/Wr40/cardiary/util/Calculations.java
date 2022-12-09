package Wr40.cardiary.util;

import Wr40.cardiary.model.entity.MaintenanceEvent;
import Wr40.cardiary.model.entity.MaintenanceHistory;

import java.math.BigDecimal;

public class Calculations {

    private Calculations() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static void calculateMaintenanceCost(MaintenanceHistory maintenanceHistory) {
        MaintenanceEvent event = maintenanceHistory.getMaintenanaceEvent();
//        TechnicalService service = maintenanceHistory.getTechnicalService();
        BigDecimal overallCost = new BigDecimal(0);
        if (event != null) {
            overallCost = overallCost.add(event.getCost());
        }
//        if (service != null) {
//            overallCost = overallCost.add(service.getCost());
//        }
        maintenanceHistory.setOverallCost(overallCost);
    }
}
