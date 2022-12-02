package Wr40.cardiary.test.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class TestTaxDTO {
    @NotNull
    @Min(value = 1)
    private BigDecimal costOfTransaction;
}
