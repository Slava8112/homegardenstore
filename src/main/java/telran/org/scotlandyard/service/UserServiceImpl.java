package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.UserEntity;
import telran.org.scotlandyard.model.Role;
import telran.org.scotlandyard.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        log.debug("User with id {}, was retrieved, User {}", userEntity.getId(), userEntity);
        return userEntity;

    }

    @Override
    public UserEntity create(UserEntity userEntity, Role role) {
        // Проверяем, есть ли у пользователя назначена роль. Если нет, назначаем роль по умолчанию.
        if (userEntity.getRole() == null) {
            userEntity.setRole(Role.ROLE_ADMIN);
             // Назначаем роль по умолчанию
        }

        UserEntity unit = userRepository.save(userEntity);
        log.debug("User was successfully added   {}", userEntity);
        return unit;
    }

    @Override
    public UserEntity findByEmail(String email) throws UsernameNotFoundException {
        log.debug("Find user with email {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with login " + email + " not found"));
    }

    @Override
    public void deleteByEmail(String email) {
        UserEntity unit = findByEmail(email);
        log.debug("Deleted user with email {}", email);
        userRepository.delete(unit);
    }
}