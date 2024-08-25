package telran.org.scotlandyard.dto.userdto;

import lombok.Getter;
import lombok.Setter;
import telran.org.scotlandyard.model.Role;

@Setter
@Getter
public class UserRegistrationDTO {
    private String name;
    private String email;
    private String phone;
    private String password;
    private Role role;

    public UserRegistrationDTO() {}

    public UserRegistrationDTO(String name, String email, String phone, String password, Role role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

}
