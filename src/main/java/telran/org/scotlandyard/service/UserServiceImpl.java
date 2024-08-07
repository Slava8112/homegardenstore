package telran.org.scotlandyard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.User_out;
import telran.org.scotlandyard.entity.User_out;
import telran.org.scotlandyard.repository.UserReposit;
import telran.org.scotlandyard.repository.UserReposit;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   public UserReposit userReposit;


    @Override
    public List<User_out> getAll() {
        return userReposit.findAll();
    }

    @Override
    public User_out getById(Long id) {
        User_out user_out = userReposit.findById(id).get();
        return user_out;
    }

//    @Override
//    public User_out findById(Long id) {
//        User_out user_out = findById(id);
// //.orElseThrow(() -> new CustomNotFoundException("No Custom with id " + id));
//        return user_out;
//    }

    @Override
    public void deleteById(long id) {
        User_out unit = getById(id);
        userReposit.delete(unit);
    }

    @Override
    public User_out create(User_out user_out) {
        User_out unit= userReposit.save(user_out);
        return unit;
    }

    @Override
    public Optional<User_out> findByEmail(String email) {
        return Optional.empty();
    }
}
