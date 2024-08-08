package telran.org.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.org.scotlandyard.entity.Category;

@Repository
public interface CategoryReposit extends JpaRepository<Category, Long> {
}
