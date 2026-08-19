package org.example.appapi.services;

import org.example.appapi.exceptions.WebException;
import org.example.appapi.model.Category;
import org.example.appapi.model.Product;
import org.example.appapi.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();



    Product getProductById(Integer id);

    List<Product> getProductsByCategoryId(Integer categoryId);

    void Create(Product product) throws WebException;

    void Update(Product product);

    void Delete(Integer id);
    
}
