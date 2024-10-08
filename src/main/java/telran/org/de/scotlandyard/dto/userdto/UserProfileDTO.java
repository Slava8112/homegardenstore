package telran.org.de.scotlandyard.dto.userdto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserProfileDTO {
    private String name;
    private String phone;

    public UserProfileDTO() {
    }

    public UserProfileDTO(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
