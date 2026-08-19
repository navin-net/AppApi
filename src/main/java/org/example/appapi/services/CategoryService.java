package org.example.appapi.services;

import org.example.appapi.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Integer id);

    void Create(Category category);

    void Update(Category category);

    void Delete(Integer id);
}
