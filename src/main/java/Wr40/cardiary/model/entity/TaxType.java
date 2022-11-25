package Wr40.cardiary.model.entity;

import jakarta.persistence.*;

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
