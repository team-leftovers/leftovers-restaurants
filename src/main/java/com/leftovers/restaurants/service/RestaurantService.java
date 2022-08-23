package com.leftovers.restaurants.service;

import com.leftovers.restaurants.dto.CreateRestaurantDTO;
import com.leftovers.restaurants.dto.FullRestaurantDTO;
import com.leftovers.restaurants.dto.ShortRestaurantDTO;
import com.leftovers.restaurants.dto.UpdateRestaurantDTO;
import com.leftovers.restaurants.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    FullRestaurantDTO createNewRestaurant(CreateRestaurantDTO dto);
    List<ShortRestaurantDTO> getAllRestaurants();
    FullRestaurantDTO getRestaurant(Integer id);
    FullRestaurantDTO updateRestaurant(Integer id, UpdateRestaurantDTO dto);
    void deleteRestaurant(Integer id);
}
