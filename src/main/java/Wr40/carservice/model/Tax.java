package Wr40.carservice.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(precision = 12, scale = 2)
    private BigDecimal costOfTransaction;
    @OneToOne
    private TaxType taxType;
}
