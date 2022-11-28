package Wr40.cardiary.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
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
