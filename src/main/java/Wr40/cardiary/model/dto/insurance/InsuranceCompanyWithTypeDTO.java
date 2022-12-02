package Wr40.cardiary.model.dto.insurance;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

@Data
@Accessors(chain = true)
public class InsuranceCompanyWithTypeDTO {
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
