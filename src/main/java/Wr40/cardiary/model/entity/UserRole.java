package Wr40.cardiary.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ANONYMOUS, USER, ADMIN;
    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
