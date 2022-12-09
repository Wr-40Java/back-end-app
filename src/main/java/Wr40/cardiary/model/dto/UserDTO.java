package Wr40.cardiary.model.dto;

import Wr40.cardiary.validation.constraint.EmailConstraint;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Accessors(chain = true)
public class UserDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    @EmailConstraint
    private String email;
    @Digits(integer = 15, fraction = 0)
    private Long phoneNumber;

}
