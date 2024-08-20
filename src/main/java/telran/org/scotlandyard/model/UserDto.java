package telran.org.scotlandyard.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto{
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    public UserDto() {}

    public UserDto(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }


}
