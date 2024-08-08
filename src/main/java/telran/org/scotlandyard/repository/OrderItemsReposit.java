package telran.org.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.scotlandyard.entity.OrderItem;

@Repository
public interface OrderItemsReposit extends JpaRepository<OrderItem, Long> {
}
