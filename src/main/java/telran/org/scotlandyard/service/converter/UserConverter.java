package telran.org.scotlandyard.service.converter;

import org.springframework.stereotype.Component;
import telran.org.scotlandyard.dto.UserCreateDto;
import telran.org.scotlandyard.dto.UserDto;
import telran.org.scotlandyard.entity.UserEntity;


@Component
public class UserConverter implements Converter<UserEntity, UserDto, UserCreateDto> {

    @Override
    public UserEntity toEntity(UserCreateDto userDto) {
        return new UserEntity(userDto.getName(), userDto.getEmail(),
                userDto.getPhone(), userDto.getPassword());
    }

    @Override
    public UserDto toDto(UserEntity user) {
        return new UserDto(user.getId(), user.getName(),user.getEmail(), user.getPhone());

    }
}
