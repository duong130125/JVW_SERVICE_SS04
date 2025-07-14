package ra.session_04.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.session_04.entity.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    Page<FoodItem> findByNameContainingAndCategory_Id(String name, Long categoryId, Pageable pageable);
    Page<FoodItem> findByNameContaining(String name, Pageable pageable);
}