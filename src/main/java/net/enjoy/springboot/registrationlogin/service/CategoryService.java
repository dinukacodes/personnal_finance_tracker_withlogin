package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.entity.Category;
import net.enjoy.springboot.registrationlogin.entity.User;
import net.enjoy.springboot.registrationlogin.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
    public Category getCategoryById(long id) {
        return categoryRepo.findById(id).orElse(null);
    }
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
    public Category updateCategory(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> getCategoriesByUser(User user) {
        return categoryRepo.findByUser(user);
    }
}
