package Wr40.cardiary.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Getter
public class InsuranceCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45)
    private String name;
    private short phoneNumber;
    private String description;
    @OneToOne
    private InsuranceType insuranceType;

    @ManyToMany(mappedBy = "insuranceCompanies")
    private Set<Car> cars;
}
