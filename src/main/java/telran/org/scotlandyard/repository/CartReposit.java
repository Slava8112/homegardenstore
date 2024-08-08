package telran.org.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.scotlandyard.entity.Cart;

@Repository
public interface CartReposit extends JpaRepository<Cart, Long> {
}
