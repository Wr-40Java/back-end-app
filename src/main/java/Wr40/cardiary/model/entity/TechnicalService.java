package Wr40.cardiary.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class TechnicalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal cost;
    private String companyResponsibleForName;
    private Long companyResponsibleForPhoneNumber;
    private String reason;
    private String description;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @PrimaryKeyJoinColumn
    @OneToOne(fetch = FetchType.LAZY)
    private MaintenanceHistory maintenanceHistory;
}
