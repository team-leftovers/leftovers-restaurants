package com.leftovers.restaurants.repository;

import com.leftovers.restaurants.model.Food;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food, Integer> {
    Food findFoodById(Integer id);
    List<Food> findFoodByNameContainingIgnoreCase(String str);
}