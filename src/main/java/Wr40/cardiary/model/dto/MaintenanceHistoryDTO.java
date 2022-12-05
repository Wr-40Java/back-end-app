package Wr40.cardiary.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MaintenanceHistoryDTO {
    @NotEmpty
    private String description;
}
