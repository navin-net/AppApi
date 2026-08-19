package org.example.appapi.repositories;

import org.example.appapi.model.Category;
import org.example.appapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByName(String name);

    List<Product> findByCategoryId(Integer categoryId);

    @Query("SELECT p FROM Product p ORDER BY p.id ASC")
    List<Product> findAllOrderedById();

}
