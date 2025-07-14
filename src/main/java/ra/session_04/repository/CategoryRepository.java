package ra.session_04.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.session_04.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}