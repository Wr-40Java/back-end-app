package Wr40.cardiary.model.dto.technicalService;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TechnicalServiceResponseDTO {
    private Long cost;
    private String companyResponsibleForName;
    private Long companyResponsibleForPhoneNumber;
    private String reason;
    private String description;
}
