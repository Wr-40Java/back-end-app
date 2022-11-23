package Wr40.carservice.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class MaintenanceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(precision = 14, scale = 2)
    private BigDecimal overallCost;
    private String description;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @OneToOne
    private TechnicalService technicalService;
    @OneToOne
    private MaintenanaceEvent maintenanaceEvent;
}
