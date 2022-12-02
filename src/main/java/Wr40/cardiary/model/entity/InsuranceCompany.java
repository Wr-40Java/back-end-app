package Wr40.cardiary.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Entity
@Setter @Getter @Accessors(chain = true)
public class InsuranceCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45)
    private String name;
    private Long phoneNumber;
    private String description;
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = false)
    private InsuranceType insuranceType;

    @ManyToMany(mappedBy = "insuranceCompanies")
    private Set<Car> cars;
}
