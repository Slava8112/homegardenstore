package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.UserEntity;
import telran.org.scotlandyard.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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
    public UserEntity create(UserEntity userEntity) {
        UserEntity unit = userRepository.save(userEntity);
        // log.debug("User was successfully added   {}", userEntity);
        return unit;
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {  // Изменено на Optional<UserEntity>
        // log.debug("Find user with email {}", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteByEmail(String email) {
        Optional<UserEntity> unit = userRepository.findByEmail(email);
        unit.ifPresent(userRepository::delete);
        // log.debug("Deleted user with email {}", email);
    }
}
