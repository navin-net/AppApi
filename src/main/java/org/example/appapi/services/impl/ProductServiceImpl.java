package org.example.appapi.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.appapi.exceptions.WebException;
import org.example.appapi.model.Category;
import org.example.appapi.model.Product;
import org.example.appapi.repositories.CategoryRepository;
import org.example.appapi.repositories.ProductRepository;
import org.example.appapi.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllOrderedById();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getProductsByCategoryId(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }


    @Override
    public void Create(Product product) throws WebException {
        var checkProductName = productRepository.findByName(product.getName());
        if (checkProductName.isPresent()){
            throw new WebException(
                    "product have already exists.",
                    "ប្រភេទមានរួចហើយ។",
                    "ERR-001"
            );
        }
        product.setId(0);
        product.setStatus("ACT");
        productRepository.save(product);
    }

    // Service impl
    @Override
    public void Update(Product product) {
        if (product.getId() == 0) {
            throw new IllegalArgumentException("Product id is required");
        }

        Product existing = productRepository.findById(product.getId())
                .orElseThrow(() -> new NoSuchElementException(
                        "Product not found: " + product.getId()));

        if (product.getCategory() != null && product.getCategory().getId() != 0) {
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new NoSuchElementException(
                            "Category not found: " + product.getCategory().getId()));
            existing.setCategory(category);
        }

        existing.setCode(product.getCode());
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setQtyOnHand(product.getQtyOnHand());
        existing.setStockType(product.getStockType());
        existing.setStatus(product.getStatus());

        productRepository.save(existing);
    }

    @Override
    public void Delete(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }
}
