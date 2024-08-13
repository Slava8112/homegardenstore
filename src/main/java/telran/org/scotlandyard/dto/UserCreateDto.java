package telran.org.scotlandyard.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@Setter
@Getter
public class UserCreateDto {
    private String name;
    private String email;
    private String phone;
    private String password;

}