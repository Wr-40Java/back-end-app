package Wr40.cardiary.model.dto.maintenance;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MaintenanceEventResponseDTO {
    private Long cost;
    private String companyResponsibleForName;
    private Long companyResponsibleForPhoneNumber;
    private String description;
    private LocalDateTime nextVisitSchedule;
}
