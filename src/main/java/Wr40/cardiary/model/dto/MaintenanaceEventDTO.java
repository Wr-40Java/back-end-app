package Wr40.cardiary.model.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class MaintenanaceEventDTO {
    @Digits(integer = 12, fraction = 2)
    @NotEmpty
    private BigDecimal cost;
    @NotEmpty
    @Length(max = 45)
    private String companyResponsibleForName;
    @NotEmpty
    private Long companyResponsibleForPhoneNumber;
    private String description;
    @NotEmpty
    private LocalDateTime nextVisitSchedule;
}
