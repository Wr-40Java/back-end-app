package Wr40.cardiary.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity @EqualsAndHashCode
@Accessors(chain = true) @Setter @Getter
public class InsuranceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String type;
    private String description;
    private BigDecimal costsPerYear;
    private BigDecimal coveredCompensation;
}
