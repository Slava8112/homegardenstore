package telran.org.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.scotlandyard.entity.Cartitems;

@Repository
public interface CartItemsReposit extends JpaRepository<Cartitems, Long> {
}
