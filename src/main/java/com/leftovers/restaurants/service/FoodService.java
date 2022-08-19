package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.CreateFoodDto;
import com.leftovers.restaurants.dto.UpdateFoodDto;
import com.leftovers.restaurants.model.Food;

import java.util.List;

public interface FoodService {
    Food createNewFood(CreateFoodDto dto);
    List<Food> getAllFood();
    Food getFood(Integer id);
    Food updateFood(Integer id, UpdateFoodDto dto);
    void deleteFood(Integer id);
}
