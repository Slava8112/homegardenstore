package telran.org.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.scotlandyard.entity.User_out;

@Repository
public interface UserReposit extends JpaRepository<User_out, Long> {
//    public UserNotFoundException(String message) {// in exception
//        super(message);
//    }
}
