package ra.session_04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ra.session_04.entity.FoodItem;
import ra.session_04.repository.FoodItemRepository;

@Service
public class FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;

    public Page<FoodItem> searchFood(String name, Long categoryId, int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());

        if (categoryId != null && categoryId != 0) {
            return foodItemRepository.findByNameContainingAndCategory_Id(name, categoryId, pageable);
        }
        return foodItemRepository.findByNameContaining(name, pageable);
    }

    public void save(FoodItem foodItem) {
        foodItemRepository.save(foodItem);
    }

    public FoodItem getById(Long id) {
        return foodItemRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        foodItemRepository.deleteById(id);
    }
}