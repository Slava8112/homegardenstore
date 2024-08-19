package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> getAll();

    UserEntity getById(Long id);

    UserEntity findByEmail(String email);

    void deleteByEmail(String email);

    UserEntity create(UserEntity userEntity);
}
