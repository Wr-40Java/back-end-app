package Wr40.cardiary.model.dto;

import Wr40.cardiary.validation.constraint.ValidWhenNotNull;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Accessors(chain = true)
public class MaintenanceHistoryDTO {
    @NotEmpty
    private String description;
//    @NotNull
//    @Digits(integer = 14,fraction = 2)
//    private Long overallCost;
//    @NotEmpty
//    private LocalDateTime timestamp;
//    @ValidWhenNotNull(value = "maintenanaceEventDTO")
    private MaintenanceEventDTO maintenanceEventDTO;
}
