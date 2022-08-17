package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.CreateFoodDto;
import com.leftovers.restaurants.dto.CreateRestaurantDto;
import com.leftovers.restaurants.dto.UpdateFoodDto;
import com.leftovers.restaurants.dto.UpdateRestaurantDto;
import com.leftovers.restaurants.model.Food;
import com.leftovers.restaurants.model.Restaurant;

import java.util.List;

public interface FoodService {
    List<Food> getAllFood();
    Food getFood(Integer id);
    Food updateFood(Integer id, UpdateFoodDto dto);
    void deleteFood(Integer id);
}
