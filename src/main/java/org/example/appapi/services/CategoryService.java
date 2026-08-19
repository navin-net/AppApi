package org.example.appapi.services;

import org.example.appapi.exceptions.WebException;
import org.example.appapi.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAllCategories();



    Category getCategoryById(Integer id);

    void Create(Category category) throws WebException;

    void Update(Category category);

    void Delete(Integer id);
}
