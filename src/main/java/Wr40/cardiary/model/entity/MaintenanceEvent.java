package Wr40.cardiary.model.entity;

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
public class MaintenanceEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(precision = 12, scale = 2)
    private BigDecimal cost;
    @Column(length = 45)
    private String companyResponsibleForName;
    private Long companyResponsibleForPhoneNumber;
    private String description;
    private LocalDateTime nextVisitSchedule;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @PrimaryKeyJoinColumn
    @OneToOne
    private MaintenanceHistory maintenanceHistory;
}
