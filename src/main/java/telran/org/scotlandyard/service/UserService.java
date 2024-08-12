package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.User_out;

import java.util.List;

public interface UserService {

    List<User_out> getAll();

    User_out getById(Long id);

    void deleteByEmail(String email);

    User_out create(User_out user_out);

    User_out findByEmail(String email);


}
