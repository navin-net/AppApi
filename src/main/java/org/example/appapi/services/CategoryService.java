package org.example.appapi.services;

import org.example.appapi.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Integer id);
}
