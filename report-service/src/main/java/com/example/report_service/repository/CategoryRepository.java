package com.example.report_service.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.report_service.model.Category;
import java.util.List;


public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByName(String name);
}
