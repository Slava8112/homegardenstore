package telran.org.de.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.entity.UserEntity;
import telran.org.de.scotlandyard.exception.NoUniqueUserEmailException;
import telran.org.de.scotlandyard.model.Role;
import telran.org.de.scotlandyard.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> getAll() {
        log.debug("Retrieving all users");
        List<UserEntity> users = userRepository.findAll();
        log.debug("Retrieved {} users", users.size());
        return users;
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserEntity create(UserEntity userEntity) {
        // Проверка на существование пользователя с таким же email
        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            throw new NoUniqueUserEmailException("Пользователь с таким email уже существует: " + userEntity.getEmail());
        }
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found"));
    }

    @Override
    public void deleteByEmail(String email) {
        UserEntity userEntity = findByEmail(email);
        userRepository.delete(userEntity);
    }
}