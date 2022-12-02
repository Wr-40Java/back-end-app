package Wr40.cardiary.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(precision = 12, scale = 2)
    private BigDecimal costOfTransaction;
    @OneToOne
    private TaxType taxType;
}
