package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.CreateFoodDTO;
import com.leftovers.restaurants.dto.FullFoodDTO;
import com.leftovers.restaurants.dto.ShortFoodDTO;
import com.leftovers.restaurants.dto.UpdateFoodDTO;

import java.util.List;

public interface FoodService {
    FullFoodDTO createNewFood(CreateFoodDTO dto);
    List<ShortFoodDTO> getAllFood();
    FullFoodDTO getFood(Integer id);
    FullFoodDTO updateFood(Integer id, UpdateFoodDTO dto);
    void deleteFood(Integer id);
}