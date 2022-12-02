package Wr40.cardiary.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class InsuranceTypeDTO {
    @NotEmpty
    @Length(max = 45)
    private String type;
    @NotEmpty
    private String description;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal costsPerYear;
    @NotNull
    @Digits(integer = 14, fraction = 2)
    private BigDecimal coveredCompensation;
}
