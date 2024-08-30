package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.entity.UserEntity;
import telran.org.de.scotlandyard.model.Role;

import java.util.List;

public interface UserService {

    List<UserEntity> getAll();

    UserEntity getById(Long id);

    UserEntity findByEmail(String email);

    void deleteByEmail(String email);

    UserEntity create(UserEntity userEntity, Role role);
}
