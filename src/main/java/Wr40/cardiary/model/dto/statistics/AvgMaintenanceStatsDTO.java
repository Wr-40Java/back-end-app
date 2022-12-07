package Wr40.cardiary.model.dto.statistics;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AvgMaintenanceStatsDTO {
    @Digits(integer = 14, fraction = 2)
    private BigDecimal averageMaintenanceCost;
    private String companyResponsibleForName;
    private String description;
    private LocalDateTime timestamp;


}
