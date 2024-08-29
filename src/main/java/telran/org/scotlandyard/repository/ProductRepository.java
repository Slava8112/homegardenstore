package telran.org.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.entity.Product;
//import telran.org.scotlandyard.service.CategoryService;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

     void deleteById(Long productId);

   List<Product> findAllByCategoryId(Long categoryId);



//   List<Product> getAllDiscountprice();


   //Product findByCategoryId(String categoryId);
}


