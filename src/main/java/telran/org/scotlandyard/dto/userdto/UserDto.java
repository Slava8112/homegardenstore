package telran.org.scotlandyard.dto.userdto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import telran.org.scotlandyard.model.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    public UserDto(Long id, String name, String email, String phone, Role role) {

    }
}
