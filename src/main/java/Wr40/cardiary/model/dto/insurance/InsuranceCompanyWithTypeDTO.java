package Wr40.cardiary.model.dto.insurance;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import static org.hibernate.annotations.QueryHints.READ_ONLY;

@Data @ToString
@Accessors(chain = true)
public class InsuranceCompanyWithTypeDTO {
    private Long id;
    @NotEmpty
    @Length(max = 45)
    private String name;
    @NotNull
    @Digits(integer = 15, fraction = 0)
    private Long phoneNumber;
    @NotEmpty
    private String description;
    private InsuranceTypeDTO insuranceTypeDTO;
}
