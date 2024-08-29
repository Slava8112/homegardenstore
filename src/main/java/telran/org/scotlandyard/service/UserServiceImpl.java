package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.UserEntity;
import telran.org.scotlandyard.exception.NoUniqueUserEmailException;
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
    public UserEntity create(UserEntity userEntity, Role role) {
        if (userEntity.getRole() == null) {
            userEntity.setRole(Role.ROLE_CLIENT);
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