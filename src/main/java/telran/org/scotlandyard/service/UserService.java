package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAll();

    UserEntity getById(Long id);

    void deleteByEmail(String email);

    UserEntity create(UserEntity user_out);

    UserEntity findByEmail(String email);


}