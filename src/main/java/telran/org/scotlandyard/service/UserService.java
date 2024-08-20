package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAll();

    UserEntity getById(Long id);

    UserEntity findByEmail(String email);

    void deleteByEmail(String email);

    UserEntity create(UserEntity userEntity);
}
