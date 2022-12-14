package Wr40.cardiary.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    @Column(name = "vin_number")
    private String VINnumber;
    private String engineType;
    private String bodyType;
    private String color;
    private Year productionYear;
    private short horsePower;
    @OneToMany
    @JsonIgnore
    @JoinColumn(name = "car_id")
    List<Tax> tax;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "car_id")
    List<MaintenanceHistory> maintenanceHistories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "car_company",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_company_id"))
    private Set<InsuranceCompany> insuranceCompanies = new HashSet<>();

    public void addInsuranceCompany(InsuranceCompany insuranceCompany) {
        insuranceCompanies.add(insuranceCompany);
        insuranceCompany.getCars().add(this);
    }

    public void removeInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompanies.remove(insuranceCompany);
        insuranceCompany.getCars().remove(this);
    }
}
