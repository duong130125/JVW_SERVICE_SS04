package ra.session_04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ra.session_04.entity.Category;
import ra.session_04.entity.FoodItem;
import ra.session_04.repository.CategoryRepository;
import ra.session_04.service.FoodItemService;

import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/foods")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String listFoods(Model model,
                            @RequestParam(defaultValue = "") String name,
                            @RequestParam(required = false) Long categoryId,
                            @RequestParam(defaultValue = "0") int page) {
        Page<FoodItem> foodPage = foodItemService.searchFood(name, categoryId, page);
        model.addAttribute("foods", foodPage);
        model.addAttribute("name", name);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryRepository.findAll());
        return "food-list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("foodItem", new FoodItem());
        model.addAttribute("categories", categoryRepository.findAll());
        return "food-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute FoodItem foodItem) {
        foodItemService.save(foodItem);
        return "redirect:/foods";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        FoodItem item = foodItemService.getById(id);
        model.addAttribute("foodItem", item);
        model.addAttribute("categories", categoryRepository.findAll());
        return "food-form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        foodItemService.delete(id);
        return "redirect:/foods";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Long id = Long.parseLong(text);
                Category category = categoryRepository.findById(id).orElse(null);
                setValue(category);
            }
        });
    }
}