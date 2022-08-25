package com.leftovers.restaurants.repository;

import com.leftovers.restaurants.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    Optional<Food> findFoodById(Integer id);
    List<Food> findAll();
    List<Food> findFoodByNameContainingIgnoreCase(String str);
    int deleteFoodById(Integer id);
}