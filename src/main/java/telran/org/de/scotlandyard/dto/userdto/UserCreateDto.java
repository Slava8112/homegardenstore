package telran.org.de.scotlandyard.dto.userdto;

import lombok.Data;
import lombok.NoArgsConstructor;
import telran.org.de.scotlandyard.model.Role;

@Data
@NoArgsConstructor
public class UserCreateDto {

    private String name;

    private String email;

    private String phone;

    private String password;

    public UserCreateDto(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
