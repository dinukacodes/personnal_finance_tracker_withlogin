package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.entity.Category;
import net.enjoy.springboot.registrationlogin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Display the categories and form to add a new category
    @GetMapping("/categories")
    public String showManageCategoriesPage(Model model) {
        List<Category> categories = categoryService.getAllCategories(); // Fetch all categories
        model.addAttribute("categories", categories); // Add categories to the model
        return "CategoriesPage"; // Ensure this matches your Thymeleaf template name
    }

    // Handle adding a new category
    @PostMapping("/addCategory")
    public String addCategory(@RequestParam("name") String name) {
        Category category = new Category();
        category.setName(name);
        categoryService.saveCategory(category); // Save the new category
        return "redirect:/manageCategories"; // Redirect back to the manage categories page
    }

    // Handle editing a category
    @GetMapping("/editcategory/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "editcategory"; // You'll need to create this Thymeleaf template
    }

    // Handle deleting a category
    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/manageCategories"; // Redirect back to the manage categories page
    }
}
