package Wr40.cardiary.model.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MaintenanceHistoryDTO {
    @NotNull
    @Digits(integer = 14,fraction = 2)
    private Long overallCost;
    @NotEmpty
    private String description;
    @NotEmpty
    private LocalDateTime timestamp;

}
