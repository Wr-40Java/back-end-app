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
public class SumTechnicalServiceStatsDTO {
    @Digits(integer = 12, fraction = 2)
    private BigDecimal technicalServiceCost;
    private String companyResponsibleForName;
    private String reason;
    private String description;
}
