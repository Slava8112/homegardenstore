//package telran.org.scotlandyard.mapper;
//
//import telran.org.scotlandyard.dto.UserCreateDto;
//import telran.org.scotlandyard.model.UserDto;
//import telran.org.scotlandyard.entity.UserEntity;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserMapper {
//
//    public UserDto toDto(UserEntity user) {
//        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getPassword());
//    }
//
//    public UserEntity toEntity(UserCreateDto dto) {
//        return new UserEntity(null, dto.getName(), dto.getEmail(), dto.getPhone(), dto.getPassword());
//    }
//}