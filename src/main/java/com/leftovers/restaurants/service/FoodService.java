package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.*;

import java.util.List;

public interface FoodService {
    FullFoodDTO createNewFood(CreateFoodDTO dto);
    List<ShortFoodDTO> getAllFood();
    FullFoodDTO getFood(Integer id);
    FullFoodDTO updateFood(Integer id, UpdateFoodDTO dto);
    void deleteFood(Integer id);
    FullFoodDTO updateFoodTags(Integer id, UpdateTagsDTO dto);
    FullFoodDTO deleteFoodTags(Integer fId, Integer tId);
}