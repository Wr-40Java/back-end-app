package Wr40.carservice.model;

import javax.persistence.*;

@Entity
public class TaxType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45)
    private String name;
    @Column(length = 45)
    private String institutionToPayFor;
    private short institutionToPayForPhoneNumber;
    private String description;
}
