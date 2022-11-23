package Wr40.carservice.model;

import javax.persistence.*;
import java.time.Year;
import java.util.List;
import java.util.Set;

@Entity
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
