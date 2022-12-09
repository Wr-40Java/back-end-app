package Wr40.cardiary.model.dto.statistics;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value @EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MaintenanceStatisticsDTO {
    List<SumMaintenanceStatsDTO> sumMaintenanceStats;
    List<AvgMaintenanceStatsDTO> avgMaintenanceStats;
}
