package com.smartinventory.smartinventory.category;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service 
// Contains category business operations.
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Creates a new category and saves it to the database.
    @Transactional 
    public Category createCategory(String name) {
        Category category = new Category(name);
        return categoryRepository.save(category);
    }

    // Retrieves all categories from the database.
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Retrieves a category by its ID.
    @Transactional(readOnly = true)
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Updates an existing category with a new name.
    @Transactional 
    public Optional<Category> updateCategory(
        Long id,
        String name) {
            return categoryRepository.findById(id).map(category -> {
                category.setName(name);
                return categoryRepository.save(category);
            });
        }

    // Deletes a category by its ID.
    @Transactional 
    public boolean deleteCategory(Long id) {
        return categoryRepository.findById(id)
        .map(category -> {
            categoryRepository.delete(category);
            return true;
        })
        .orElse(false);
    }
    
}
