package Wr40.cardiary.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Accessors(chain = true) @Setter @Getter
public class InsuranceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45)
    private String type;
    private String description;
    @Column(precision = 10, scale = 2)
    private BigDecimal costsPerYear;
    @Column(precision = 14, scale = 2)
    private BigDecimal coveredCompensation;
}
