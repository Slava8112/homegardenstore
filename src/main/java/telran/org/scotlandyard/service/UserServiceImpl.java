package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.UserEntity;
import telran.org.scotlandyard.exception.UserNotFoundException;
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
//        UserEntity userEntity = userRepository.findById(id).get();
//        UserServiceImpl.log.debug("User with id {}, was created , User {}", userEntity.getId(), userEntity);
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user with id " + id));
    }

    @Override
    public UserEntity create(UserEntity userEntity) {
        UserEntity unit = userRepository.save(userEntity);
        //log.debug("Order was sacsessfully added   {}", userEntity);
        return unit;
    }

    @Override
    public UserEntity findByEmail(String email) throws UsernameNotFoundException {
        //  log.debug("Find user with email {}", email);
        UserEntity unit = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with login " + email + " not found"));
        return unit;
    }

    @Override
    public void deleteByEmail(String email) {
        UserEntity unit = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("No user with email " + email));
        //  log.debug("Deleted user with email {}", email);
        userRepository.delete(unit);
    }
}
