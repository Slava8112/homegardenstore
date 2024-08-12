package telran.org.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.scotlandyard.entity.User_out;

import java.util.Optional;

@Repository
public interface UserReposit extends JpaRepository<User_out, Long> {

    Optional<User_out> findByEmail(String email);
//    public UserNotFoundException(String message) {// in exception
//        super(message);
//    }
}
