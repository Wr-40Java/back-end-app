package Wr40.cardiary.model.dto;

import Wr40.cardiary.validation.constraint.EmailConstraint;
import Wr40.cardiary.validation.constraint.PasswordConstraint;
import Wr40.cardiary.validation.constraint.PhoneConstraint;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

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
    @PasswordConstraint
    private String password;
    @NotEmpty
    @EmailConstraint
    private String email;
    @Digits(integer = 15, fraction = 0)
    @PhoneConstraint
    private Long phoneNumber;

}
