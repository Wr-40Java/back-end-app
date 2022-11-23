package Wr40.carservice.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
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
