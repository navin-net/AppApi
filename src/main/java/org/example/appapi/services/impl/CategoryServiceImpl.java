package org.example.appapi.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.appapi.exceptions.WebException;
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

    @Override
    public void Create(Category category) throws WebException{
        var checkCategoryName = categoryRepository.findByName(category.getName());
        if (checkCategoryName.isPresent()){
            throw new WebException(
                    "Category have already exists.",
                    "ប្រភេទមានរួចហើយ។",
                    "ERR-001"
            );
        }
        category.setId(0);
        category.setStatus("ACT");
        categoryRepository.save(category);

    }

    @Override
    public void Update(Category category) {
        if (getCategoryById(category.getId()) != null){
            categoryRepository.save(category);
        }
    }

    @Override
    public void Delete(Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }
    }


}
