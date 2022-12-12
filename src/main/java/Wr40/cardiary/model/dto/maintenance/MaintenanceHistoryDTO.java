package Wr40.cardiary.model.dto.maintenance;

import Wr40.cardiary.model.dto.technicalService.TechnicalServiceDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class MaintenanceHistoryDTO {
    @NotEmpty
    private String description;
    private MaintenanceEventDTO maintenanceEventDTO;
    private TechnicalServiceDTO technicalServiceDTO;
}
