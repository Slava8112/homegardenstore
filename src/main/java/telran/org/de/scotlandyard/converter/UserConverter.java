package telran.org.de.scotlandyard.converter;

import org.springframework.stereotype.Component;
import telran.org.de.scotlandyard.dto.userdto.UserCreateDto;
import telran.org.de.scotlandyard.entity.UserEntity;
import telran.org.de.scotlandyard.dto.userdto.UserDto;


@Component
public class UserConverter implements Converter<UserEntity, UserDto, UserCreateDto> {

    @Override
    public UserEntity toEntity(UserCreateDto dto) {
        return new UserEntity( dto.getName(), dto.getEmail(), dto.getPhone(), dto.getPassword());
    }

    @Override
    public UserDto toDto(UserEntity entity) {
        return new UserDto(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone(), null);

    }
}
