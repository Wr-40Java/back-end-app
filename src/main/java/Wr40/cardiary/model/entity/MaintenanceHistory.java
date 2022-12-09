package Wr40.cardiary.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
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
//    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "maintenanace_event_id", referencedColumnName = "id")
    private MaintenanaceEvent maintenanaceEvent;
}
