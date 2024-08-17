package telran.org.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.scotlandyard.entity.Category;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.service.CategoryService;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

   Product findByCategoryId(Long categoryId);

}

