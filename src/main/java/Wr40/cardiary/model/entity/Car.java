package Wr40.cardiary.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Column(length = 45)
    private String brand;
    @Column(length = 45)
    private String model;
    @Column(name = "vin_number", unique = true, length = 45)
    private String VINnumber;
    @Column(length = 45)
    private String engineType;
    @Column(length = 45)
    private String bodyType;
    @Column(length = 45)
    private String color;
    private Year productionYear;
    private short horsePower;
    @OneToMany
    @JoinColumn(name = "car_id")
    List<Tax> tax;
    @OneToMany
    @JoinColumn(name = "car_id")
    List<MaintenanceHistory> maintenanceHistories;

    @ManyToMany
    @JoinTable(
            name = "car_company",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_company_id"))
    private Set<InsuranceCompany> insuranceCompanies;
}
