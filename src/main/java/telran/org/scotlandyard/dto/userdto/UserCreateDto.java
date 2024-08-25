package telran.org.scotlandyard.dto.userdto;

import lombok.Data;
import lombok.NoArgsConstructor;
import telran.org.scotlandyard.model.Role;

@Data
@NoArgsConstructor
public class UserCreateDto {

    private String name;

    private String email;

    private String phone;

    private String password;
    private Role role;

    public UserCreateDto(String name, String email, String phone, String password, Role role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }
}
