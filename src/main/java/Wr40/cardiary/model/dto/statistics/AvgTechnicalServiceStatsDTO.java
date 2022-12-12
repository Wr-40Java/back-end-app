package Wr40.cardiary.model.dto.statistics;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AvgTechnicalServiceStatsDTO {
    @Digits(integer = 14, fraction = 2)
    private BigDecimal averageTechnicalServiceCost;
    private String companyResponsibleForName;
    private String reason;
    private String description;
}
