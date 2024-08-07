package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.User_out;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User_out> getAll();

    User_out getById(Long id);

    void deleteById(long id);

    User_out create(User_out user_out);

    Optional<User_out> findByEmail(String email);
}
