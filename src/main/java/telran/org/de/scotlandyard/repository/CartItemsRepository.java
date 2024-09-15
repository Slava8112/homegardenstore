package telran.org.de.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.entity.CartItems;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

    void deleteAllByCart(Cart cart);

    List<CartItems> findAllByCartUserEntityId(Long userId);
}
