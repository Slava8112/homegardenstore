package telran.org.de.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.de.scotlandyard.entity.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//   void deleteById(Long productId);
   List<Product> findAllByCategoryId(long categoryId);
   //   List<Product> getAllDiscountprice();
}



