package Wr40.cardiary.test.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class TestCarDTO {
    @NotEmpty
    private String brand;
    @NotEmpty
    private String model;
    @NotEmpty
    private String vinnumber;
    @NotEmpty
    private String engineType;
    @NotEmpty
    private String bodyType;
    @NotEmpty
    private String color;
    @NotNull
    private int productionYear;
    @NotNull
    @Min(value = 1)
    private short horsePower;
}

