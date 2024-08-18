package telran.org.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.scotlandyard.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
