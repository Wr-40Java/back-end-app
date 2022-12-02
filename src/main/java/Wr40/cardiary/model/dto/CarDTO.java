package Wr40.cardiary.model.dto;

import Wr40.cardiary.validation.YearConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Year;

@Data
@Accessors(chain = true)
public class CarDTO {
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
    @YearConstraint
    private Year productionYear;
    @NotNull
    @Min(value = 1)
    private short horsePower;
}
