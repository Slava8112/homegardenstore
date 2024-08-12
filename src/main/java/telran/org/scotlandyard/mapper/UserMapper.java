package telran.org.scotlandyard.mapper;

import telran.org.scotlandyard.dto.UserCreateDto;
import telran.org.scotlandyard.model.UserDto;
import telran.org.scotlandyard.entity.User_out;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User_out user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getPassword());
    }

    public User_out toEntity(UserCreateDto dto) {
        return new User_out(null, dto.getName(), dto.getEmail(), dto.getPhone(), dto.getPassword());
    }
}