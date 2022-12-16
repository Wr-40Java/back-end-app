package Wr40.cardiary.model.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private UserRole roleName;

    public Role(UserRole roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName.roleName;
    }
}
