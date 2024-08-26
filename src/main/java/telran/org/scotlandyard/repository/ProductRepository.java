package telran.org.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.service.CategoryService;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

   List<Product> findAllByCategoryId(String categoryId);

//   List<Product> getAllDiscountprice();


   //Product findByCategoryId(String categoryId);
}


