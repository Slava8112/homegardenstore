package telran.org.de.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.entity.CartItems;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    void deleteAllByCart(Cart cart);
}
