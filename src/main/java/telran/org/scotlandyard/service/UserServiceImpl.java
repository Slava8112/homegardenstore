package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.dto.UserCreateDto;
import telran.org.scotlandyard.entity.User_out;
import telran.org.scotlandyard.mapper.UserMapper;
import telran.org.scotlandyard.model.UserDto;
import telran.org.scotlandyard.repository.UserReposit;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private final UserReposit userReposit;

    @Autowired
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        return userReposit.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        User_out user = userReposit.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.debug("User with id {}, was retrieved, User {}", user.getId(), user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        User_out user = userMapper.toEntity(userCreateDto);
        User_out savedUser = userReposit.save(user);
        log.debug("User was successfully created: {}", savedUser);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto findByEmail(String email) {
        log.debug("Find user with email {}", email);
        User_out user = userReposit.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public void deleteByEmail(String email) {
        User_out user = userReposit.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.debug("Deleted user with email {}", email);
        userReposit.delete(user);
    }

    @Override
    public UserDto updateUser(Long id, UserCreateDto userCreateDto) {
        User_out user = userReposit.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userCreateDto.getName());
        user.setEmail(userCreateDto.getEmail());
        user.setPhone(userCreateDto.getPhone());
        user.setPassword(userCreateDto.getPassword());
        User_out updatedUser = userReposit.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userReposit.deleteById(id);
    }
}
