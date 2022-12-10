package Wr40.cardiary.model.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Accessors(chain = true)
public class MaintenanceEventDTO {
    @NotNull
    @Digits(integer = 12,fraction = 2)
    private Long cost;
    @NotEmpty
    private String companyResponsibleForName;
    @NotNull
    private Long companyResponsibleForPhoneNumber;
    @NotEmpty
    private String description;
    @NotEmpty
    private Date nextVisitSchedule;
    @NotEmpty
    private LocalDateTime timestamp;
}
