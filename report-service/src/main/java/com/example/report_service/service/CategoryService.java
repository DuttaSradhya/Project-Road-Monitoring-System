package com.example.report_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.report_service.model.Category;
import com.example.report_service.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void addCategory(Category category){
        categoryRepository.save(category);
        log.info("Category {} has been added", category.getName());
    }

    public List<Category> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    public void deleteCategory(String categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            categoryRepository.deleteById(categoryId);
            log.info("Category {} has been deleted", category.get().getName());
        } else {
            log.warn("Category with ID {} not found", categoryId);
        }
    }
    
}
