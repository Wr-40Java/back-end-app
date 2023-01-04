package Wr40.cardiary.model.dto.tax;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TaxTypeDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String institutionToPayFor;
    @NotNull
    private short institutionToPayForPhoneNumber;
    private String description;

}
