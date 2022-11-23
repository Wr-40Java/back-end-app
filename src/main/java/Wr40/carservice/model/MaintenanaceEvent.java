package Wr40.carservice.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class MaintenanaceEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(precision = 12, scale = 2)
    private BigDecimal cost;
    @Column(length = 45)
    private String companyResponsibleForName;
    private short companyResponsibleForPhoneNumber;
    private String description;
    private LocalDateTime nextVisitSchedule;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @PrimaryKeyJoinColumn
    private MaintenanceHistory maintenanceHistory;
}
