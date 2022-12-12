package Wr40.cardiary.model.dto.technicalService;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class TechnicalServiceDTO {
    @NotNull
    @Digits(integer = 12, fraction = 2)
    private Long cost;
    @NotEmpty
    private String companyResponsibleForName;
    @NotNull
    private Long companyResponsibleForPhoneNumber;
    @NotEmpty
    private String reason;
    @NotEmpty
    private String description;
}
