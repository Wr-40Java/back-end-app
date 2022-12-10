package Wr40.cardiary.model.entity;

import jakarta.persistence.*;
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    List<MaintenanceHistory> maintenanceHistories;

    @ManyToMany
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
