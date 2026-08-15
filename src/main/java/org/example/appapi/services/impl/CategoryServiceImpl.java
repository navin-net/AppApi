package org.example.appapi.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.appapi.model.Category;
import org.example.appapi.repositories.CategoryRepository;
import org.example.appapi.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    //Call Repository
    //Object Injection by field , method , constructor

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
