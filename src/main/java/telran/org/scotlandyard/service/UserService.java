package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> getAll();

    UserEntity getById(Long id);

    void deleteByEmail(String email);

    UserEntity create(UserEntity userEntity);

    Optional<UserEntity> findByEmail(String email); // Изменено для возвращения Optional
}
