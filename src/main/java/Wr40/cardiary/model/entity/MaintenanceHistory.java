package Wr40.cardiary.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MaintenanceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(precision = 14, scale = 2)
    private BigDecimal overallCost;
    private String description;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private TechnicalService technicalService;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private MaintenanceEvent maintenanceEvent;
}
