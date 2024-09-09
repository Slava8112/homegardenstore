package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAll();

    UserEntity getById(Long id);

    UserEntity findByEmail(String email);

    void deleteByEmail(String email);

    Long getCurrentUserId();

    UserEntity create(UserEntity userEntity);
}
