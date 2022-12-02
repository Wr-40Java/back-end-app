package Wr40.cardiary.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45)
    private String name;
    @Column(length = 45)
    private String surname;
    @Column(unique = true, length = 45)
    private String username;
    @Column(length = 45)
    private String password;
    @Column(length = 45)
    private String email;
    private Long phoneNumber;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Role> roles;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Car> cars;
}
