package telran.org.de.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.de.scotlandyard.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

   List<Product> findAllByCategoryId(Long categoryId);

   void deleteById(Long productId);

//   List<Product> getAllDiscountprice();


   List<Product> findByCategoryId(Long categoryId);

   //Product findByCategoryId(String categoryId);
}


