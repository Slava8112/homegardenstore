package telran.org.scotlandyard.service;

import telran.org.scotlandyard.dto.UserCreateDto;
import telran.org.scotlandyard.model.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto getById(Long id);

    void deleteByEmail(String email);

    UserDto create(UserCreateDto userCreateDto);

    UserDto findByEmail(String email);

    UserDto updateUser(Long id, UserCreateDto userCreateDto);

    void deleteUser(Long id);
}

