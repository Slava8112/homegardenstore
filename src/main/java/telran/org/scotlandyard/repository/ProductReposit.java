package telran.org.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.scotlandyard.entity.Product;

@Repository
public interface ProductReposit extends JpaRepository<Product, Long> {
}
