package Wr40.cardiary.model.dto.maintenance;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class MaintenanceEventDTO {
    @NotNull
    @Digits(integer = 12, fraction = 2)
    private Long cost;
    @NotEmpty
    private String companyResponsibleForName;
    @NotNull
    private Long companyResponsibleForPhoneNumber;
    @NotEmpty
    private String description;
    @Future
    private LocalDateTime nextVisitSchedule;
}
