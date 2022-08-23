package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.CreateRestaurantDto;
import com.leftovers.restaurants.dto.UpdateRestaurantDto;
import com.leftovers.restaurants.model.Food;
import com.leftovers.restaurants.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant createNewRestaurant(CreateRestaurantDto dto);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurant(Integer id);
    Restaurant updateRestaurant(Integer id, UpdateRestaurantDto dto);
    void deleteRestaurant(Integer id);
    List<Food> getAllFoodByRestaurant(Integer id);
}
