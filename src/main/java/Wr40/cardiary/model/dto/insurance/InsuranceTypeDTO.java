package Wr40.cardiary.model.dto.insurance;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

import static org.hibernate.annotations.QueryHints.READ_ONLY;

@Data
@Accessors(chain = true)
public class InsuranceTypeDTO {
    private Long id;
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
